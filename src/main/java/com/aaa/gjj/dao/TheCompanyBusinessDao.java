package com.aaa.gjj.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;
/**
 * className:TheCompanyBusinessDao
 * discription:
 * author:qcm
 * createTime:2018-12-10 21:03
 */
public interface TheCompanyBusinessDao {
    /**
     * 获取单位账号信息表分页数据
     * @param map
     * @return
     */
    @Select("<script>select a.DWZH,b.uname,a.uDepositRatio,a.uPersonRatio,a.uDepositedPnum," +
            "a.uARemain,a.uaOweMonery,a.uaState,a.uaPayEndDate " +
            " from tb_unitaccount a,tb_unit b where a.uid = b.uid and uaState = 1 " +
            "<if test=\"uname!=null and uname!=''\"> and b.uname like  concat('%',#{uname},'%')</if>" +
            "limit #{start},#{end}</script>")
    List<Map> UnitAccountInformation(Map map);
    /**
     * 获取单位账号信息总数量
     * @param map
     * @return
     */
    @Select("<script>select count(*) as cnt from tb_unitaccount a,tb_unit b where a.uid = b.uid " +
            "<if test=\"uname!=null and uname!=''\"> and b.uname like concat('%',#{uname},'%')</if></script>")
    List<Map> UnitAccountInformationCount(Map map);
    /**
     * 汇缴时判断本月是否已经汇缴过了,查询明细查询表中当前单位汇缴的最近一个时间
     */
    @Select("<script>select max(ubdCreateDate) time from tb_unitbizdetail where dwzh = #{DWZH}</script>")
    List<Map> getRemitTime(Map map);

    /**
     * 汇缴办理完成之后把汇缴的信息存到明细查询表里，汇缴之后单位账户里的余额会减少，个人账户余额会增加
     */

    //汇缴办理完成之后更新单位账户的余额
    @Update("update tb_unitaccount set uARemain=#{uARemain}-#{uaOweMonery},uaPayEndDate =concat(DATE_FORMAT(now(),'%Y-%m-%d')) \n" +
            "where DWZH =#{DWZH}")
    int updateRemit(Map map);
    //汇缴办理完成之后把汇缴的信息存到明细查询表里 ,得到明细查询新增的数据对应的id
    @Select("select uARemain,uDepositedPnum,YWBLR,uaPayEndDate,uid,DWZH,uaOweMonery from tb_unitaccount where DWZH =#{DWZH}")
    Map getDetailList(Map map);
    //插入    session获取管理员
    @Insert("insert into tb_unitbizdetail (ubdAccrual,ubdPopulation,ubdDetailType,ubdSettleStates," +
            "ubdCreater,ubdCreateDate,ubdRemark,uaID,hjys,dwzh,uaOweMonery,ubdPayWay) " +
            "values(#{uARemain},#{uDepositedPnum},'汇缴',1,#{YWBLR},DATE_FORMAT(#{uaPayEndDate},'%Y-%m-%d'),#{c}," +
            "#{uid},#{hjys},#{DWZH},#{uaOweMonery},'均缴')")
    int insertDtail(Map map);
    //单位汇缴之后，个人账户余额会增加 汇缴办理之后单位人员的汇缴状态会变成已汇缴
    @Update("update tb_paccountutil set dalance=(dalance+yDrawAmt),yinterestBal = 1  " +
            "where uaid=(select uid from tb_unitaccount where DWZH = #{DWZH}) and yinterestBal = 0 and peraccState = 1")
    int updateState(Map map );
    /**
     * 获取明细表分页数据
     * @param map
     * @return
     */
    @Select("<script>SELECT  a.DWZH,b.uname,a.ubdAccrual,a.ubdPopulation,a.ubdDetailType,a.hjys,a.ubdPayWay,a.uaOweMonery," +
            "a.ubdSettleStates,a.ubdCreater,a.ubdCreateDate FROM tb_unitbizdetail a,tb_unit b WHERE a.uaid=b.uid " +
            "<if test=\"uname!=null and uname!=''\"> and b.uname like  concat('%',#{uname},'%')</if>" +
            "limit #{start},#{end}</script>")
    List<Map> MXCXBiao(Map map);

    /**
     * 获取明细信息总数量
     * @param map
     * @return
     */
    @Select("<script>SELECT  count(*) cnt FROM tb_unitbizdetail a,tb_unit b WHERE a.uaid=b.uid " +
            "<if test=\"uname!=null and uname!=''\"> and b.uname like concat('%',#{uname},'%')</if></script>")
    List<Map>MXCXBiaoCnt(Map map);
    /**
     * 比例变更时，查找员工的信息传到前台
     * @param map
     * @return
     */
    @Select("select a.*,b.tb_pName from tb_paccountutil a join tb_person_info b on a.pid = b.tb_pid " +
            "where uaid = (select uid from tb_unitaccount where DWZH = #{DWZH}) and a.peraccState=1")
    List<Map> getStaffInfo(Map map);
    @Select("<script>SELECT  count(*) cnt FROM tb_paccountutil a,tb_person_info b WHERE a.pid = b.tb_pid " +
            "<if test=\"uname!=null and uname!=''\"> and b.uname like concat('%',#{uname},'%')</if></script>")
    List<Map> GETStaffInfoCnt(Map map);
    /**
     * 比例变更
     */
    //查询单位账号对应的单位id
    //select uid from tb_unitaccount where DWZH =
    //list
    //根据单位id修改个人账户中的比例和基数
    @Update("update tb_paccountutil set ubitNrop = #{uDepositRatio},indiNrop = #{uPersonRatio}, " +
            "unitMonPaysum=baseNummber*ubitNrop*0.01,perMonPaysum=baseNummber*indiNrop*0.01," +
            "yDrawAmt=unitMonPaysum+perMonPaysum where uaid = (select uid from tb_unitaccount where DWZH= #{DWZH})")
    int  updatePRatio(Map map);
    //修改单位账户中的比例和基数和应缴纳金额

    @Update("update tb_unitaccount set uDepositRatio = #{uDepositRatio},uPersonRatio=#{uPersonRatio}," +
            "uaOweMonery = (select SUM(yDrawAmt)from tb_paccountutil where uaid =#{c}) where uid =#{c}")
    int updateURatio(Map map);
    //查询单位账号对应的单位id
    @Select("select uid from tb_unitaccount where DWZH =#{DWZH}")
    List<Map> list(Map map);

    //修改个人账号表中的缴纳基数和 单位和个人缴纳之和
    @Update("update  tb_paccountutil set baseNummber =#{baseNummber},yDrawAmt =#{unitMonPaysum}+#{perMonPaysum},unitMonPaysum=#{unitMonPaysum},perMonPaysum=#{perMonPaysum} where GRZH =#{GRZH}")
    int updatePaccountutil(Map map);
    @Update("update tb_unitaccount set uaOweMonery=(select SUM(yDrawAmt) from tb_paccountutil where uaid = #{uid}) where DWZH = #{DWZH}")
    int updateMoney1(Map map);
    /**
     * 挂账办理业务
     * @param map
     * @return
     */
    @Update("update tb_unitaccount set uARemain=uARemain+#{gzje} where DWZH=#{DWZH}")
    int GZBLyewuD(Map map);
}
