package com.aaa.gjj.dao;

import com.aaa.gjj.entity.Node;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * className:RoleDao
 * discription:
 * author:lin
 * createTime:2018-12-15 09:25
 */
public interface RoleDao {

    /**
     * 带参分页查询
     * @param map
     * @return
     * 如果使用注解的方式，动态sql必须在标签<script></script>
     * 如果使用<script></script>标签，mybatis大于小于,必须使用&gt;  &lt;
     */
    @Select("<script>select roleId,roleName,roleDesc,roleFlag from tb_role  \n" +
            "where 1=1 " +
            "<if test=\"roleId!=null and roleId!=''\"> and roleId=#{roleId} </if>" +
            "<if test=\"roleName!=null and roleName!=''\"> and roleName like concat('%',#{roleName},'%') </if>" +
            "<if test=\"roleDesc!=null and roleDesc!=''\"> and roleDesc like concat('%',#{roleDesc},'%') </if>" +
            "<if test=\"roleFlag!=null and roleFlag!=''\">  and roleFlag =#{roleFlag} </if>" +
            " limit #{start},#{end} </script>")
    List<Map> getPageByParam(Map map);

    /**
     * 查询分页总数量
     * @param map
     * @return
     */
    @Select("<script> select count(*) from tb_role <where>" +
            "<if test=\"roleId!=null and roleId!=''\"> and roleId=#{roleId}</if>" +
            "<if test=\"roleName!=null and roleName!=''\"> and roleName like concat('%',#{roleName},'%')</if>" +
            "<if test=\"roleDesc!=null and roleDesc!=''\"> and roleDesc like concat('%',#{roleDesc},'%')</if>" +
            "<if test=\"roleFlag!=null and roleFlag!=''\">  and roleFlag =#{roleFlag}</if>" +
            " </where></script>")
    int getPageCount(Map map);

    /**
     * 权限列表添加
     * @param map
     * @return
     */
    @Insert("insert into tb_role values(null,#{roleName},#{roleDesc},#{roleFlag})")
    int add(Map map);

    /**
     * 根据ID获取集合对象
     * @param map
     * @return
     */
    @Select("<script> select * from tb_role <where>" +
            "<if test=\" roleId !=null and roleId!='' \"> and roleId=#{roleId}</if>" +
            "<if test=\" roleName !=null and roleName!='' \"> and roleName like concat('%',#{roleName},'%')</if>" +
            "<if test=\" roleFlag !=null and roleFlag!='' \">  and roleFlag =#{roleFlag}</if>" +
            " </where></script>")
    Map<String,Object> getById(Map map);

    /**
     * 角色信息修改
     * @param map
     * @return
     */
    @Update("update tb_role set roleId=#{roleId},roleName=#{roleName},roleDesc=#{roleDesc},roleFlag=#{roleFlag} where roleId=#{roleId}")
    int update(Map map);

    /**
     * 角色信息删除
     * @param roleId
     * @return
     */
    @Delete("delete from tb_role where roleId=#{roleId}")
    int delete(int roleId);

    /**
     * 批量删除
     * @param list
     * @return
     */
    @Delete("<script>delete from tb_role where roleId in " +
            "<foreach collection='list' item='roleId' open='(' close=')' separator=','>#{roleId}</foreach>"+
            "</script>")
    int batchDelete(List list);

    /**
     * 带选择的权限树（用于授权勾选）
     * @param roleId
     * @return
     */
    @Select("select roleid,powerid from tb_role_power where roleId=#{roleId}")
    List<Map>  powersByRoleId(String roleId);

    /**
     * 带选择的权限树（用于授权勾选）
     * @return
     */
    @Select("select id, name as label, iconcls as iconClass, url, parentid pid from tb_treemenu")
    List<Map>  powerMapList();

    /**
     * 添加角色权限关联表
     * @param
     * @return
     */
    @Insert("insert into tb_role_power values(null,#{roleId},#{powerid},now())")
    int addRolePower(@Param("roleId") String roleId, @Param("powerid") String powerid);
    /**
     * 删除原来关联的所有权限
     * @param roleId
     * @return
     */
    @Delete("delete from tb_role_power where roleId=#{roleId}")
    int delPowersByRoleId(String roleId);

    /*--------------------------------------------账户管理-----------------------------------------*/

    /**
     * 带参分页查询
     * @param map
     * @return
     * 如果使用注解的方式，动态sql必须在标签<script></script>
     * 如果使用<script></script>标签，mybatis大于小于,必须使用&gt;  &lt;
     */
    @Select("<script>select account_Id, userName,roleId,account_name,create_date,post,passWord from tb_account  \n" +
            "where 1=1 " +
            "<if test=\"account_Id!=null and account_Id!=''\"> and account_Id=#{account_Id} </if>" +
            "<if test=\"userName!=null and userName!=''\"> and userName like concat('%',#{userName},'%') </if>" +
            "<if test=\"account_name!=null and account_name!=''\"> and account_name like concat('%',#{account_name},'%') </if>" +
            "<if test=\"post!=null and post!=''\"> and post like concat('%',#{post},'%') </if>" +
            " limit #{start},#{end} </script>")
    List<Map> getPageByParamC(Map map);

    /**
     * 查询分页总数量
     * @param map
     * @return
     */
    @Select("<script> select count(*) from tb_account <where>" +
            "<if test=\"account_Id!=null and account_Id!=''\"> and account_Id=#{account_Id} </if>" +
            "<if test=\"userName!=null and userName!=''\"> and userName like concat('%',#{userName},'%') </if>" +
            "<if test=\"account_name!=null and account_name!=''\"> and account_name like concat('%',#{account_name},'%') </if>" +
            " </where></script>")
    int getPageCountC(Map map);

    /**
     * 账户添加
     * @param map
     * @return
     */
    @Insert("insert into tb_account values(null,#{userName},#{passWord},#{roleId},#{account_name},date_format(#{create_date},'%Y-%m-%d'),#{post} )")
    int addAccount(Map map);

    /**
     * 账户信息修改 account_Id, userName,roleId,account_name,create_date,post,passWord
     * @param map
     * @return
     */
    @Update("update tb_account set account_Id=#{account_Id},userName=#{userName},roleId=#{roleId},account_name=#{account_name},passWord=#{passWord},create_date=date_format(#{create_date},'%Y-%m-%d'),post=#{post} where roleId=#{roleId}")
    int updateAccount(Map map);

    /**
     * 账户删除
     * @param account_Id
     * @return
     */
    @Delete("delete from tb_account where account_Id=#{account_Id}")
    int deleteAccount(int account_Id);
}
