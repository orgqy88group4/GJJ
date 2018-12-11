package com.aaa.gjj.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:FundExtretionDao
 * discription:
 * author:qcm
 * createTime:2018-12-08 16:23
 */
public interface FundExtretionDao {
    //贷款图
    @Select("select sum(loan_money) as money ,LEFT(ctime,7) as yuefen FROM tb_repay  GROUP BY MONTH(ctime)")
    //@Select("select sum(loan_money) as money ,substr(ctime,1,7) as yuefen FROM tb_repay4  GROUP BY substr(ctime,1,7)")
    List<Map> DaiKuanXinxi();
    //还款图
    @Select("select sum(repay_month_money) as money ,LEFT(repayed_date,7) as yuefen FROM tb_repay_record  GROUP BY MONTH(repayed_date)")
    List<Map> huanKuanXinxi();
    //每月提取Dao
    @Select("select sum(extract_money) as money , LEFT(appl_time,7) as yuefen FROM tab_extract_application  GROUP BY MONTH(appl_time)")
    List<Map> meiYueTiqu();
    //每月汇缴金额
    @Select("select sum(uaOweMonery) as money , LEFT(ubdCreateDate,7) as yuefen FROM tb_unitbizdetail GROUP BY MONTH(ubdCreateDate)")
    List<Map> meiYueJinE();
}
