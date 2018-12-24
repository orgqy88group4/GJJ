package com.aaa.gjj.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * className:LoanDao
 * discription:
 * author:qcm
 * createTime:2018-12-18 11:05
 */
public interface LoanDao {
    /**
     * 查询出来 个人账号  做唯一性校验 tb_loanapproval贷款记录表
     * @return
     */
    @Select("select GRZH from tb_loanapproval")
    List<Map> getgrzh();
    /**
     * 根据前台传来的个人账号，来查询账户信息
     * @return
     */
    @Select("select * from tb_person_info p join tb_paccountutil pa on p.tb_pid=pa.pid join tb_unit u  on u.uid=pa.uaid where pa.GRZH=#{grzh}")
    List<Map> getList(Map map);

    /**
     * 个人贷款模块 --共同借款人--前台传来共同贷款人账号
     * @param map
     * @return
     */
    @Select("select *  from tb_person_info p join tb_paccountutil pa on p.tb_pid=pa.pid join tb_unit u  on u.uid=pa.uaid where pa.GRZH=#{grzh1}")
    List<Map> getTogether(Map map);

    /**
     * 把贷款数据存到数据库里面
     * @param map
     * @return
     */
    @Insert("insert into tb_loanapproval(Loan_id,pid,GRZH,salary,bank_money,loan_money,loan_periods,loan_rate,loan_bank," +
            "loan_repay,loan_repayer,loan_repaydate,repay_bank,repay_accountname,repay_account,house_type,house_location," +
            "house_area,id_number,buy_name,bank_account,house_total,house_firstpay,house_price,pawn_type,pawn_name," +
            "pawn_idnumber,pawn_address,pawn_state,pawn_money,ctime) values(null,#{tb_pid},#{GRZH},#{bcApplyIcome},#{bcBankFund}," +
            "#{bcMoney},#{bcNum},#{bcRate},#{bcbankname},#{bcRefundType},#{bcname},#{bcRepaymentDate},#{bcwAtBankName}," +
            "#{bcAccount},#{YHZH},#{hsPurchaseType},#{hshouseAddress},#{hsFloorSpace},#{tb_idNUmber},#{tb_pName},#{YHZH}," +
            "#{hsAllMonery},#{hsFirstMonery},#{hsFloorSpacePrice},#{miType},#{tb_pName},#{tb_idNUmber},#{miThAddresss}," +
            "#{miThState},#{miPledgeMonery},concat(DATE_FORMAT(now(),'%Y-%m-%d')))")
    int getInfo(Map map);

    /**
     * 贷款初审
     * @param map
     * @return
     */
    @Select("<script>select Loan_id,t.pid,pa.GRZH,p.tb_pName,loan_money,loan_periods,loan_rate,loan_bank,loan_repay," +
            "ctime,status,tb_pIphone,tb_idNUmber,tb_Profession,tb_pEducation,tb_pEmail,tb_pAddress,unit_Id,salary," +
            "bank_money,repay_bank,repay_account,loan_repay,pawn_type,pawn_name,pawn_idnumber,pawn_address,pawn_state," +
            "pawn_money,house_type,house_location,house_area,buy_name,house_total,house_firstpay,house_price " +
            "from tb_person_info p join tb_paccountutil pa on p.tb_pid=pa.pid join tb_loanapproval t on t.pid=p.tb_pid where status = 1 " +
            "<if test=\"pName!=null and pName!=''\"> and tb_pName like concat('%',#{pName},'%')</if>" +
            "<if test=\"ctime!=null and ctime!=''\"> and ctime like concat('%',#{ctime},'%')</if>" +
            " limit #{start},#{end}</script>")
    List<Map> getPage(Map map);

    /**
     * 查询贷款初审分页数据的总数量 (如果分页数据里面带有条件 )
     * @param map
     * @return
     */
    @Select("<script>select count(*) as count from tb_person_info p join tb_paccountutil pa on p.tb_pid=pa.pid join tb_loanapproval t on t.pid=p.tb_pid " +
            "where status = 1 <if test=\"pName!=null and pName!=''\"> and tb_pName like concat('%',#{pName},'%')</if>" +
            "<if test=\"ctime!=null and ctime!=''\"> and ctime like concat('%',#{ctime},'%')</if>" +
            "</script>")
    List<Map> getPageCount(Map map);

    /**
     * 点击详细按钮，前台传来个人账户，根据个人账户来查询详细信息
     * @param map
     * @return
     */
    @Select("select Loan_id,t.pid,pa.GRZH,p.tb_pName,loan_money,loan_periods,loan_rate,loan_bank," +
            "loan_repay,ctime,status,tb_pIphone,tb_idNUmber,tb_Profession,tb_pEducation,tb_pEmail," +
            "tb_pAddress,unit_Id,salary,bank_money,repay_bank,repay_account,loan_repay,pawn_type," +
            "pawn_name,pawn_idnumber,pawn_address,pawn_state,pawn_money,house_type,house_location," +
            "house_area,buy_name,house_total,house_firstpay,house_price " +
            "from tb_person_info p join tb_paccountutil pa on p.tb_pid=pa.pid join tb_loanapproval t on t.pid=p.tb_pid where pa.GRZH=#{GRZH}")
    List<Map> getPersonInfo(Map map);

    /**
     * 点击审核通过按钮，将数据插入到终审表 修改状态为2
     * @param map
     * @return
     */
    @Insert("insert into tb_loanapproval_end (Loan_id,pid,repay_bank,repay_account,pName,GRZH,loan_money," +
            "loan_periods,loan_rate,loan_bank,loan_repay,ctime,status) values(#{Loan_id},#{pid},#{repay_bank}," +
            "#{repay_account},#{pName},#{GRZH},#{loan_money},#{loan_periods},#{loan_rate},#{loan_bank}," +
            "#{loan_repay},#{ctime},#{status})")
    int PassCheck(Map map);

    /**
     * 初审驳回按钮 将数据插入到终审表  插入时状态为3
     * @param map
     * @return
     */
    @Insert("insert into tb_loanapproval_end (Loan_id,pid,repay_bank,repay_account,pName,GRZH,loan_money," +
            "loan_periods,loan_rate,loan_bank,loan_repay,ctime,status,rejectreason) values(#{Loan_id},#{pid},#{repay_bank}," +
            "#{repay_account},#{pName},#{GRZH},#{loan_money},#{loan_periods},#{loan_rate},#{loan_bank}," +
            "#{loan_repay},#{ctime},#{status},#{rejectreason})")
    int reject(Map map);

    /**
     * 初审通过，将状态修改位2   初审点击驳回  获取值 修改状态为3
     * @param map
     * @return
     */
    @Update("update tb_loanapproval set status = #{status} where Loan_id = #{Loan_id}")
    int PassChangeState(Map map);

    /**
     * 贷款终审
     * @param map
     * @return
     */
    @Select("<script>select t.Loan_id,pa.pid,t.repay_bank,t.repay_account,t.pName,pa.GRZH,t.loan_money,t.loan_periods," +
            "t.loan_rate,loan_bank,t.loan_repay,t.ctime,status " +
            "from tb_person_info p join tb_paccountutil pa on p.tb_pid=pa.pid join tb_loanapproval_end t on t.pid=p.tb_pid where status = 2" +
            "<if test=\"pName!=null and pName!=''\"> and pName like concat('%',#{pName},'%')</if>" +
            "<if test=\"ctime!=null and ctime!=''\"> and ctime like concat('%',#{ctime},'%')</if>" +
            " limit #{start},#{end}</script>")
    List<Map> getPageFinal(Map map);

    /**
     * 查询贷款终审分页数据的总数量 (如果分页数据里面带有条件 )
     * @param map
     * @return
     */
    @Select("<script>select count(*) as count from tb_loanapproval_end where status = 2" +
            "<if test=\"pName!=null and pName!=''\"> and pName like concat('%',#{pName},'%')</if>" +
            "<if test=\"ctime!=null and ctime!=''\"> and ctime like concat('%',#{ctime},'%')</if>" +
            "</script>")
    List<Map> getPageFinalCount(Map map);

    /**
     *点击贷款终审，确认驳回按钮，状态变为5
     * @param map
     * @return
     */
    @Update("update tb_loanapproval_end set rejectreason=#{rejectreason},status=#{status},ctime=concat(DATE_FORMAT(now(),'%Y-%m-%d')) where Loan_id=#{Loan_id}")
    int SureReject(Map map);

    /**
     *终审通过之后  把信息存进还款明细 计算出应还本金 利息等
     * @param map
     * @return
     */
    @Insert("insert into tb_repay_detail(Loan_id,pid,repay_account,GRZH,loan_money,loan_periods,loan_rate,repay_bank,loan_repay,pName,repay_money,repay_interests,repay_month,ctime) values(#{Loan_id},#{pid},#{repay_account},#{GRZH},#{loan_money},#{loan_periods},#{loan_rate},#{repay_bank},#{loan_repay},#{pName},#{repay_money},#{repay_interests},#{repay_month},concat(DATE_FORMAT(now(),'%Y-%m-%d')))")
    int FinalPass(Map map);

    /**
     * 终审通过、修改贷款终审表的相关状态
     * @param map
     * @return
     */
    @Update("update tb_loanapproval_end set status=#{status},ctime=concat(DATE_FORMAT(now(),'%Y-%m-%d')) where Loan_id=#{Loan_id}")
    int passFinal(Map map);

    /**
     * 销户审核信息
     * @param map
     * @return
     */
    @Select("<script>select unseal_id,unseal_name,unseal_unit,unseal_sex,unit_post,unseal_phone,unseal_number," +
            "DATE_FORMAT(born_date,'%Y-%m-%d') AS born_date,unseal_account,reason,operator," +
            "DATE_FORMAT(create_time,'%Y-%m-%d %H:%i:%S') AS create_time,audit_state,state " +
            "from tb_unseal_audit where state = '0' and audit_name = '0'" +
            "<if test=\"unseal_name!=null and unseal_name!=''\"> and unseal_name like concat('%',#{unseal_name},'%')</if>" +
            " limit #{start},#{end}</script>")
    List<Map> QueryAuditInfomation(Map map);

    /**
     * 销户审核信息总条数
     * @param map
     * @return
     */
    @Select("<script>select count(*) as count from tb_unseal_audit where state = '0' and audit_name = '0' " +
            "<if test=\"unseal_name!=null and unseal_name!=''\"> and unseal_name like concat('%',#{unseal_name},'%')</if>" +
            "</script>")
    List<Map> QueryAuditInfomationCount(Map map);

    /**
     * 修改个人账户状态
     * @param map
     * @return
     */
    @Update("update tb_paccountutil set peraccState = #{pdstype},yPayAmt = #{resson} where GRZH = #{grzh}")
    int PersonAccountState(Map map);

    /**
     * 修改审核表中的audit_name字段，改为通过
     * @param map
     * @return
     */
    @Update("update tb_unseal_audit set audit_name = #{audit_name},bohui=#{bohui}  where unseal_id = #{unseal_id}")
    int unsealAuditUpdate(Map map);

    /**
     * 修改公司缴存人数
     * @param map
     * @return
     */
    @Update("update tb_unitaccount a set a.uDepositedPnum = (select count(*) from" +
            " tb_paccountutil c join tb_unit b on c.uaid = b.uid where c.peraccState = '1' " +
            "and b.uid = a.uid and a.uid =(select c.uaid from tb_paccountutil c where GRZH = #{grzh} )) ")
    int unitsUpdate(Map map);

    /**
     * 销户，封存 修改公司缴存总金额
     * @param map
     * @return
     */
    @Update("update tb_unitaccount set uaOweMonery = uaOweMonery-(select yDrawAmt from tb_paccountutil where GRZH = #{grzh}) " +
            "where uid = (select uaid from tb_paccountutil where GRZH = #{grzh})")
    int unitsUpdateMoney(Map map);

    /**
     * 启封 修改公司缴存总金额
     * @param map
     * @return
     */
    @Update("update tb_unitaccount set uaOweMonery = uaOweMonery+(select yDrawAmt from tb_paccountutil where GRZH = #{grzh}) " +
            "where uid = (select uaid from tb_paccountutil where GRZH = #{grzh})")
    int unitsMoney(Map map);

    /**
     * 1 为启封  2为封存  0为销户
     * 查询审核表中状态位1和2的
     * 查询审核表中的
     * @return
     */
    @Select("<script>select unseal_id,unseal_name,unseal_unit,unseal_sex,unit_post,unseal_phone,unseal_number,DATE_FORMAT(born_date,'%Y-%m-%d') AS born_date,unseal_account,reason,operator,DATE_FORMAT(create_time,'%Y-%m-%d %H:%i:%S') AS create_time,audit_state,state,audit_name from tb_unseal_audit where state in(1,2) and audit_name = '0'" +
            "<if test=\"unseal_name!=null and unseal_name!=''\"> and unseal_name like concat('%',#{unseal_name},'%')</if>" +
            " limit #{start},#{end}</script>")
    List<Map> unsealAuditSelect(Map map);
    /**
     * 1 为启封  2为封存  0为销户
     * 查询审核表中状态位1和2的
     * @param map
     * @return
     */
    @Select("<script>select count(*) as count from tb_unseal_audit where state in(1,2) and audit_name = '0' " +
            "<if test=\"unseal_name!=null and unseal_name!=''\"> and unseal_name like concat('%',#{unseal_name},'%')</if>" +
            "</script>")
    List<Map> unsealAuditSelectCount(Map map);

    /**
     *查询人员转移审核表信息
     * @param map
     * @return
     */
    @Select("<script>select transfer_id,pname,idNumber,balance,state,transfer_out_unit,transfer_into_unit,auditor," +
            "person_account,transfer_reason,operator,submit_time,audit_state,id " +
            "from tb_transfer_audit" +
            "<if test=\"auditor!=null and auditor!=''\"> where auditor like concat('%',#{auditor},'%')</if>" +
            " limit #{start},#{end}</script>")
    List<Map> uditTransfer(Map map);

    /**
     * 查询转移人员信息总条数
     * @param map
     * @return
     */
    @Select("<script>select count(*) as count from tb_transfer_audit" +
            "<if test=\"auditor!=null and auditor!=''\"> where auditor like concat('%',#{auditor},'%')</if>" +
            "</script>")
    List<Map> uditTransferCount(Map map);

    /**
     * 查询出要转入的公司
     * @param map
     * @return
     */
    @Select("select transfer_into_unit,transfer_out_unit,person_account from tb_transfer_audit where transfer_id=#{transfer_id}")
    List<Map> rejects(Map map);

    /**
     *查询审核表
     * @param map
     * @return
     */
    @Select("select * from tb_transfer_audit where transfer_id=#{transfer_id}")
    List<Map> CheckAuditTable(Map map);

    /**
     * 添加到审核记录表
     * @param map
     * @return
     */
    @Insert("insert into tb_transfer_audit_jl(pname,idNumber,balance,state,transfer_out_unit,transfer_into_unit,auditor,person_account,transfer_reason,operator,submit_time,baseNummber,audit_state)" +
            "values(#{pname},#{idNumber},#{balance},#{state},#{transfer_out_unit},#{transfer_into_unit},#{auditor},#{person_account},#{transfer_reason},#{operator},#{submit_time},#{baseNummber},#{audit_state})")
    int addInfoToRecord(Map map);

    /**
     * 人员转移驳回，删除记录
     * @param map
     * @return
     */
    @Delete("delete from tb_transfer_audit where transfer_id=#{transfer_id}")
    int RejectDelete(Map map);

    /**
     * 通过转入公司名称查询到要转入公司id
     * @param name
     * @return
     */
    @Select("select uid,uname from tb_unit where uname=#{name}")
    List<Map> seletId(@Param("name") String name);

    /**
     * 根据获取的个人账号查询到自己公司id 在修改为要转入的公司id
     * @param GRZH
     * @param uaid
     * @return
     */
    @Update("update tb_paccountutil set uaid=#{uaid} where GRZH = #{GRZH}")
    int upDateId(@Param("uaid") int uaid, @Param("GRZH") String GRZH);

    /**
     * 转入公司缴存人数  查询到当前公司账户状态为正常的个数  把公司账户信息表 应缴纳人数修改了
     * @param uname
     * @return
     */
    @Update("update tb_unitaccount a set a.uDepositedPnum = (select count(*) from tb_paccountutil c join tb_unit b on c.uaid = b.uid where peraccState = '1' and b.uid = (select uid from tb_unit where uname = #{uname})) where a.uid = (select uid from tb_unit where uname = #{uname})")
    int unitsUpdatec(@Param("uname") String uname);

    /**
     * 转出公司缴存人数  查询到当前公司账户状态为正常的个数  把公司账户信息表 应缴纳人数修改了
     * @param uname
     * @return
     */
    @Update("update tb_unitaccount a set a.uDepositedPnum = (select count(*) from tb_paccountutil c join tb_unit b on c.uaid = b.uid where peraccState = '1' and b.uid = (select uid from tb_unit where uname = #{uname})) where a.uid = (select uid from tb_unit where uname = #{uname})")
    int unitsUpdatea(@Param("uname") String uname);

    /**
     * 获取转出公司 进行修改总缴存金额 方法
     * @param name
     * @return
     */
    @Update("update tb_unitaccount set uaOweMonery = (select SUM(yDrawAmt) from tb_paccountutil a ,tb_unit b where a.uaid=b.uid and a.peraccState = 1 and b.uid =(select uid from tb_unit where uname = #{name} )) where uid = (select uid from tb_unit where uname = #{name} )")
    int unitsUpdateMoneya(@Param("name") String name);

    /**
     * 获取转入公司 进行修改总缴存金额 方法
     * @param name
     * @return
     */
    @Update("update tb_unitaccount set uaOweMonery = (select SUM(yDrawAmt) from tb_paccountutil a ,tb_unit b where a.uaid=b.uid and a.peraccState = 1 and b.uid =(select uid from tb_unit where uname = #{name} )) where uid = (select uid from tb_unit where uname = #{name} )")
    int unitsMoneya(@Param("name") String name);

    /**
     * 获取还款信息表
     * @param id
     * @return
     */
    @Select("select * from tb_repay_detail where Loan_id=#{id}")
    List<Map> getrepayinfo(Integer id);
    /**
     * 点击通过 把还款信息表内容添加到还款表
     * @param map
     * @return
     */
    @Insert("insert into tb_repay(repay_id,Loan_id,pid,repay_account," +
            "GRZH,loan_money,loan_periods,loan_rate,repay_bank,loan_repay," +
            "pName,repay_money,repay_interests,repay_month,ctime,repayed_money," +
            "repayed_interests,residue_money,residue_interests,repayed_period," +
            "residue_periods,repay_mmonth,repay_all_mmonth,repayed_date,repay_state," +
            "repay_month_money,repay_month_interest,repayed_All_money) " +
            "values(#{repay_id},#{Loan_id},#{pid},#{repay_account},#{GRZH},#{loan_money}," +
            "#{loan_periods},#{loan_rate},#{repay_bank},#{loan_repay},#{pName},#{repay_money}," +
            "#{repay_interests},#{repay_month},#{ctime},#{repayed_money},#{repayed_interests}," +
            "#{residue_money},#{residue_interests},#{repayed_period},#{residue_periods}," +
            "#{repay_mmonth},#{repay_all_mmonth},#{repayed_date},1,#{repay_month_money}," +
            "#{repay_month_interest},#{repayed_All_money})")
    int insertRepay(Map map);

    /**
     * 查询还款信息表
     * @param GRZH
     * @return
     */
    @Select("select * from tb_repay_detail where GRZH=#{GRZH}")
    List<Map> updateGRZHBalance(String GRZH);

    /**
     * 终审通过之后 修改个人账户余额
     * @param map
     * @return
     */
    @Update("update tb_repay_detail r,tb_paccountutil t set dalance = dalance + #{danlance1} where r.GRZH=t.GRZH and  r.GRZH = #{GRZH}")
    int updateBalance(Map map);
}
