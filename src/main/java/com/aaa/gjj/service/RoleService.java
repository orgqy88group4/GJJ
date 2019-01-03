package com.aaa.gjj.service;

import com.aaa.gjj.entity.Node;

import java.util.List;
import java.util.Map;

/**
 * className:RoleService
 * discription:角色管理   账户管理
 * author:lin
 * createTime:2018-12-15 09:25
 */
public interface RoleService {

    /**
     * 分页
     * @param map
     * @return
     */
    List<Map> getPageByParam(Map map);

    /**
     * 分页总数量
     * @param map
     * @return
     */
    int getPageCount(Map map);
    /**
     * 权限列表添加
     * @param map
     * @return
     */
    int add(Map map);

    /**
     * 根据ID获取集合对象
     * @param map
     * @return
     */
    Map<String,Object> getById(Map map);
    /**
     * 角色信息修改
     * @param map
     * @return
     */
    int update(Map map);

    /**
     * 角色信息删除
     * @param roleId
     * @return
     */
    int delete(int roleId);
    /**
     * 批量删除
     * @param ids
     * @return
     */
    int batchDelete(String ids);
    /**
     * 添加角色权限关联表
     * @param map
     * @return
     */
    int addRolePower(Map map);

    /**
     * 带选择的权限树（用于授权勾选）
     * @param roleId
     * @return
     */
    List<Map>  getPowerCheckedTree(String roleId);

    /*--------------------------------------------账户管理-----------------------------------------*/
    /**
     * 分页
     * @param map
     * @return
     */
    List<Map> getPageByParamC(Map map);

    /**
     * 添加账户名时防止用户名重复
     * @return
     */
    int getPageByParamC1(Map map);

    /**
     * 分页总数量
     * @param map
     * @return
     */
    int getPageCountC(Map map);

    /**
     * 账户添加
     * @param map
     * @return
     */
    int addAccount(Map map);

    /**
     * 账户信息修改
     * @param map
     * @return
     */
    int updateAccount(Map map);

    /**
     * 账户删除
     * @param account_Id
     * @return
     */
    int deleteAccount(int account_Id);
}
