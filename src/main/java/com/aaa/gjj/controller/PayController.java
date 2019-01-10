package com.aaa.gjj.controller;

import com.aaa.gjj.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * className:PayController
 * discription:  还款
 * author:lin
 * createTime:2018-12-19 16:28
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;

    /**
     * 还款
     * @return
     */
    @ResponseBody
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        Map resultMap = new HashMap();
        resultMap.put("pageData",payService.getPageByParam(map));
        resultMap.put("total",payService.getPageCount(map));
        return resultMap;
    }

    /**
     * 还款记录（还款页面）
     * @return
     */
    @ResponseBody
    @RequestMapping("/getRecord")
    public Object getRecord(@RequestBody Map map){
        //System.out.println("分页参数"+map);
        Map resultMap = new HashMap();
        resultMap.put("RecordData",payService.getRecord(map));
        System.out.println("返回数据"+resultMap);
        return resultMap;
    }

    /**
     * 还款记录表
     * @return
     */
    @ResponseBody
    @RequestMapping("/getHistory")
    public Object getHistory(@RequestBody Map map){
        //System.out.println("分页参数=="+map);
        Map resultMap = new HashMap();
        resultMap.put("getHistory",payService.getRecordByParam(map));
        resultMap.put("total",payService.getRecordCount(map));
        //System.out.println("分页数据=="+resultMap);
        return resultMap;
    }

    /**
     * 点击还款  修改还款数据
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/repayMoney")
    public Object repayMoney(@RequestBody Map map){
        System.out.println("收到的map==="+map);
        if(map!=null&&map.size()>0){
            //新增到还款记录表
            int result = repayRecord(Integer.valueOf(map.get("repay_id") + ""), map);
            //还款    修改数据
            int repayMoney = payService.repayMoney(map);
        }
        return 1;
    }

    /**
     * 通过查询还款表数据 把每期还款数据存到还款记录表
     * @param id
     */
    private int repayRecord(Integer id,Map mmm){
        //查询还款表
        Map map =  payService.gettbRepay(id).get(0);
        System.out.println("查到的map=="+map);
        if(map!=null&&map.size()>0){
            //新增到还款记录表
            int repay_state = Integer.valueOf(map.get("repay_state")+"") ;         //还款状态
            Double repay_interests = Double.valueOf(map.get("repay_interests")+"") ;       //应还利息 总
            Double repay_month_interest = Double.valueOf(map.get("repay_month_interest")+"") ;       //月还利息 总
            if(repay_state==3){
                Double over_interests = Double.valueOf(mmm.get("over_interests")+"") ;//逾期利息
                //map.put("repay_interests",repay_interests+over_interests);
                map.put("repay_month_interest",repay_month_interest+over_interests);

            }
            int result = payService.repayRecord(map);
            return result;
        }
        return 0;
    }

    /**
     * 贷款明细(分页)
     * @return
     */
    @ResponseBody
    @RequestMapping("/loanRecord")
    public Object loanRecord(@RequestBody Map map){
        Map resultMap = new HashMap();
        resultMap.put("pageData",payService.getRepay(map));
        resultMap.put("total",payService.getRepayCount(map));
        return resultMap;
    }

    /**
     * 逾期状态
     * @param map
     */
    @ResponseBody
    @RequestMapping("/overDate")
    public int overDa(@RequestBody Map map){
        int result = payService.updateStatus(map);
        return result;
    }

    /**
     * 逾期信息
     * @param map
     */
    @ResponseBody
    @RequestMapping("/getOverPay")
    public Map getOverPay(@RequestBody Map map){
        Map result = payService.getOverPay(map);
        return result;
    }

    /**
     * 逾期还款
     * @param map
     */
    @ResponseBody
    @RequestMapping("/overPay")
    public Map overPay(@RequestBody Map map){
        System.out.println("接受的参数==="+map);
        Map result = payService.getOverPay(map);
        System.out.println("返回的数据==="+result);
        return result;
    }

}
