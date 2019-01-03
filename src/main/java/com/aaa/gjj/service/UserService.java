package com.aaa.gjj.service;

import com.aaa.gjj.entity.Person;
import com.aaa.gjj.entity.User;

import java.util.List;
import java.util.Map;

/**
 * className:UserService
 * discription:
 * author:fhm
 * createTime:2018-12-06 21:24
 */
public interface UserService {
    /**
     * 根据用户名找用户
     * @param ename
     * @return
     */
    User getUserByuserName(String ename);

    //1211
    /**
     * 查询待审核表  查询审核信息  前台回去的值对比 相同操作的人
     * @param map
     * @return
     */
    List<Map> submitVerify1(Map map);


    /**
     * 下拉框选中公司 获取ID  查询公司的受托银行
     * @param id
     * @return
     */
    List<Map> Trustee1(int id);

    /**
     * 根据用户名称查询  查询转移公司
     * @param id
     * @return
     */
    List<Map> UserIDSelect1(String id);


    /**
     * 用户列表
     * @return
     */
    List<Person> UserSelect1(Map map);

    /**
     * 用户分页列表总数量
     * @param map
     * @return
     */
    int UserCount12(Map map);


    /**
     * 获取分页数据
     * @param map
     * @return
     */
    List<Person> getPage1(Map map);

    /**
     * 获取分页数据总数量
     * @param map
     * @return
     */
    int getPageCount1(Map map);

    /**
     * 根据id查询数据 个人信息  然后添加到待转移列表
     * @param id
     * @return
     */
    List<Map> shift(int id);
    /**
     * 根据id查询数据 个人信息  然后添加到待转移列表
     * @param id
     * @return
     */
    List<Map> shift1(int id);

    /**
     * 下拉框选中公司 获取ID  查询公司的受托银行   添加到审核表中
     * @param id
     * @return
     */
    List<Map> uname(int id);
    /**
     * 下拉框选中公司 获取ID  查询公司的受托银行   添加到审核表中
     * @param id
     * @return
     */
    List<Map> uname1(int id);

    /**
     * 把获取到的人员信息存入带转移表中
     * @param map
     * @return
     */
    int addShift1(Map map);
    /**
     * 公司动态下拉框
     * @return
     */
    List<Map> select();
    /**
     * 根据用户名找用户
     * @param userName
     * @return
     */
    List<Map> selByUserName(String userName);

    /**
     * 通过id找用户
     * @return
     */
    User getUserById(Integer empNo);
}
