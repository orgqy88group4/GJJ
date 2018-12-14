package com.aaa.gjj.controller;

import com.aaa.gjj.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * className:JumpPageController
 * discription:
 * author:qcm
 * createTime:2018-12-10 11:15
 */
@Controller
@RequestMapping("/Page")
public class JumpPageController {

    @Autowired
    private PowerService powerService;

    //个人信息页面
    @RequestMapping("/PersonaInfo")
    public String PersonaInfo(){
        return "back/person/PersonDatil";
    }
    //单位信息页面
    @RequestMapping("/UnitInfo")
    public String UnitInfo(){
        return "back/person/Unit_Information";
    }
    //年度贷款金额统计图
    @RequestMapping("/LoanInfoMap")
    public String LoanInfoMap(){
        return "back/financial/DKQKXX";
    }
    //年度还款金额统计图
    @RequestMapping("/RefundInformationMap")
    public String RefundInfoMap(){
        return "back/financial/HJTQXX";
    }
    //单位注册，点击保存按钮后跳到账户信息  单位信息
    @RequestMapping("/UnitInformationa01")
    public String Unit_Information(){
        return "back/person/Unit_Information";
    }
    //单位开户
    @RequestMapping("/unitRegister")
    public String unitRegister(){
        return "back/company/unitRegister";
    }
    //跳转权限树页面
    @RequestMapping("/toTree")
    public String toTree(){
        return "back/power/tree";
    }

    /**
     * 跳转权限树页面
     * @return
     */
    @RequestMapping("/toadd")
    public String toadd(){
        return "back/power/add";
    }
    /**
     * 跳转更新页面
     * @param powerId
     * @return
     */
    //@ResponseBody
    @RequestMapping("/toupdate")
    public String toUpdate(Integer powerId,Model model){
        //model.addAttribute("power", powerService.getById(powerId));
        return "back/power/update";
    }

    /**
     * 权限菜单修改
     * @param paramMap
     * @param response
     * @throws IOException
     */
    @RequestMapping("/update")
    public void update(@RequestParam Map paramMap,HttpServletResponse response) throws IOException{
        int update = powerService.update(paramMap);
        System.out.println("更新了。。。。。。。。。。。。。。。。。");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        if(update==-1)
            response.getWriter().print("修改失败！");
        else
            response.getWriter().print("<script>window.parent.parent.location.href=window.parent.parent.location.href; </script>");
    }

}
