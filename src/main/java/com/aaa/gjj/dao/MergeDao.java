package com.aaa.gjj.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:MergeDao
 * discription:
 * author:qcm
 * createTime:2018-12-12 11:38
 */
public interface MergeDao {
    /**
     * 封存 启封 销户 分页
     * @param map
     * @return
     */
    @Select("<script>select a.GRZH,a.ubitNrop,a.indiNrop,a.pid,b.tb_pid,a.baseNummber," +
            "a.dalance,a.lastNaydate,a.peraccState,b.tb_pName " +
            "from tb_paccountutil a,tb_person_info b where a.pid=b.tb_pid " +
            "<if test=\"tb_pName!=null and tb_pName!=''\"> and tb_pName like concat('%',#{tb_pName},'%')</if>" +
            "limit #{start},#{end}</script>")
    List<Map> SealedPage1(Map map);

    /**
     * 封存 启封 销户 查询信息总条数
     * @param map
     * @return
     */
    @Select("<script>select count(*) as cnt from tb_paccountutil a,tb_person_info b where a.pid=b.tb_pid" +
            "<if test=\"tb_pName!=null and tb_pName!=''\"> and tb_pName like concat('%',#{tb_pName},'%')</if>" +
            "</script>")
    List<Map> SealedPageCount1(Map map);

    /**
     * 判断唯一性校验  封存 启封 销户 校验   不能重复操作
     * @param map
     * @return
     */
    @Select("select * from tb_unseal_audit where audit_name = '0' and unseal_account = #{grzh}")
    List<Map> verification(Map map);

    /**
     * 校验贷款的人 不能销户 和 封存
     * @param map
     * @return
     */
    @Select("select * from tb_repay_detail where  GRZH = #{grzh}")
    List<Map> loansVerification(Map map);

    /**
     * 封存 启封 销户   操作弹出层查询信息
     * @param map
     * @return
     */
    @Select("select a.GRZH,a.peraccState,b.tb_pName,a.pid," +
            "b.tb_pid,a.uaid,c.uid,c.uname,d.DWZH,d.uid" +
            " from tb_paccountutil a," +
            "tb_person_info b," +
            "tb_unit c," +
            "tb_unitaccount d" +
            " where a.pid=b.tb_pid and a.uaid=c.uid and c.uid=d.uid and GRZH = #{grzh}")
    List<Map> operationQuery(Map map);
    /**
     * 获取审核信息   放入审核表中
     * @param map
     * @return
     * element
     */
    @Select("select a.GRZH,a.peraccState,b.tb_pSex,b.tb_idNUmber,b.tb_pDate,b.tb_pIphone,b.tb_Profession,b.tb_pName,a.pid,b.tb_pid,a.uaid,c.uid,c.uname,d.DWZH,d.uid,c.uid from tb_paccountutil a,tb_person_info b,tb_unit c,tb_unitaccount d where a.pid=b.tb_pid and a.uaid=c.uid and c.uid=d.uid and GRZH = #{grzh}")
    List<Map> unsealAudit1(Map map);

    /**
     * 获取审核信息  添加到审核表中
     * @param map
     * @return
     * element
     */
    @Insert("insert into tb_unseal_audit(unseal_name,unseal_unit,unseal_sex,unit_post,unseal_phone,unseal_number,born_date,unseal_account,reason,operator,create_time,audit_state,state) values(#{tb_pName},#{uname},#{tb_pSex},#{tb_Profession},#{tb_pIphone},#{tb_idNUmber},#{tb_pDate},#{GRZH},#{resson},#{operator},#{create_time},#{audit_state},#{state})")
    int unsealAuditAdd1(Map map);


    /**
     * 明细查询分页
     * @param map
     * @return
     */
    @Select("<script>select a.GRZH,a.unitMonPaysum,a.perMonPaysum,a.paOp,a.pid,b.tb_pid,a.uaid,c.uid,c.uname," +
            "b.tb_pName,a.openDate from tb_paccountutil a,tb_person_info b,tb_unit c " +
            " where a.pid=b.tb_pid and a.uaid=c.uid " +
            "<if test=\"tb_pName!=null and tb_pName!=''\"> and tb_pName like concat('%',#{tb_pName},'%')</if>" +
            "limit #{start},#{end}</script>")
    List<Map> getPage1(Map map);

    /**
     * 明细查询信息条数
     * @param map
     * @return
     */
    @Select("<script>select count(*) as cnt from tb_paccountutil a,tb_person_info b,tb_unit c  where a.pid=b.tb_pid and a.uaid=c.uid " +
            "<if test=\"tb_pName!=null and tb_pName!=''\"> and tb_pName like concat('%',#{tb_pName},'%')</if>" +
            "</script>")
    List<Map> getPageCount1(Map map);


    //12121640查看审批

    /**
     * 审批工作类别查询
     * @return
     */
    @Select("<script>select * from accraditation</script>")
    List<Map> accraditation1();
    /**
     * 审批工作类别查询   查询   个人贷款  共多少条信息
     * @return
     */
    @Select("select count(*) as flux from tb_loanapproval_end")
    int  accraditationCount();
    /**
     * 添加到审批表中  共有多少条信息
     * 把信息个数   存入个人贷款类型信息
     * @param flux  信息条数
     * @param name	工作类别
     * @return
     */
    @Update("update accraditation set Accraditation_flux = #{flux} where Accraditation_name= #{name} ")
    int addAccraditationCount(@Param("flux") int flux,@Param("name") String name);

    /**
     * 审批工作类别查询   查询 人员转移审批   共多少条信息
     * @return
     */
    @Select("select count(*) as flux from tb_transfer_audit_jl")
    int accraditationCountb();

    /**
     *审批工作类别查询   查询 公积金提取   共多少条信息
     * @return
     */
    @Select("select count(*) as flux from tab_extract_application")
    int accraditationCountt();

    /**
     * 审批工作类别查询   查询 封存、启封、销户审批    共多少条信息
     * @return
     */
    @Select("select count(*) as flux from tb_unseal_audit")
    int accraditationCounts();

    /**
     * 查询贷款记录表中的信息 录入查看审批表中
     * @param
     * @param
     * @return
     */
    @Select("<script>select a.*,b.pid,c.tb_idNUmber from tb_loanapproval_end a join tb_paccountutil b on a.pid=b.pid join tb_person_info c on b.pid=c.tb_pid" +
            " limit #{start},#{end}</script>")
    List<Map> loans1(Map map);

    /**
     * 查询贷款记录表中的信息(总条数)
     * @return
     */
    @Select("<script>select count(*) as cou from tb_loanapproval_end a join tb_paccountutil b on a.pid=b.pid join tb_person_info c on b.pid=c.tb_pid</script>")
    List<Map> loansCount1();

    /**
     * 查询封存、启封、销户 记录表
     * @param map
     * @return
     */
    @Select("<script>select * from tb_unseal_audit" +
            " limit #{start},#{end}</script>")
    List<Map> breaka1(Map map);

    /**
     * 查询封存、启封、销户 记录表 条数
     * @return
     */
    @Select("select count(*) as cou from tb_unseal_audit ")
    List<Map> breakaCount1();

    /**
     * 查询人员转移记录表
     * @param map
     * @return
     */
    @Select("<script>select * from tb_transfer_audit_jl" +
            " limit #{start},#{end}</script>")
    List<Map> transfer(Map map);

    /**
     * 查询人员转移记录表 条数
     * @return
     */
    @Select("select count(*) as cou from tb_transfer_audit_jl")
    List<Map> transferCount();

    /**
     * 查询公积金提取记录表
     * @param map
     * @return
     */
    @Select("<script>select pre_account,appl_name,comp_name,application_type,application_way,extract_reason,extract_money,appl_time,appl_state from tab_extract_application" +
            " limit #{start},#{rows}</script>")
    List<Map> extract(Map map);
    /**
     * 查询公积金提取记录表
     * @param map
     * @return
     */
    @Select("<script>select pre_account,appl_name,comp_name,application_type,application_way," +
            "extract_reason,extract_money,date_format(appl_time,'%Y-%m-%d %H:%i:%S') as appl_time,appl_state from tab_extract_application" +
            " limit #{start},#{end}</script>")
    List<Map> extract1(Map map);

    /**
     * 查询公积金提取记录表 条数
     * @return
     */
    @Select("select count(*) as cou from tab_extract_application")
    List<Map> extractCount1();
}
