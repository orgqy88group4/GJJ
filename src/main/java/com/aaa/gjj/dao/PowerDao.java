package com.aaa.gjj.dao;

import com.aaa.gjj.entity.Node;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

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

    /**
     * 权限列表添加
     * @param map
     * @return
     */
    @Insert("insert into tb_treemenu values(null,#{name},#{url},#{parentid},#{iconcls})")
    int add(Map map);

    /**
     * 权限列表修改
     * @param map
     * @return
     */
    @Update("update tb_treemenu set id=#{id},name=#{name},url=#{url},iconcls=#{iconcls},parentid=#{parentid} where id=#{id}")
    int update(Map map);

    /**
     * 权限列表删除
     * @param id
     * @return
     */
    @Delete("delete from tb_treemenu where id=#{id}")
    int delete(Integer id);

    /**
     * 根据ID获取集合对象
     * @param id
     * @return
     */
    @Select("select * from tb_treemenu where id=#{id}")
    Map<String,Object> getById(int id);

    /**
     * 根据ID获取父节点信息
     * @param id
     * @return
     */
    @Select("select * from tb_treemenu where id=#{parentid}")
    Map<String,Object> getParent(int id);
}
