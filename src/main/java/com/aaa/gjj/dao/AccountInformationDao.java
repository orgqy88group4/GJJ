package com.aaa.gjj.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:AccountInformationDao
 * discription:
 * author:qcm
 * createTime:2018-12-09 18:09
 */
public interface AccountInformationDao {
    //个人信息
    @Select("<script>select a.GRZH,b.tb_pSex,b.tb_pIphone,a.ubitNrop,a.indiNrop,a.dalance,a.peraccState,b.tb_pName,c.uname,d.DWZH,d.uid,a.pid,b.tb_pid,a.uaid,c.uid from tb_paccountutil a,tb_person_info b,tb_unit c,tb_unitaccount d   where a.pid=b.tb_pid and a.uaid=c.uid and c.uid=d.uid " +
            "<if test=\"tb_pName!=null and tb_pName!=''\"> and tb_pName like '%'||#{tb_pName}||'%'</if>" +
            " limit  #{start},#{rows}</script>")
    List<Map> AccountInformation(Map map);
    @Select("<script>select count(*) as cnt from tb_paccountutil a,tb_person_info b,tb_unit c,tb_unitaccount d  where a.pid=b.tb_pid and a.uaid=c.uid and c.uid=d.uid " +
            "<if test=\"tb_pName!=null and tb_pName!=''\"> and tb_pName like '%'||#{tb_pName}||'%'</if>"+
            "</script>")
    List<Map>  AccountInformationCount(Map map);
    //个人信息修改操作
    @Select("select a.GRZH,a.ubitNrop,a.indiNrop,a.baseNummber,a.peraccState,c.uname,d.DWZH,d.uid,a.pid,b.*,a.uaid,c.uid,a.KHYH,a.YHZH from tb_paccountutil a,tb_person_info b,tb_unit c,tb_unitaccount d where a.pid=b.tb_pid and a.uaid=c.uid and c.uid=d.uid and GRZH = #{GRZH}")
    List<Map>  particulars(Map map);
    //个人信息修改完毕提交操作
    @Update("update tb_person_info set  tb_pName = #{tb_pName},tb_pEmail = #{tb_pEmail},tb_pIphone = #{tb_pIphone},tb_pAddress = #{tb_pAddress},tb_pSex = #{tb_pSex},tb_pDate = #{tb_pDate},tb_idcard = #{tb_idcard},tb_pMarriage = #{tb_pMarriage},tb_idNUmber = #{tb_idNUmber},tb_pEducation = #{tb_pEducation},tb_Profession = #{tb_Profession}  where tb_pid = #{tb_pid}")
    int  modification(Map map);
    @Update("update tb_paccountutil set KHYH = #{KHYH},YHZH = #{YHZH}  where pid =#{tb_pid}")
    int  modification2(Map map);


    //单位信息
    @Select("<script>select c.uname,c.uNetworkCode,d.uid,c.uid,d.DWZH,d.uDepositRatio,d.uPersonRatio,d.uARemain,d.uaState,d.KHRQ,d.uDepositedPnum,d.uaOweMonery from tb_unit c,tb_unitaccount d where d.uid=c.uid  " +
            "<if test=\"tb_pName!=null and tb_pName!=''\"> and tb_pName like '%'||#{tb_pName}||'%'</if>"+
            " limit  #{start},#{rows}</script>")
    List<Map> UnitInformation(Map map);
    //单位信息
    @Select("<script>select count(*) as cnt from tb_unit c,tb_unitaccount d  where d.uid=c.uid " +
            "<if test=\"tb_pName!=null and tb_pName!=''\"> and tb_pName like '%'||#{tb_pName}||'%'</if>" +
            "</script>")
    List<Map>  UnitInformationCount(Map map);
    //单位信息修改按钮
    @Select("select a.*,b.* from tb_unit a,tb_unitaccount b where a.uid=b.uid and DWZH = #{DWZH}")
    List<Map> tan(Map map);
    //单位信息提交更改按钮
    @Update("update tb_unit set uname = #{uname},uAddress = #{uAddress},GSLX = #{GSLX},LSGX = #{LSGX},JJLX = #{JJLX},uPayDate = #{uPayDate},UnitBeginDate = #{UnitBeginDate},uOperator = #{uOperator},uOpIphone = #{uOpIphone},JBRZJLX = #{JBRZJLX},JBRZJHM = #{JBRZJHM},uLegalPerson = #{uLegalPerson}, ULegalType = #{ULegalType},ULegalCard = #{ULegalCard},SSHY = #{SSHY},YYZZ = #{YYZZ} where uid  = #{uid}")
    int  unitModification(Map map);
    @Update("update tb_unitaccount set uaBankName = #{uaBankName},STYH = #{STYH},uaBankNumber = #{uaBankNumber},uaState = #{uaState}  where uid = #{uid}")
    int  unitModification2(Map map);
}
