package com.aaa.gjj.controller;

import com.aaa.gjj.service.QianTaiService;
import com.aaa.gjj.util.MessageUtil;
import com.aaa.gjj.util.PhoneTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:QanTai
 * discription:
 * author:qcm
 * createTime:2018-12-26 20:26
 */
@Controller
@RequestMapping("/qiantai")
public class QanTai {

    @Autowired
    private QianTaiService qianTaiService;

    @Autowired
    private HttpSession httpSession;

    /**
     * 前台个人登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/denglu")
    public Object tree(@RequestBody Map map){
        System.out.println("登录进来了"+map);
        String phone = MessageUtil.getCode(map.get("phone") + "");
        System.out.println("phone="+phone);
        List<Map> list = qianTaiService.ChackPersonLogin(map);
        System.out.println("后台返回的值："+list);
        httpSession.setAttribute("list",list);
        if (list.size()>0&&list!=null){
            return 1;
        }else {
            return 0;
        }

//        if (list.size()>0&&list!=null){
//            //return "forward:/Page/green";
//            return "redirect:/Page/green";
//        }
//        return "forward:/Page/denglu";
    }

    /**
     * 短信验证码验证
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("sendCode")
    public int sendCode(@RequestBody Map map) {
        System.out.println("前台传来的值===："+map.get("tel"));
        //int modelMsg = PhoneTest.getModelMsg(map.get("tel")+"");
        return 0;
    }

    /**
     * 缴存记录
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/seljiao")
    public Object SelJiao(@RequestParam Map map){
        System.out.println("前台传来的值："+map);
        List<Map> list = qianTaiService.SelectCheckJiLu(map);
        System.out.println("后台返回的值："+list);
        return list;
    }

    /**
     * 贷款信息记录
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/daikuan")
    public Object DaiKuan(@RequestParam Map map){
        System.out.println("贷款前台传来的值："+map);
        List<Map> list = qianTaiService.SelectCheckDaiKuanJiLu(map);
        System.out.println("贷款后台返回的值："+list);
        return list;
    }
    /**
     * 前台单位登录
     * @return
     */
    @RequestMapping("/danwei")
    public Object DanWei(@RequestParam Map map){
        System.out.println("单位登录进来了"+map);
        List<Map> list = qianTaiService.ChackUnitLogin(map);
        System.out.println("单位后台返回的值："+list);
        httpSession.setAttribute("list",list);
        if (list.size()>0&&list!=null){
            return "qiantai/dxinxi";
        }
        return "qiantai/danwei";
    }
    /**
     * 前台单位登录基金缴纳
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/danjiaona")
    public Object DanJiaoNa(@RequestParam Map map){
        System.out.println("单位基金缴纳前台传来的值："+map);
        List<Map> list = qianTaiService.ChackUnitLogin1(map);
        System.out.println("单位基金缴纳后台返回的值："+list);
        return list;
    }
    /**
     * 前台单位登录单位缴纳记录
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/seldanjiao")
    public Object DanJiaoNaJiLu(@RequestParam Map map){
        System.out.println("单位基金缴纳记录前台传来的值："+map);
        List<Map> list = qianTaiService.UnitJiaoNaJiLu(map);
        System.out.println("单位基金缴纳记录后台返回的值："+list);
        return list;
    }
    /**
     * 查看单个小新闻
     * @param map
     * @return
     */
    @RequestMapping("/selnews")
    public Object Selnews(@RequestParam Map map, Model model){
        System.out.println("查看单个小新闻前台传来的值："+map);
        List<Map> list = qianTaiService.Selnews(Integer.valueOf(map.get("id")+""));
        System.out.println("查看单个小新闻后台返回的值："+list.get(0));
        model.addAttribute("news",list.get(0));
        return "qiantai/gongzuo11";
    }



    //=================新闻模块=============================================
    /**
     * 查询新闻信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/infomation")
    public Object Infomation(@RequestBody Map map){
        System.out.println("新闻首页前台传来的值："+map);
        Map tempMap = new HashMap();
        tempMap.put("pageDate",qianTaiService.informationList(map));
        tempMap.put("total",qianTaiService.informationListCount(map));
        System.out.println("新闻首页后台返回的值："+tempMap);
        return tempMap;
    }
    /**
     * 查询新闻信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateInformation")
    public Object UpdateInForMation(@RequestBody Map map){
        System.out.println("新闻修改前台传来的值："+map);
        int i = qianTaiService.upInformation(map);
        System.out.println("新闻修改后台返回的值："+i);
        return i;
    }
    /**
     * 新闻添加
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/addInformation")
    public Object AddInformation(@RequestBody Map map){
        System.out.println("新闻添加前台传来的值："+map);
        int i = qianTaiService.information(map);
        System.out.println("新闻添加后台返回的值："+i);
        return i;
    }
    /**
     * 新闻删除
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteInformation")
    public Object DeleteInformation(@RequestBody Map map){
        System.out.println("新闻添加前台传来的值："+map);
        int i = qianTaiService.delInformation(Integer.valueOf(map.get("id")+""));
        System.out.println("新闻添加后台返回的值："+i);
        return i;
    }
    /**
     * 新闻传输到前台
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/selnewgall")
    public Object SelnewGall(@RequestBody Map map){
        System.out.println("新闻传输到前台传来的值："+map);
        List<Map> tid = qianTaiService.selnewgall(Integer.valueOf(map.get("tid") + ""));
        System.out.println("新闻传输到前台后台返回的值："+tid);
        return tid;
    }
}
