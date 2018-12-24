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
        if(map!=null&&map.size()>0){
            //还款    修改数据
            int repayMoney = payService.repayMoney(map);
            //新增到还款记录表
            repayRecord(Integer.valueOf(map.get("repay_id")+""));
        }
        return 1;
    }

    /**
     * 通过查询还款表数据 把每期还款数据存到还款记录表
     * @param id
     */
    private void repayRecord(Integer id){
        //查询还款表
        Map map = (Map) payService.gettbRepay(id).get(0);
        if(map!=null&&map.size()>0){
            //新增到还款记录表
            payService.repayRecord(map);
        }
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

}
