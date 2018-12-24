package com.aaa.gjj.service;





import com.aaa.gjj.dao.FundExtretionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * className:FundExtretionImpl
 * discription:
 * author:qcm
 * createTime:2018-12-08 16:24
 */
@Service
public class FundExtretionServiceImpl implements FundExtretionService{


    @Autowired
    private FundExtretionDao fundExtretionDao;
    //每月汇缴金额
    @Override
    public List<Map> meiYueJinE() {
        return fundExtretionDao.meiYueJinE();
    }

    //每月提取
    @Override
    public List<Map> meiYueTiqu() {
        return fundExtretionDao.meiYueTiqu();
    }

    //还款信息图
    @Override
    public List<Map> huanKuanXinxi() {
        return fundExtretionDao.huanKuanXinxi();
    }

    //贷款信息图
    @Override
    public List<Map> DaiKuanXinxi() {
        return fundExtretionDao.DaiKuanXinxi();
    }
}
