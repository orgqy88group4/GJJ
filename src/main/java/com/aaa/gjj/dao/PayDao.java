package com.aaa.gjj.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:PayDao
 * discription: 还款
 * author:lin
 * createTime:2018-12-18 09:08
 */
public interface PayDao {

    /**
     * 带参分页查询
     * @param map
     * @return
     * 如果使用注解的方式，动态sql必须在标签<script></script>
     * 如果使用<script></script>标签，mybatis大于小于,必须使用&gt;  &lt;
     */
    @Select("<script>select * from tb_repay  \n" +
            "where 1=1 " +
            "<if test=\"repay_id!=null and repay_id!=''\"> and repay_id=#{repay_id} </if>" +
            "<if test=\"GRZH!=null and GRZH!=''\"> and GRZH=#{GRZH} </if>" +
            "<if test=\"pName!=null and roleName!=''\"> and pName like concat('%',#{pName},'%') </if>" +
            "<if test=\"loan_money!=null and loan_money!=''\"> and loan_money like concat('%',#{loan_money},'%') </if>" +
            "<if test=\"loan_periods!=null and loan_periods!=''\">  and loan_periods =#{loan_periods} </if>" +
            "<if test=\"repay_state==3 \">  and repay_state =#{repay_state} and residue_periods!=0 </if>" +
            " limit #{start},#{end} </script>")
    List<Map> getPageByParam(Map map);

    /**
     * 查询分页总数量
     * @param map
     * @return
     */
    @Select("<script> select count(*) from tb_repay <where>" +
            "<if test=\"repay_id!=null and repay_id!=''\"> and repay_id=#{repay_id} </if>" +
            "<if test=\"GRZH!=null and GRZH!=''\"> and GRZH=#{GRZH} </if>" +
            "<if test=\"pName!=null and roleName!=''\"> and pName like concat('%',#{pName},'%') </if>" +
            "<if test=\"loan_money!=null and loan_money!=''\"> and loan_money like concat('%',#{loan_money},'%') </if>" +
            "<if test=\"loan_periods!=null and loan_periods!=''\">  and loan_periods =#{loan_periods} </if>" +
            "<if test=\"repay_state==3 \">  and repay_state =#{repay_state} and residue_periods!=0 </if>" +
            " </where></script>")
    int getPageCount(Map map);

    /**
     * 查询还款记录
     * @return
     * 如果使用注解的方式，动态sql必须在标签<script></script>
     * 如果使用<script></script>标签，mybatis大于小于,必须使用&gt;  &lt;
     */
    @Select("<script>select * from tb_repay_record  where 1=1 " +
            "<if test=\"repay_id!=null and repay_id!=''\"> and repay_id=#{repay_id} </if>" +
            "<if test=\"GRZH!=null and GRZH!=''\"> and GRZH=#{GRZH} </if>" +
            "<if test=\"repayed_date !=null and repayed_date !=''\"> and timestampdiff(month,#{repayed_date},date_format(now(),'%Y-%m-%d')) &lt;=1 </if>" +
            "<if test=\"desc!=null and desc!=''\"> order by repayed_date desc limit 0,10 </if>" +
            "  </script>")
    List<Map> getRecord(Map map);

    /**
     * 带参分页查询(还款记录)
     * @param map
     * @return
     * 如果使用注解的方式，动态sql必须在标签<script></script>
     * 如果使用<script></script>标签，mybatis大于小于,必须使用&gt;  &lt;
     */
    @Select("<script>select * from tb_repay_record  \n" +
            " where 1=1 " +
            "<if test=\"GRZH!=null and GRZH!=''\"> and GRZH=#{GRZH} </if>" +
            "<if test=\"pName!=null and pName!=''\"> and pName=#{pName} </if>" +
            " limit #{start},#{end} </script>")
    List<Map> getRecordByParam(Map map);

    /**
     * 查询分页总数量(还款记录)
     * @param map
     * @return
     */
    @Select("<script> select count(*) from tb_repay_record <where> " +
            "<if test=\"GRZH!=null and GRZH!=''\"> and GRZH=#{GRZH} </if>" +
            "<if test=\"pName!=null and pName!=''\"> and pName=#{pName} </if>" +
            " </where> </script>")
    int getRecordCount(Map map);

    /**
     * 修改贷款记录表   还款状态
     * @param
     * @return
     */
    @Update("update tb_repay_detail set repay_state=#{repay_state} where repay_id=#{repay_id}")
    int updRecordState(@Param("repay_state") int repay_state,@Param("repay_id") String repay_id);

    /**
     * 点击还款   修改还款表待还款信息
     * @param map
     * @return
     */
    @Update("<script> update tb_repay set " +
            "<if test=\"residue_money!=null and residue_money!=''\"> residue_money = #{residue_money}, </if> " +
            "<if test=\"residue_interests!=null and residue_interests!=''\"> residue_interests = #{residue_interests}, </if> " +
            "<if test=\"repayed_money!=null and repayed_money!=''\"> repayed_money = #{repayed_money}, </if> " +
            "<if test=\"repayed_interests!=null and repayed_interests!=''\"> repayed_interests = #{repayed_interests}, </if>" +//residue_interests
            "<if test=\"repayed_period!=null and repayed_period!=''\"> repayed_period = #{repayed_period}, </if>" +
            "<if test=\"repay_state!=null and repay_state!=''\"> repay_state=#{repay_state}, </if>" +
            "<if test=\"repay_month!=null and repay_month!=''\"> repay_month=#{repay_month}, </if>" +
            "<if test=\"repay_mmonth!=null and repay_mmonth!=''\"> repay_mmonth=#{repay_mmonth}, </if>" +
            "<if test=\"repayed_All_money !=null and repayed_All_money !=''\"> repayed_All_money=#{repayed_All_money}, </if>" +
            "<if test=\"residue_periods!=null and residue_periods!=''\"> residue_periods = #{residue_periods}, </if> " +
            "<if test=\"repay_month_interest!=null and repay_month_interest!=''\"> repay_month_interest = #{repay_month_interest}, </if> " +
            "<if test=\"repay_month_money!=null and repay_month_money!=''\"> repay_month_money = #{repay_month_money}, </if> " +
            "<if test=\"repay_date !=null and repay_date !=''\"> repayed_date= date_format(now(),'%Y-%m-%d'), " +
            "repay_date= date_format(DATE_ADD(#{repay_date},INTERVAL 1 MONTH),'%Y-%m-%d') </if>" +

            "  where repay_id = #{repay_id} </script>")
    int repayMoney(Map map);

    @Update("update tb_repay set " +
            "repay_state=#{repay_state}  where repay_id = #{repay_id}")
    int repayStatus(Map map);
    /**
     * 查询还款信息表
     * @param id
     * @return
     */
    @Select("select * from tb_repay where repay_id=#{repay_id}")
    List<Map> gettbRepay(Integer id);
    /**
     * 新增到还款记录表
     * @param map
     * @return
     */
    @Insert("insert into tb_repay_record(repay_id,GRZH,pName,repay_month_money,repay_month_interest,repayed_date,repay_state) " +
            " values(#{repay_id},#{GRZH},#{pName},#{repay_month_money},#{repay_month_interest},#{repayed_date},#{repay_state})")
    int repayRecord(Map map);

    /**
     * 贷款明细(分页)
     * @param map 可选项  用于拼接查询条件
     * @return
     */
    @Select("<script>select * from tb_repay where 1=1 " +
            "<if test=\"GRZH!=null and GRZH!=''\">  and GRZH=#{GRZH} </if>" +
            "<if test=\"pName!=null and pName!=''\">  and pName=#{pName} </if>" +
            " limit #{start},#{end} </script>")
    List<Map> getRepay(Map map);

    /**
     * 获取分页数据数量（贷款明细）
     * @param map
     * @return
     */
    @Select("<script>select count(*) from tb_repay where 1=1 " +
            "<if test=\"GRZH!=null and GRZH!=''\">  and GRZH=#{GRZH} </if>" +
            "<if test=\"pName!=null and pName!=''\">  and pName=#{pName} </if>" +
            "</script>")
    int getRepayCount(Map map);

    /**
     * 逾期信息
     * @param map
     * @return
     */
    @Select("select timestampdiff(day,#{date},date_format(now(),'%Y-%m-%d')) as over_days," +
            " (timestampdiff(month,#{date},date_format(now(),'%Y-%m-%d'))+1)*(#{money}) as over_money")
    Map getOverPay(Map map);
}
