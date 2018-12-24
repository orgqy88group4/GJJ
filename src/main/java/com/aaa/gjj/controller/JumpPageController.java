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
     * 跳转权限树添加页面
     * @return
     */
    @RequestMapping("/toadd")
    public String toadd(){
        return "back/power/add";
    }

    /**
     * 跳转更新页面
     * @return
     */
    @RequestMapping("/toupdate")
    public String toUpdate(){
        return "back/power/update";
    }

    /**
     * 跳转角色管理页面toaccount
     * @return
     */
    @RequestMapping("/torole")
    public String torole(){
        return "back/role/role";
    }

    /**
     * 跳转角色管理页面
     * @return
     */
    @RequestMapping("/toaccount")
    public String toaccount(){
        return "back/role/count";
    }

    /**
     * 跳转还款页面
     * @return
     */
    @RequestMapping("/topay")
    public String topay(){
        return "back/pay/pay";
    }
    /**
     * 跳转还款记录页面
     * @return
     */
    @RequestMapping("/topayRecord")
    public String topayRecord(){
        return "back/pay/payRecord";
    }

    /**
     * 跳转还款记录页面
     * @return
     */
    @RequestMapping("/toloadRecord")
    public String toloadRecord(){
        return "back/pay/loadRecord";
    }

    /**
     * 跳转角色登录
     * @return
     */
    @RequestMapping("/toLogin")
    public String loading(){
        return "login";
    }

}
