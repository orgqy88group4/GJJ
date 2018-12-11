package com.aaa.gjj.service;

import java.util.List;
import java.util.Map;

/**
 * className:FundExtretionDao
 * discription:
 * author:qcm
 * createTime:2018-12-08 16:24
 */
public interface FundExtretionService {
    //贷款信息图
    List<Map> DaiKuanXinxi();
    //还款信息图
    List<Map> huanKuanXinxi();
    //每月提取Service
    List<Map> meiYueTiqu();
    //每月汇缴金额
    List<Map> meiYueJinE();
}
