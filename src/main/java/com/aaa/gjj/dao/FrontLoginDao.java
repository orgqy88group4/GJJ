package com.aaa.gjj.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:FrontLoginDao
 * discription:
 * author:dujihu
 * createTime:2018-12-11 16:47
 */
public interface FrontLoginDao {
    /**
     * 查询个人账户
     * @param map
     * @return
     */
    @Select("select a.*,b.* from tb_paccountutil a,tb_unit b where GRZH=#{grzh} and GRMM=#{grmm} ")
    List<Map> getGRAccount(Map map);

    /**
     * 查询单位账户
     * @param map
     * @return
     */
    @Select("select a.*,b.* from tb_paccountutil a,tb_unit b where DWXZ=#{DWXZ} and ULegalCard=#{ULegalCard} ")
    List<Map> getDWAccount(Map map);



}
