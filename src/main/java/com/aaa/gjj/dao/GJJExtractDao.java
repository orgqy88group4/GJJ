package com.aaa.gjj.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:GJJExtractDao
 * discription:
 * author:dujihu
 * createTime:2018-12-13 14:47
 */
public interface GJJExtractDao {

    /**
     * 公积金普通提取带参分页查询
     * @param map
     * @return
     * 如果使用注解的方式 动态sql必须在<script></script>里
     * 如果使用<script></script>标签 mybatis的大于小于 要使用&gt; &lt;
     */
    @Select("<script>select a.*,b.*,c.*,d.*,e.* from \n" +
            " tb_paccountutil b,tb_unitaccount c,tb_unit d,tb_person_info a left join tb_loanapproval e on a.tb_pid = e.pid  where a.tb_pid = b.pid and a.unit_Id=c.DWZH and c.uid=d.uid \n" +
            "and peraccState = 1   " +
            "<if test=\"GRZH!=null and GRZH!=''\"> and b.GRZH=#{GRZH}</if>" +
            "<if test=\"tb_pName!=null and tb_pName!=''\"> and tb_pName like concat('%',#{tb_pName},'%')</if>" +
            " limit #{start},#{end} </script>")

    List<Map> getPageByparam(Map map);

    /**
     * 公积金普通提取查询分页总数量
     * @param map
     * @return
     */
    @Select("<script> select count(*) from tb_paccountutil b,tb_unitaccount c,tb_unit d ,tb_person_info a left join tb_loanapproval e on a.tb_pid = e.pid   <where> a.tb_pid = b.pid and a.unit_Id=c.DWZH and c.uid=d.uid and peraccState = 1" +
            "<if test=\"GRZH!=null and GRZH!=''\"> and b.GRZH=#{GRZH}</if>" +
            "<if test=\"tb_pName!=null and tb_pName!=''\"> and tb_pName like concat('%',#{tb_pName},'%')</if>" +
            " </where></script>")

    int getPageCount(Map map);

    /**
     * 添加提取申请
     * @param
     * @return
     */
    @Insert("insert into tab_extract_application (pre_account,appl_name,appl_bank_name,appl_bank_account,application_type,application_way,appl_idnum,comp_name,comp_account,extract_money,bailor_name,bilor_idnum,extract_reason,renting_address,renting_compact,renting_type,renting_money,house_acreage,house_part,house_address,house_money,appl_time,appl_state) values (" +
            "#{GRZH},#{tb_pName},#{applyName},#{YHZH},#{tiqutype},#{tiquway},#{tb_idNUmber},#{uname},#{unit_Id},#{appDalance},#{emClientName},#{emClientCard},#{tiquCause},#{houseAddr},#{houseNum},#{houseType},#{housePay},#{houseAcreage},#{housePart},#{houseAddr},#{houseValue},NOW(),1)")
    int addExtract(Map map);

    /**
     * 添加销户提取申请
     * @param
     * @return
     */
    @Insert("insert into tab_extract_application (pre_account,appl_name,appl_bank_name,appl_bank_account,application_type,application_way,appl_idnum,comp_name,comp_account,extract_money,bailor_name,bilor_idnum,extract_reason,renting_address,renting_compact,renting_type,renting_money,house_acreage,house_part,house_address,house_money,appl_time,appl_state) values (" +
            "#{GRZH},#{tb_pName},#{applyName},#{YHZH},#{tiqutype},#{tiquway},#{tb_idNUmber},#{uname},#{unit_Id},#{appDalance},#{emClientName},#{emClientCard},4,#{houseAddr},#{houseNum},#{houseType},#{housePay},#{houseAcreage},#{housePart},#{houseAddr},#{houseValue},NOW(),1)")
    int addCloseExtract(Map map);
    /**
     * 添加约定提取申请
     * @param
     * @return
     */
    @Insert("insert into tb_agreed_to_extract (pName,pIphone,pcNUmber,GRZH,dalance,peraccState,loan_money,loan_periods,loan_repay,loan_rate,peraccState1,ydrq,hkje,ydqs,dklx,shijian,SHZT) values " +
            "(#{tb_pName},#{tb_pIphone},#{tb_idNUmber},#{GRZH},#{dalance},#{peraccState},#{loan_money},#{loan_periods},'等额本息',#{loan_rate},#{Loan_id},#{appointTime},#{refund_limit},#{ydqs},#{loanType},NOW(),1)")
    int addAppointExtract(Map map);


    /**
     * 公积金约定提取带参分页查询
     * @param map
     * @return
     */
    @Select("<script>select a.*,b.* from \n" +
            " tb_agreed_to_extract a left join tb_repay b on a.peraccState1 = b.Loan_id  where 1=1 \n" +
            "<if test=\"GRZH!=null and GRZH!=''\"> and a.GRZH=#{GRZH}</if>" +
            "<if test=\"pName!=null and pName!=''\"> and a.pName like concat('%',#{pName},'%')</if>" +
            " limit #{start},#{end} </script>")

    List<Map> getAppointPageByparam(Map map);

    /**
     * 公积金约定提取查询分页总数量
     * @param map
     * @return
     */
    @Select("<script> select count(*) from tb_agreed_to_extract a left join tb_repay b  on a.peraccState1 = b.Loan_id  <where> 1=1" +
            "<if test=\"GRZH!=null and GRZH!=''\"> and a.GRZH=#{GRZH}</if>" +
            "<if test=\"pName!=null and pName!=''\"> and a.pName like concat('%',#{pName},'%')</if>" +
            " </where></script>")

    int getAppointPageCount(Map map);

    /**
     * 获取约定提取信息
     * @param map
     * @return
     */
    @Select("select a.*,b.* from tb_agreed_to_extract a, tb_appoint_update b where a.GRZH=b.GRZH and a.GRZH=#{GRZH} and b.upState=2")
    List<Map>  getView(Map map);

    /**
     * 公积金普通提取审核带参分页查询
     * @param map
     * @return
     */
    @Select("<script>select date_format(a.appl_time,'%Y-%m-%d %H:%i:%S') as appl_time,a.*,b.*,c.dalance from \n" +
            " tab_extract_application a,tb_person_info b,tb_paccountutil c   where a.pre_account=c.GRZH and b.tb_pid=c.pid \n" +
            "<if test=\"pre_account!=null and pre_account!=''\"> and pre_account=#{pre_account}</if>" +
            "<if test=\"appl_name!=null and appl_name!=''\"> and appl_name like concat('%',#{appl_name},'%')</if>" +
            " limit #{start},#{end} </script>")

    List<Map> getExaminePageByparam(Map map);

    /**
     * 公积金普通提取审核查询分页总数量
     * @param map
     * @return
     */
    @Select("<script> select count(*) from tab_extract_application a,tb_person_info b,tb_paccountutil c   <where> a.pre_account=c.GRZH and b.tb_pid=c.pid" +
            "<if test=\"pre_account!=null and pre_account!=''\"> and pre_account=#{pre_account}</if>" +
            "<if test=\"appl_name!=null and appl_name!=''\"> and appl_name like concat('%',#{appl_name},'%')</if>" +
            " </where></script>")

    int getExaminePageCount(Map map);

    /**
     *部分提取通过
     * @param map
     * @return
     */
    @Update("UPDATE tab_extract_application set appl_state='2' WHERE extract_application_id=#{extract_application_id}")
    int BFtongGuo(Map map);

    /**
     *部分提取通过修改余额
     * @param map
     * @return
     */
    @Update("UPDATE tb_paccountutil set dalance=dalance-#{tqje} WHERE GRZH=#{pre_account}")
    int reviseMoney(Map map);
    /**
     * 部分提取驳回
     * @param map
     * @return
     */
    @Update("UPDATE tab_extract_application set appl_state='3' WHERE extract_application_id=#{extract_application_id}")
    int BFboHui(Map map);


    /**
     * 约定提取通过
     * @param map
     * @return
     */
    @Update("UPDATE tb_agreed_to_extract set SHZT=2 WHERE bid=#{bid}")
    int YDTQtongguo(Map map);

    /**
     *约定提取通过修改余额
     * @param map
     * @return
     */
    /*@Update("UPDATE tb_paccountutil set dalance=dalance-(#{hkje}*#{ydqs}) WHERE GRZH=#{GRZH}")*/
    @Insert("insert into tb_appoint_update (hkje,GRZH,appointTime,upState,operater,extractReason) values (#{hkje},#{GRZH},CONCAT(#{year},'-',#{month},'-',#{ydrq}),1,'小刘','约定提取')")
    void reviseAppointMoney(Map map);

    /**
     * 约定提取驳回
     * @param map
     * @return
     */
    @Update("UPDATE tb_agreed_to_extract set SHZT=3 WHERE bid=#{bid}")
    int YDTQbohui(Map map);

    /**
     * 销户提取通过
     * @param map
     * @return
     */
    @Update("UPDATE tab_extract_application set appl_state='2' WHERE extract_application_id=#{extract_application_id}")
    int xiaoHu(Map map);

    /**
     * 修改单位缴存人数
     * @return
     */
    @Update("update tb_unitaccount a set a.uDepositedPnum = (select count(*) from tb_paccountutil c join tb_unit b on c.uaid = b.uid where peraccState = '1' and b.uid = a.uid and a.uid =(select c.uaid from tb_paccountutil c where GRZH = #{pre_account} )) where a.uid = (select c.uaid from tb_paccountutil c where GRZH = #{pre_account} ) ")
    int  updateUnitPeople(Map map);

    /**
     * 修改单位应缴纳金额
     * @return
     */
    @Update("update tb_unitaccount set uaOweMonery = uaOweMonery-(select yDrawAmt from tb_paccountutil where GRZH =  #{pre_account}) where uid = (select uaid from tb_paccountutil where GRZH =  #{pre_account})")
    int updateUaOweMonery(Map map);

    /**
     * 修改个人账户信息
     * @return
     */
    @Update("UPDATE tb_paccountutil set dalance=0,peraccState=0 WHERE GRZH= #{pre_account}")
    int updatePaccountutil(Map map);

    /**
     * 验证约定提取人员
     * @param map
     * @return
     */
    @Select("select count(*) as cut from tb_agreed_to_extract,tab_extract_application where (GRZH= #{GRZH} and SHZT=1) or (pre_account=#{GRZH} and appl_state=1 )")
    int yanZhengYDTQrenyuan(Map map);

    /**
     * 提取还贷
     * @param map
     * @return
     */
    @Select("select a.*,b.*,c.*,d.*,e.* from \n" +
            " tb_paccountutil b,tb_unitaccount c,tb_unit d,tb_loanapproval f,tb_repay e left join tb_person_info a  on e.pid = a.tb_pid  where a.tb_pid = b.pid and a.unit_Id=c.DWZH and c.uid=d.uid and a.tb_pid=f.pid \n" +
            "and peraccState = 1 and b.GRZH=#{GRZH}")
    List<Map>  getTQHD(Map map);

    /**
     * 提取部分还贷修改还款表
     * @param map
     * @return
     */
    @Update("update tb_repay set repay_money = repay_money+#{corpus},repayed_interests=repayed_interests+#{interest},repayed_All_money=repayed_All_money+#{repayMoney},residue_money=residue_money-#{corpus},residue_interests=residue_interests-#{interest}," +
            "repayed_period=repayed_period+1,residue_periods=residue_periods-1,repayed_date=NOW() where GRZH=#{GRZH}")
    int  getTQHDBF(Map map);

    /**
     * 提取一次性还贷修改还款表
     * @param map
     * @return
     */
    @Update("update tb_repay set repay_money =repay_money+#{corpus},repayed_interests=repayed_interests+#{interest},repayed_All_money=repayed_All_money+#{repayMoney},residue_money=0,residue_interests=0," +
            "repayed_period=repayed_period+1,residue_periods=0,repay_state=2,repayed_date=NOW() where GRZH=#{GRZH}")
    int  getTQHDYC(Map map);

    /**
     *提取还贷修改余额
     * @param map
     * @return
     */
    @Update("UPDATE tb_paccountutil set dalance=dalance-#{repayMoney} WHERE GRZH=#{GRZH}")
    int TQHDReviseMoney(Map map);
    /**
     *提取还贷添加还款记录
     * @param map
     * @return
     */
    @Insert("insert into tb_repay_record (repay_id,GRZH,pName,repayed_date) values (#{repay_id},#{GRZH},#{pName},NOW()) where GRZH = #{GRZH}")
    int addRecord(Map map);

    /**
     * 添加共同还贷人分页
     * @param map
     */
    @Select("<script>select a.*,b.*,c.*,d.*,e.* from \n" +
            " tb_paccountutil b,tb_unitaccount c,tb_unit d,tb_person_info a left join tb_loanapproval e on a.tb_pid = e.pid  where a.tb_pid = b.pid and a.unit_Id=c.DWZH and c.uid=d.uid \n" +
            "and peraccState = 1   " +
            " limit #{start},#{end} </script>")

    List<Map> addPerson(Map map);

    /**
     * 添加共同还贷人分页总数量
     * @param map
     * @return
     */
    @Select("<script> select count(*) from tb_paccountutil b,tb_unitaccount c,tb_unit d ,tb_person_info a left join tb_loanapproval e on a.tb_pid = e.pid   <where> a.tb_pid = b.pid and a.unit_Id=c.DWZH and c.uid=d.uid and peraccState = 1" +
            " </where></script>")

    int addPersonCount(Map map);

    /**
     * 添加共同还贷人
     * @param
     * @return
     */
    @Insert("insert into tb_repayment (GRZH,toGRZH) values (#{GRZH},#{toGRZH})")
    int addBatchPerson(Map map);

    /**
     * 查询共同还贷人
     * @param
     * @return
     */
    @Select("select a.*,b.*,c.*,d.*,e.*,f.* from tb_person_info a,tb_loanapproval e,tb_unitaccount c,tb_unit d,tb_repayment f left join tb_paccountutil b  on f.GRZH = b.GRZH  where a.tb_pid = e.pid and a.tb_pid = b.pid and a.unit_Id=c.DWZH and c.uid=d.uid and peraccState = 1 and f.toGRZH=#{toGRZH}")
    List<Map> getBatchPerson(Map map);

    /**
     * 移除共同还贷人
     * @param
     * @return
     */
    @Delete("delete from tb_repayment where GRZH in (#{_parameter}) and toGRZH = #{toGRZH}")
    int delBatchPerson(Map map);


}
