package com.aaa.gjj.controller;

import com.aaa.gjj.service.PowerService;
import com.aaa.gjj.service.QianTaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
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
    @Autowired
    private QianTaiService qianTaiService;

    //个人信息页面
    @RequestMapping("/PersonaInfo1")
    public String PersonaInfo1(){
        return "back/person/PersonDatiled";
    }
    //单位信息页面
    @RequestMapping("/UnitInfo1")
    public String UnitInfo1(){
        return "back/person/Unit_Informationed";
    }
    //年度贷款金额统计图
    @RequestMapping("/LoanInfoMap")
    public String LoanInfoMap2(){
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
    //封存、启封、销户 element
    @RequestMapping("/list")
    public String list(){
        return "back/person/list";
    }
    //人员转移
    @RequestMapping("/select1")
    public String PersonRemoveAudit1(){
        return "back/person/PersonTransfered";
    }
    //明细查询
    @RequestMapping("/Aetail_Enquiry1")
    public String Aetail_Enquiry1(){
        return "back/person/Aetail_Enquiryed";
    }
    //查看审批
    @RequestMapping("/Examine_and_approve1")
    public String Examine_and_approve1(){
        return "back/person/Examine_and_approved";
    }
    //个人抵押贷款
    @RequestMapping("/PersonLoan")
    public String PersonLoan(){
        return "back/loan/PersonLoan";
    }
    //贷款初审
    @RequestMapping("/LoanAudit")
    public String LoanAudit(){
        return "back/loan/LoanAudit";
    }
    //贷款终审
    @RequestMapping("/LoanAuditFinal")
    public String LoanAuditFinal(){
        return "back/loan/LoanAuditFinal";
    }
    //销户审核
    @RequestMapping("/HouseholdsAudit")
    public String HouseholdsAudi(){
        return "back/loan/HouseholdsAudit";
    }
    //封存启封审核
    @RequestMapping("/UnsealAudit")
    public String UnsealAudit(){
        return "back/loan/UnsealAudit";
    }
    //人员转移审核
    @RequestMapping("/PersonRemoveAudit")
    public String PersonRemoveAudit(){
        return "back/loan/PersonRemoveAudit";
    }
    //基数调整
    @RequestMapping("/jishutiaozheng")
    public String JiShuTiaoZheng(){
        return "back/company/jishutiaozheng";
    }
    //补缴办理
    @RequestMapping("/BuJiaoBanLi")
    public String BuJiaoBanLi(){
        return "back/company/BuJIaoBanLi";
    }
    //个人开户
    @RequestMapping("/personRegister")
    public String PersonRegister(){
        return "back/company/personRegister";
    }
    //汇缴页面
    @RequestMapping("/remit")
    public String remit(){
        return "back/company/Remit";
    }
    //挂账办理
    @RequestMapping("/guaZhangBanLi")
    public  String  guaZhangBanLi(){
        return "back/company/GuaZhangBanLi";
    }
    //比例变更
    @RequestMapping("/biLiBianGeng")
    public String biLiBianGeng(){
        return "back/company/BiliBinaGeng";
    }
    //明细查询
    @RequestMapping("/mXCXC")
    public  String  MXCX(){
        return "back/company/MXCX";
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

//    /**
//     * 跳转角色登录
//     * @return
//     */
//    @RequestMapping("/toLogin")
//    public String loading(){
//        return "login";
//    }
    /**
     * 跳转还款记录页面
     * @return
     */
    @RequestMapping("/overdue")
    public String overdue(){
        return "back/pay/overduePay";
    }
    /**
     * 跳转角色登录
     * @return
     */
    @RequestMapping("/toLogin")
    public String loading(){
        return "Login";
    }
    //=====================前台页面跳转==========================================
    //支付页面跳转
    @RequestMapping("pay")
    public String pay(@RequestParam Map map, HttpSession session){
        Object payables = map.get("payables");
        session.setAttribute("payables",payables);
        return "qiantai/paytwo";
    }

    /**
     * 跳转支付陈宫页面
     * @param session
     * @return
     */
    @RequestMapping("success")
    public String success(@RequestParam Map map, HttpSession session){
        return "qiantai/success";
    }
    @ResponseBody
    @RequestMapping("paypay")
    public Object paypay(HttpSession session){
        Object payables = session.getAttribute("payables");
        Object GRZH = session.getAttribute("GRZH");
        Map map=new HashMap();
        map.put("payables",payables);
        map.put("GRZH",GRZH);
        return map;
    }


    //前台首页
    @RequestMapping("/shouye1")
    public String ShouYe(){
        return "qiantai/shouye";
    }
    //个人登录
    @RequestMapping("/denglu")
    public String DengLu(){
        return "qiantai/denglu";
    }
    //个人登录进来
    @RequestMapping("/green")
    public String Green(){
        return "qiantai/green";
    }
    //个人登录进来缴纳记录
    @RequestMapping("/jiaona")
    public String JiaoCun(){
        return "qiantai/jiaona";
    }
    //个人登录进来贷款记录
    @RequestMapping("/daikuan")
    public String DaiKuan(){
        return "qiantai/daikuan";
    }
    //个人登录进来偿还贷款
    @RequestMapping("/huankuan")
    public String Huankuan(){
        return "qiantai/huankuan";
    }
    //单位登录
    @RequestMapping("/danwei")
    public String DanWei(){
        System.out.println("注销进来了：：：");
        return "qiantai/danwei";
    }
    //单位登录信息
    @RequestMapping("/dxinxi")
    public String DxinXi(){
        return "qiantai/dxinxi";
    }
    //单位登录缴纳
    @RequestMapping("/danjiaona")
    public String DanJiaoNa(){
        return "qiantai/danjiaona";
    }

    //单位缴纳成功
    @RequestMapping("/danjiaonaSuc")
    public String danjiaonaSuc(@RequestParam Map map){
        qianTaiService.DWHJUpdate(map);
        qianTaiService.DWHJUpdateXX(map);

        return "qiantai/danjiaonaSuc";
    }

    //单位登录缴纳记录
    @RequestMapping("/seldanjiao")
    public String DanJiaoNaJiLu(){
        return "qiantai/danjiaonajilu";
    }

    //政策法规
    @RequestMapping("/zhengce")
    public String ZhengCe(){
        return "qiantai/zhengce";
    }
    //政策法规内容
    @RequestMapping("/zhengce11")
    public String ZhengCe11(){
        return "qiantai/zhengce11";
    }
    //政策法规内容之小新闻
    @RequestMapping("/gongzuo11")
    public String GongZuo11(){
        return "qiantai/gongzuo11";
    }
    //==========================================================================
    //后台新闻模块
    @RequestMapping("/newsInfo")
    public String NewsInfo(){
        return "back/information/information";
    }

    /**
     * 跳转首页
     * @return
     */
    @RequestMapping("First")
    public String First(){
        return "back/power/MyHTML";
    }

}
