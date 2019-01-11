package com.aaa.gjj.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:CompanyDao
 * discription:
 * author:qcm
 * createTime:2018-12-21 14:59
 */
public interface CompanyDao {
    /**
     * 基数调整页面
     * @param map
     * @return
     */
    @Select("<script>select a.DWZH,b.uname,a.uDepositRatio,a.uPersonRatio," +
            "a.uDepositedPnum,a.uARemain,a.uaOweMonery,a.uaState,a.uaPayEndDate " +
            "from tb_unitaccount a,tb_unit b where a.uid = b.uid and uaState = 1 " +
            "<if test=\"uname!=null and uname!=''\"> and uname like concat('%',#{uname},'%')</if>" +
            " limit #{start},#{end}</script>")
    List<Map> UnitAccountInformation(Map map);

    /**
     * 基数调整页面总数量
     * @param map
     * @return
     */
    @Select("<script>select count(*) as count from tb_unitaccount a,tb_unit b where a.uid = b.uid and uaState = 1 " +
            "<if test=\"uname!=null and uname!=''\"> and uname like concat('%',#{uname},'%')</if></script>")
    List<Map>UnitAccountInformationCount(Map map);

    /**
     * 点击比例变更按钮，前台传来单位账号
     * @param map
     * @return
     */
    @Select("<script>select a.*,b.tb_pName from tb_paccountutil a join tb_person_info b on a.pid = b.tb_pid where uaid = (select uid from tb_unitaccount where DWZH = #{DWZH}) and a.peraccState=1" +
            "<if test=\"tb_pName!=null and tb_pName!=''\"> and tb_pName like concat('%',#{tb_pName},'%')</if></script>")
    List<Map> getStaffInfo(Map map);

    /**
     * 基数调整，保存调整按钮
     * @param map
     * @return
     */
    @Select("select uid from tb_unitaccount where DWZH =#{DWZH}")
    List<Map> updateMoney(Map map);

    /**
     * 修改个人账号表中的缴纳基数和 单位和个人缴纳之和
     * @param map
     * @return
     */
    @Update("update tb_paccountutil set baseNummber = #{baseNummber},yDrawAmt =#{unitMonPaysum}+#{perMonPaysum},unitMonPaysum=#{unitMonPaysum},perMonPaysum=#{perMonPaysum} where GRZH = #{GRZH}")
    int updateMoney1(Map map);

    /**
     * 修改单位账户中的月应缴纳金额
     * @param map
     * @return
     */
    @Update("update tb_unitaccount set uaOweMonery=(select SUM(yDrawAmt) from tb_paccountutil where uaid = #{uid}) where DWZH = #{DWZH}")
    int updateMoney2(Map map);

    /**
     * 补缴办理页面，补缴办理按钮
     * @param map
     * @return
     */
    @Select("select	 count(*) as payPerson from tb_paccountutil where yinterestBal =0 and peraccState = 1 and uaid = (select uid from tb_unitaccount where DWZH = #{DWZH})")
    List<Map> getUnitPayInfo(Map map);

    /**
     * 补缴办理人员信息
     * @param map
     * @return
     */
    @Select("<script>select a.GRZH,a.dalance,a.peraccState,a.ubitNrop,a.indiNrop,a.unitMonPaysum,a.perMonPaysum,a.yinterestBal,b.tb_pName from tb_paccountutil a join tb_person_info b on a.pid = b.tb_pid where uaid =(select uid from tb_unitaccount  where DWZH = #{DWZH}) and a.yinterestBal = 0 and peraccState = 1" +
            "<if test=\"tb_pName!=null and tb_pName!=''\"> and tb_pName like concat('%',#{tb_pName},'%')</if></script>")
    List<Map> getPersonPayInfo(Map map);

    /**
     * 补缴按钮
     * @param map
     * @return
     */
    @Select("select yDrawAmt from tb_paccountutil where GRZH =#{GRZH}")
    List<Map> updatePersonInfo(Map map);

    /**
     * 批量补缴按钮
     * @param GRZH
     * @return
     */
    @Select("select yDrawAmt from tb_paccountutil where GRZH =#{GRZH}")
    List<Map> updatePersonInfo99(int GRZH);

    /**
     * 补缴按钮
     * @param map
     * @return
     */
    @Update("update tb_unitaccount set uARemain=uARemain-#{yDrawAmt} where uid =(select uaid from tb_paccountutil where GRZH =#{GRZH})")
    int updatePersonInfo1(Map map);

    /**
     * 补缴按钮
     * @param map
     * @return
     */
    @Update("update tb_paccountutil set dalance=dalance+unitMonPaysum+perMonPaysum,yinterestBal=1 where GRZH =#{GRZH}")
    int updatePersonInfo2(Map map);

    /**
     * 批量补缴
     * @param GRZH
     * @return
     */
    @Update("update tb_paccountutil set dalance=dalance+unitMonPaysum+perMonPaysum,yinterestBal=1 where GRZH =#{GRZH}")
    int updatePersonInfo3(int GRZH);

    /**
     *根据单位账号，查找单位名称、缴纳的比例
     * @param DWZH
     * @return
     */
    @Select("select b.uname,a.uDepositRatio,a.uPersonRatio from tb_unitaccount a join tb_unit b on a.uid = b.uid where DWZH =#{DWZH}")
    List<Map> getUnitInfo(@Param("DWZH") String DWZH);

    /**
     * 个人注册，点击保存按钮，把信息存储到数据库
     * @param map
     * @return
     */
    @Insert("insert into tb_person_info values ( null,#{tb_pName},#{tb_pIphone},#{tb_pSex},#{tb_idcard},#{tb_idNUmber},concat(DATE_FORMAT(#{tb_pDate},'%Y/%m/%d')),#{tb_pMarriage},#{tb_Profession},#{tb_pEducation},#{tb_pEmail},#{tb_pAddress},#{DWZH},'良好')")
    int addPersonInfo(Map map);

    /**
     * 查询个人信息表中的主键
     * @return
     */
    @Select("select max(tb_pid) from tb_person_info")
    int addPersonInfo1();

    /**
     * 得到单位id ，应缴纳金额
     * @param map
     * @return
     */
    @Select("select uid,uaOweMonery from tb_unitaccount where DWZH = #{DWZH}")
    List<Map> addPersonAccountInfo(Map map);

    /**
     * 把信息添加到账户信息表
     * @param map
     * @return
     */
    @Insert("insert into tb_paccountutil values (null,#{uid},concat(DATE_FORMAT(now(),'%Y-%m-%d')),0,1,#{baseNummber},#{uDepositRatio},#{uPersonRatio},concat(DATE_FORMAT(now(),'%Y-%m-%d')),#{baseNummber}*#{uDepositRatio}*0.01,#{baseNummber}*#{uPersonRatio}*0.01,'注册成功',#{baseNummber}*#{uDepositRatio}*0.01+#{baseNummber}*#{uPersonRatio}*0.01,0,#{paOp},null,concat(lpad(floor(RAND()*100000),8,0)),#{pid},#{KHYH},#{YHZH},concat(lpad(floor(RAND()*100000),8,0)))")
    int addPersonAccountInfo1(Map map);

    /**
     *个人注册完成后，单位账号里面的单位缴存人数应该发生变化
     * @param map
     * @return
     */
    @Update("update tb_unitaccount set uDepositedPnum = (select count(*) from tb_paccountutil where uaid = #{uid} and peraccState = 1) where uid = #{uid}")
    int addPersonAccountInfo2(Map map);

    /**
     * 得到当前人员注册完成后单位应缴纳金额更新
     * @param map
     * @return
     */
    @Update("update tb_unitaccount set uaOweMonery =(select SUM(yDrawAmt) from tb_paccountutil where pid in (select tb_pid from tb_person_info where unit_Id = #{DWZH}) ) where DWZH = #{DWZH}")
    int addPersonAccountInfo3(Map map);

    /**
     * 公司名称唯一效验
     * @return
     */
    @Select("select uname,uNetworkCode from tb_unit")
    List<Map> getUnitInfoList();

    /**
     * 公司注册添加信息
     * @param map
     * @return
     */
    @Insert("insert into tb_unit values ( null,#{uname},#{uAddress},#{uLegalPerson},#{ULegalType},#{ULegalCard}," +
            "#{uPayDate},#{uOperator},#{uOpIphone},#{JBRZJLX},#{JBRZJHM},#{UnitBeginDate},concat(DATE_FORMAT(now(),'%Y%m'),lpad(floor(RAND()*100),4,0))," +
            "#{GSLX},#{SSHY},#{LSGX},#{JJLX},#{YYZZ},#{uCreater},null);")
    int unitRegister(Map map);

    /**
     * 添加公司信息
     * @return
     */
    @Select("select max(uid) from tb_unit")
    int unitRegister1();

    /**
     * 单位注册时的账户信息
     * @param map
     * @return
     */
    @Insert("insert into tb_unitaccount (uaID,uaBankName,uaBankNumber,uDepositRatio," +
            "uPersonRatio,STYH,uaState,YWBLR,KHRQ,uDepositedPnum," +
            "uSealPnum,uARemain,uACancellDate,uacReason,uaPayEndDate," +
            "uaOweMonery,uaOweMonths,uaTebalance,DWZH,uid) values ( null,#{uaBankName}," +
            "#{uaBankNumber},#{uDepositRatio},#{uPersonRatio},#{STYH},#{uaState1}," +
            "#{YWBLR},date_format(now(),'%Y-%m-%d'),0,0,0,null,null,null,0,0," +
            "null,concat(DATE_FORMAT(now(),'%Y%m'),lpad(floor(RAND()*100000),10,0)),#{uid})")
    int unitAccount(Map map);
}
