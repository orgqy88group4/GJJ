package com.aaa.gjj.service;

import com.aaa.gjj.entity.Node;

import java.util.List;
import java.util.Map;

/**
 * className:PowerService
 * discription:
 * author:zz
 * createTime:2018-12-11 09:35
 */
public interface PowerService {

    /**
     * 列表查询方法
     * @return
     */
    List<Node> getList(Map map);

    /**
     * 权限添加
     * @param map
     * @return
     */
    int add(Map map);

    /**
     * 权限修改
     * @param map
     * @return
     */
    int update(Map map);

    /**
     *  权限删除
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 根据ID获取集合对象
     * @param id
     * @return
     */
    Map<String,Object> getById(int id);

    /**
     * 根据ID获取父节点信息
     * @param id
     * @return
     */
    Map<String,Object> getParent(int id);
}
