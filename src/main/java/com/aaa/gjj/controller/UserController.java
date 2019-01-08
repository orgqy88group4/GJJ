package com.aaa.gjj.controller;

import com.aaa.gjj.service.UserService;
import com.aaa.gjj.util.AESUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;


/**
 * className:UserController
 * discription:
 * author:fhm
 * createTime:2018-12-04 18:19
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;
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
            return "redirect:/sb/Page/toTree";

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

    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/Login")
    public Object login(String userName, String passWord, @RequestParam Map map,Model model) throws NoSuchPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        /**
         * 使用shiro编写认证操作
         */
        //加密
        //String password = AESUtil.getInstance().encrypt(passWord);
        //1 获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(userName,passWord);
        //3 执行登录方法
        try {
            //执行过程中把token传递给shiro，然后shiro执行登录操作,根据有无异常判断登录成功还是失败
            subject.login(token);
            session.setAttribute("userName",userName);
            session.setAttribute("account_name",getaccount_name(userName));
            //List<Map> userName = empService.selByUserName(username);
            //model.addAttribute("name",username);
            model.addAttribute("msg","欢迎："+userName);
            map.put("msg","登录成功！");
            return "redirect:/Page/toTree";
        } catch (UnknownAccountException e) {
            //model.addAttribute("errorUnm","用户名不存在！");
            map.put("errorUnm","用户名不存在！");
            model.addAttribute("msg","用户名不存在");
            return "forward:/user/toLogin";
        } catch (IncorrectCredentialsException e){
            //返回 IncorrectCredentialsException 此异常，密码错误
            map.put("errorPwd","密码错误！");
            //model.addAttribute("errorPwd","密码错误！");
            model.addAttribute("msg","密码不正确");
            return "forward:/user/toLogin";
        }

    }

    /**
     * 获取用户名
     * @return
     */
    public String getaccount_name(String userName){
        List<Map> list = userService.selByUserName(userName);
        if(list.size()>0&&list!=null){
            Map map=list.get(0);
            String account_name=map.get("account_name").toString();
            return account_name;
        }
        return null;
    }

    /**
     * 登录成功跳转主页
     * @return
     */
    @RequestMapping("/toIndex")
    public String toIndex(){
        return "redirect:/sb/Page/toTree";

    }
}
