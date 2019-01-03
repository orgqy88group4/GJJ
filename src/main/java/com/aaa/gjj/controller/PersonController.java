package com.aaa.gjj.controller;

import com.aaa.gjj.entity.Person;
import com.aaa.gjj.entity.PersonsAccountNumberState;
import com.aaa.gjj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * className:UserController
 * discription:
 * author:fhm
 * createTime:2018-12-04 18:19
 */
@Controller
@RequestMapping("/person")
public class PersonController {

    /**
     * 测试方法
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/hello")
    public Object hello() {
        System.out.println("Hello SpringBootMybatisShiro");
        return "Hello world";
    }

    /**
     * 测试thymeleaf
     *
     * @return
     */
    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model) {
        //把数据存入model
        model.addAttribute("name", "AAA软件教育");
        //返回test.html
        return "test";
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/add";
    }

    /**
     * 跳转到更新页面
     *
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate() {
        return "user/update";
    }

    /**
     * 跳转到登陆页面
     *
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    /**
     * 跳转到登陆页面
     *
     * @return
     */
    @RequestMapping("/unAuth")
    public String unAuth() {
        return "noAuth";
    }

    /**
     * 登陆逻辑处理
     *
     * @return
     */
    @RequestMapping("/login")
    public String login(String name, String password,Model model) {
        System.out.println("用户名："+name+"，密码："+password);
        /**
         * 使用shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();

        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);

        //3.执行登陆方法
        try {
            subject.login(token);
            //登陆成功,跳转到主页
            return "redirect:/Page/toTree";

        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            //登录失败，用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "forward:/user/toLogin";
        } catch (IncorrectCredentialsException e){
            //e.printStackTrace();
            //登录失败，密码错误
            model.addAttribute("msg","密码不正确");
            return "forward:/user/toLogin";
        }
    }

    //1211
    @Autowired
    private UserService userService;

    /**
     * 获取到待审核表 个人转移信信息  和前台获取的值对比 不能有相同操作的人
     * 提交审核的按钮
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/submitVerify1")
    public Object submitVerify1(@RequestBody Map map){
        List<Map> submitVerify = userService.submitVerify1(map);
        if(submitVerify.size()>0){
            return "1";
        }else{
            return "0";
        }
    }

    /**
     * 下拉框选中公司 获取ID  查询公司的受托银行
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/Trustee1")
    public Object Trustee1(@RequestBody Map map) {
        System.out.println("======"+map);
        //System.out.println(map.get("uaBankName"));
        List<Map> stName = userService.Trustee1(Integer.valueOf(map.get("selectId")+""));
        System.out.println("+++++"+stName);
        return stName;
    }

    /**
     * 提交审核  根据这条信息取到在职公司单位
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/transfer1")
    public Object text1(@RequestBody Map map) {
        return userService.UserIDSelect1(map.get("tb_pName")+"");
    }

    /**
     *待转移用户列表
     * @return
     */
    @ResponseBody   //标志该方法返回值为json集合对象		相当于response.getWriter().print(json);
    @RequestMapping("/PersonTransfer1")
    public Object userList1(@RequestBody Map map) {
        System.out.println("-----------"+map);
        List<Person> people = userService.UserSelect1(map);
        if(people!=null&people.size()>0){
            Map tempMap = new HashMap();
            //绑定数据 分页数据
            tempMap.put("pageDate", people);
            //总数量
            tempMap.put("total",userService.UserCount12(map));
            System.out.println("============111"+tempMap);
            return tempMap;
        }else {
            return "0";
        }
    }

    /**
     * element
     * @param map
     * @return
     * controller --放到service层
     */
    @ResponseBody
    @RequestMapping("/page1")
    public Object list(@RequestBody Map map){
        System.out.println("这是年后你回家"+map);
        if (map!=null&&map.size()>0){
            List<Person> page = userService.getPage1(map);  //获取分页查询的值
            Map tempMap = new HashMap();
            //绑定数据 分页数据
            tempMap.put("pageDate", page);
            //总数量
            tempMap.put("total",userService.getPageCount1(map));
            tempMap.put("selectss",userService.select());
            System.out.println("============"+tempMap);
            return tempMap;
        }else {
            return "0";
        }
    }
    @ResponseBody
    @RequestMapping("/submits1")
    public Object submits1(@RequestBody Map map,HttpSession session) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String name = session.getAttribute("account_name")+"";  //获取 当前操作人员
        String  pid = map.get("tb_pid")+"";  //获取id
        List<Map> shift = userService.shift1(Integer.valueOf(pid));//根据id查询数据 个人信息 然后添加到待转移列表
        List<Map> selName = userService.uname1(Integer.valueOf(map.get("selName")+""));//通过id查询的信息   因为前台数据冲突
        String puname = selName.get(0).get("uname")+"";//通过id查询的转入单位名称   因为前台数据冲突
        if(shift!=null&&shift.size()>0&&selName!=null&&selName.size()>0){
            Map tempMap = new HashMap();
            tempMap.put("transfer_out_unit", map.get("uname"));//转出单位
            tempMap.put("transfer_into_unit",puname);//转入单位
            tempMap.put("auditor",shift.get(0).get("tb_pName"));//申请人
            tempMap.put("person_account",shift.get(0).get("GRZH"));//个人帐号
            tempMap.put("transfer_reason",map.get("zy"));//转移原因
            tempMap.put("operator",name);//操作人		（操作人待添加）
            tempMap.put("submit_time",df.format(new Date()));//提交时间
            tempMap.put("audit_state","待审核");//审核状态
            tempMap.put("pname",shift.get(0).get("tb_pName"));//待转移人员姓名
            tempMap.put("id",pid);					//待转移人员id
            tempMap.put("idNumber",shift.get(0).get("tb_idNUmber"));//证件号码
            tempMap.put("balance",shift.get(0).get("dalance"));//个人账号余额
            tempMap.put("state",shift.get(0).get("peraccState"));//个人账号状态
            tempMap.put("baseNummber",Double.parseDouble(shift.get(0).get("baseNummber")+""));//个人缴存基数
            if(tempMap!=null&&tempMap.size()>0){
                return userService.addShift1(tempMap);
            }
        }
        return 0;
    }
    /**
     * 跳转人员转移		先调转到获取下拉框值的方法 获取到值到前台  然后跳转
     * @param model
     * @return
     */
    @RequestMapping("/skip")
    public String skip(Model model) {
        return "redirect:/person/select";
    }
    /**
     * 跳转人员转移		先调转到获取下拉框值的方法 获取到值到前台  然后跳转
     * @param model
     * @return
     */
    @RequestMapping("/skip1")
    public String skip1(Model model) {
        return "redirect:/person/select1";
    }
    /**
     * 获取查询到的下拉框的转为单位的所有名称 然后跳转到页面
     * @return
     *
     */
    @RequestMapping("/select")
    public String select(Model model) {
        List<Map> userSelect = userService.select();
        model.addAttribute("select",userSelect);
        return "back/person/PersonTransfer";
    }



    /**
     * 获取查询到的下拉框的转为单位的所有名称 然后跳转到页面
     * @return
     *
     */
    @RequestMapping("/select1")
    public String select1(Model model) {
//        List<Map> userSelect = userService.select();
//        model.addAttribute("select",userSelect);
        return "back/person/PersonTransfered";
    }
}
