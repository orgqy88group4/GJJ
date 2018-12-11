package com.aaa.gjj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * className:JumpPageController
 * discription:
 * author:qcm
 * createTime:2018-12-10 11:15
 */
@Controller
@RequestMapping("/Page")
public class JumpPageController {
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
}
