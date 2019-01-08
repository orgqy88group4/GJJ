package com.aaa.gjj.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * className:QianTaiDao
 * discription:
 * author:qcm
 * createTime:2018-12-26 20:27
 */
public interface QianTaiDao {
    /**
     * 个人账号登录前台
     * @param map
     * @return
     */
    @Select("select b.tb_pName,b.tb_pSex,a.GRZH,b.tb_pIphone,b.tb_Profession,a.dalance,b.tb_pAddress,b.tb_pEducation from tb_paccountutil a,tb_person_info b where a.pid=b.tb_pid and GRZH=#{grzh} and GRMM=#{grmm}")
    List<Map> ChackPersonLogin(Map map);

    /**
     * 查询缴存记录
     * @param map
     * @return
     */
    @Select("select GRZH,openDate,dalance,yinterestBal,perMonPaysum from tb_paccountutil where GRZH=#{GRZH}")
    List<Map> SelectCheckJiLu(Map map);

    /**
     * 查询个人贷款
     * @param map
     * @return
     */
    @Select("select GRZH,pName,loan_money,loan_periods,ctime,residue_periods,repay_money,residue_money,repay_all_mmonth,repay_month_money,repay_month_interest from tb_repay  where GRZH=#{GRZH}")
    List<Map> SelectCheckDaiKuanJiLu(Map map);

    /**
     * 单位账号登录前台
     * @param map
     * @return
     */
    @Select("select b.ULegalCard,a.uaOweMonery,a.DWZH,b.uname,a.uARemain,b.GSLX,b.uAddress,b.uLegalPerson,b.uOperator,b.uOpIphone,b.UnitBeginDate from tb_unitaccount a,tb_unit b where a.uid=b.uid and DWZH=#{DWXZ} and ULegalCard=#{ULegalCard}")
    List<Map> ChackUnitLogin(Map map);
    /**
     * 单位账号登录前台
     * @param map
     * @return
     */
    @Select("select b.ULegalCard,a.uaOweMonery,a.DWZH,b.uname,a.uARemain,b.GSLX,b.uAddress,b.uLegalPerson,b.uOperator,b.uOpIphone,b.UnitBeginDate from tb_unitaccount a,tb_unit b where a.uid=b.uid and DWZH=#{DWXZ}")
    List<Map>ChackUnitLogin1(Map map);

    /**
     * 单位账号缴纳记录
     * @param map
     * @return
     */
    @Select("select i.uname,t.uaOweMonery,l.ubdDetailType,l.ubdAccrual,l.ubdPayWay,t.uaPayEndDate from tb_unit i join tb_unitaccount t join tb_unitbizdetail l on i.uid=t.uid and t.uaID=l.uaId and t.DWZH=#{DWZH}")
    List<Map> UnitJiaoNaJiLu(Map map);

    /**
     * 单位汇缴后更新数据
     */
    @Update("update tb_unitaccount set uARemain= uARemain - #{dwyjce} ,uaPayEndDate=date_format(NOW(),'%Y-%m-%d') where DWZH=#{DWZH}")
    int DWHJUpdate(Map map);
    //单位汇缴之后，个人账户余额会增加 汇缴办理之后单位人员的汇缴状态会变成已汇缴
    @Update("update tb_paccountutil set dalance=dalance+#{dwyjce},yinterestBal = 1  " +
            "where uaid=(select uid from tb_unitaccount where DWZH = #{ DWZH}) and yinterestBal = 0 and peraccState = 1")
    int updateState(Map map);
    /**
     * 单位汇缴后更新详细表数据
     */
    @Update("update tb_unitbizdetail set ubdAccrual= ubdAccrual - #{dwyjce} ,ubdCreateDate=date_format(NOW(),'%Y-%m-%d') where dwzh=#{DWZH}")
    int DWHJUpdateXX(Map map);


    /**
     * 查看单个小新闻
     * @param id
     * @return
     */
    @Select("select * from tb_inform where id = #{id}")
    List<Map> Selnews(@Param("id") int id);

    //=========================后台新闻模块=====================================

    /**
     * 查询新闻信息
     * @param map
     * @return
     */
    @Select("select * from tb_inform limit #{start},#{end}")
    List<Map> informationList(Map map);

    /**
     * 查询新闻总数量
     * @param map
     * @return
     */
    @Select("select count(*) as count from tb_inform")
    List<Map> informationListCount(Map map);

    /**
     * 修改新闻
     * @param map
     * @return
     */
    @Update("update tb_inform set title = #{title},details = #{details},informType = #{informType},informStatus = #{informStatus} where id= #{id}")
    int upInformation(Map map);

    /**
     * 新闻添加
     * @param map
     * @return
     */
    @Insert("insert into tb_inform (title,details,informType,informStatus) values(#{title},#{details},#{informType},#{informStatus})")
    int information(Map map);

    /**
     * 新闻删除
     * @param id
     * @return
     */
    @Delete("delete from tb_inform where id=#{id}")
    int delInformation(@Param("id") int id);

    /**
     * 把后台发布的新闻传输到前台
     * @param tid
     * @return
     */
    @Select("select * from tb_inform where informStatus=#{tid}")
    List<Map> selnewgall(@Param("tid") int tid);
    /**
     * 核实银行卡信息是否正确
     */
    @Select(value = "select * from pay where card_account=#{card_account} and pay_password=#{pay_password}")
    List<Map> YHK(Map map);


    /**
     * 前台个人还款后更新数据
     */
    @Update("update tb_repay set repay_money = repay_money+#{repay_month_money},repayed_interests=repayed_interests+#{repay_month_interest},repayed_All_money=repayed_All_money+#{payables},residue_money=residue_money-#{repay_month_money},residue_interests=residue_interests-#{repay_month_interest}," +
            "repayed_period=repayed_period+1,residue_periods=residue_periods-1,repayed_date=NOW() where GRZH=#{GRZH}")
    int XGSJ(Map map);
}
