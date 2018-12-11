package com.aaa.gjj.controller;

import com.aaa.gjj.service.FundExtretionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * className:FundExtretionDao
 * discription:
 * author:qcmecharts.js
 echarts-gl.js
 jquery-3.3.1.min.js
 * createTime:2018-12-08 16:22
 */
@Controller
public class FundExtretion {
    @Autowired
    private FundExtretionService fund;
    //贷款信息图
    @RequestMapping("/DaiKuanXinxi")
    @ResponseBody
    public Object DaiKuanXinxi(){
        List<Map> list = fund.DaiKuanXinxi();
        System.out.println("这里执行了。。。。");
        return list;
    }
    //还款信息图
    @ResponseBody
    @RequestMapping("/huanKuanXinxi")
    public Object huanKuanXinxi() {
        List<Map> list = fund.huanKuanXinxi();
        return list;

    }
    //每月提取
    @ResponseBody
    @RequestMapping("/meiYueTiqu")
    public Object meiYueTiqu() {
        List<Map> list = fund.meiYueTiqu();
        return list;

    }
    //每月汇缴金额
    @ResponseBody
    @RequestMapping("/meiYueJinE")
    public Object meiYueJinE() {

        List<Map> list = fund.meiYueJinE();
        return list;
    }
}
