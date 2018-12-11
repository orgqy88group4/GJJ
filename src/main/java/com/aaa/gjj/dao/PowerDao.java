package com.aaa.gjj.dao;

import com.aaa.gjj.entity.Node;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * className:PowerDao
 * discription:后台树
 * author:zz
 * createTime:2018-12-11 09:22
 */
public interface PowerDao {

    /**
     * 权限列表查询
     * @return
     */
    @Select("select id, name as label, iconcls as iconClass, url, parentid pid from tb_treemenu")
    List<Node> getList();
}
