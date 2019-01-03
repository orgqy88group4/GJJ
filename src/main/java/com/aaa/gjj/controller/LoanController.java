package com.aaa.gjj.controller;

import com.aaa.gjj.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:LoanController
 * discription:
 * author:qcm
 * createTime:2018-12-18 11:03
 */
@Controller
@RequestMapping("/Loan")
public class LoanController {
    @Autowired
    private LoanService loanService;

    /**
     * 查询用户，判断该用户能不能贷款
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/getgrzh")
    public Object getgrzh(@RequestBody Map map){
        //先查询该账号在贷款记录表 有没有贷款
        List<Map> getgrzh = loanService.getgrzh();
        Map tempMap= new HashMap();
        //贷款数据库不空
        if(getgrzh!=null&&getgrzh.size()>0){
            for(Map map2:getgrzh){
                //将输入的个人账号 与贷款信息表个人账号比较 如果相等 跳出 反之 继续进行
                if(map2.get("GRZH").equals(map.get("grzh"))){
                    tempMap.put("infof", true);
                    tempMap.put("infos", "该账号不能进行重复贷款");
                    break;
                }else{
                    tempMap.put("infof", false);
                }
            }
            //贷款数据库为空  执行
        }else{
            tempMap.put("infof", false);
        }
        return tempMap;
    }

    @ResponseBody
    @RequestMapping("/user")
    public Object getLoanUser(@RequestBody Map map){
        List<Map> list = loanService.getList(map);
        System.out.println(list);
        return list;
    }

    /**
     * 共同借款人
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/together")
    public Object getTogether(@RequestBody Map map){
        System.out.println(map);
        List<Map> together = loanService.getTogether(map);
        System.out.println("together"+together);
        return together;
    }

    /**
     * 第四步提交按钮
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/save")
    public Object getSave(@RequestBody Map map){
        //System.out.println(map);
        int saveid = loanService.getInfo(map);
        //System.out.println("后台返回的值为："+saveid);
        return saveid;
    }

    /**
     * 贷款初审
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/page")
    public Object getLoanService(@RequestBody Map map) {
        System.out.println("贷款初审前台传来的值为："+map);
        Map tempMap = new HashMap();
        tempMap.put("pageDate",loanService.getPage(map));
        tempMap.put("total",loanService.getPageCount(map));
        System.out.println("贷款初审后台传来的值为："+tempMap);
        return tempMap;
    }

    /**
     * 点击详细按钮，前台传来个人账户，根据个人账户来查询详细信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/personInfo")
    public Object getPersonInfo(@RequestBody Map map){
        System.out.println("前台传来的值："+map);
        Map tempMap = new HashMap();
        tempMap.put("pageDate",loanService.getPersonInfo(map));
        System.out.println("后台的值："+tempMap);
        return tempMap;
    }

    /**
     * 初审点击通过按钮
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/passCheck")
    public Object passCheck(@RequestBody Map map){
        System.out.println("前台传来的值："+map);
        int i = loanService.PassCheck(map);
        System.out.println("后台插入成功");
        if (i==1){
            int loan_id = loanService.PassChangeState(map);
            return loan_id;
        }else {
            return 0;
        }
    }

    /**
     * 初审驳回
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/reject")
    public Object Reject(@RequestBody Map map){
        System.out.println("初审驳回前台传来的值："+map);
        int reject = loanService.reject(map);
        System.out.println("插入后台成功，返回="+reject);
        if (reject==1){
            return loanService.PassChangeState(map);
        }else {
            return 0;
        }
    }

    /**
     * 贷款终审页面
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/finalLoan")
    public Object Finallyloan(@RequestBody Map map){
        System.out.println("贷款终审前台传来的值："+map);
        Map tempMap = new HashMap();
        tempMap.put("pageDate",loanService.getPageFinal(map));
        tempMap.put("total",loanService.getPageFinalCount(map));
        System.out.println("贷款终审后台返回的值："+tempMap);
        return tempMap;
    }

    /**
     * 确认驳回按钮
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/sureReject")
    public Object SureReject(@RequestBody Map map){
        return loanService.SureReject(map);
    }

    /**
     * 终审通过
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/finalPass")
    public Object FinalPass(@RequestBody Map map){
        System.out.println("贷款终审前台传来的值："+map);
        int i = loanService.FinalPass(map);
        System.out.println("finalpass====="+i);
        //新增到还款表
        insertRepay(Integer.valueOf(map.get("Loan_id")+""));
        //修改个人账户余额
        updateGRZHDanlance(map.get("GRZH")+"");
        if (i == 1){
            int passFinal = loanService.passFinal(map);
            return passFinal;
        }
        return 0;
    }

    /**
     * 终审通过将贷款数据新增到贷款表
     * @param id
     */
    private void insertRepay(Integer id){
        System.out.println("id===="+id);
        //获取还款表信息
        Map map = (Map) loanService.getrepayinfo(id).get(0);
        System.out.println("map===="+map);
        if(map!=null&&map.size()>0){
            System.out.println("进入了这里");
            int i = loanService.insertRepay(map);
            System.out.println("这里有值吗："+i);
        }
    }
    /**
     * 终审通过  修改账户余额
     * @param GRZH
     */
    private void updateGRZHDanlance(String GRZH){
        System.out.println("GRZH======"+GRZH);
        Map map = loanService.updateGRZHBalance(GRZH).get(0);
        if(map!=null&&map.size()>0){
            //修改账户余额
            loanService.updateBalance(map);
        }
    }
    /**
     * 销户审核
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/AccountAudit")
    public Object AccountAudit(@RequestBody Map map){
        System.out.println("销户审核前台传来的值："+map);
        Map tempMap = new HashMap();
        tempMap.put("pageDate",loanService.QueryAuditInfomation(map));
        tempMap.put("total",loanService.QueryAuditInfomationCount(map));
        System.out.println("销户审核后台返回的值："+tempMap);
        return tempMap;
    }

    /**
     * 后台审核确认销户
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/AccountAuditPass")
    public Object AccountAuditPass(@RequestBody Map map){
        System.out.println("销户审核通过按钮前台传来的值："+map);
        int i = loanService.PersonAccountState(map); //修改个人账户的状态  1为正常 2为封存 0为销户
        if (i==1){
            loanService.unsealAuditUpdate(map);  // 修改审核表中的状态tb_unseal_audit  修改审核表中的audit_name字段，给位通过
            loanService.unitsUpdate(map);//修改公司缴存人数
            if (map.get("state").equals("2")||map.get("state").equals("0")){//1 为启封  2为封存  0为销户
                loanService.unitsUpdateMoney(map); //销户，封存 修改公司缴存总金额
            }
            if(map.get("state").equals("1")){
                loanService.unitsMoney(map);
            }
        }
        return null;
    }

    /**
     * 销户驳回
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/HouseholdsToDismiss")
    public Object HouseholdsToDismiss(@RequestBody Map map){
        System.out.println("封存驳回前台传来的值："+map);
        return loanService.unsealAuditUpdate(map);
    }

    /**
     * 封存启封审核页面
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/qifengfengcunshenhe")
    public Object QiFengFengCunShenHe(@RequestBody Map map){
        System.out.println("启封封存前台传来的值："+map);
        Map tempMap = new HashMap();
        tempMap.put("pageDate",loanService.unsealAuditSelect(map));
        tempMap.put("total",loanService.unsealAuditSelectCount(map));
        return tempMap;
    }

    /**
     * 人员转移后台审核
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/personRemoveAutid")
    public Object PersonRemoveAutid(@RequestBody Map map){
        System.out.println("人员转移前台传来的值："+map);
        Map tempMap = new HashMap();
        tempMap.put("pageDate",loanService.uditTransfer(map));
        tempMap.put("total",loanService.uditTransferCount(map));
        System.out.println("后台返回前台的值："+tempMap);
        return tempMap;
    }

    /**
     * 人员转移审核驳回
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/RejectionOfLoanReview")
    public Object RejectionOfLoanReview(@RequestBody Map map){
        System.out.println("人员转移审核驳回前台传来的值："+map);
        loanService.CheckAuditTable(map);//查询审核表中的记录，插入审核记录表中
        int i = loanService.RejectDelete(map);
        return i;
    }

    /**
     * 人员转移审核通过按钮
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/RejectionOfLoanPass")
    public Object RejectionOfLoanPass(@RequestBody Map map){
        System.out.println("人员转移审核通过前台传来的值："+map);
        int rejects = loanService.rejects(map);//查询出要转入的公司
        //List<Map> list = loanService.CheckAuditTable(map);//查询审核表中的记录，插入审核记录表中
        if (rejects==1){
            return 1;
        }else {
            return 0;
        }
    }
}
