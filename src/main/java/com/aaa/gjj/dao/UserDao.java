package com.aaa.gjj.dao;

import com.aaa.gjj.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * className:UserDao
 * discription:
 * author:fhm
 * createTime:2018-12-06 21:11
 */
public interface UserDao {
    /**
     * 根据用户名找用户
     * @param ename
     * @return
     */
    @Select("select empno,ename,job from tbq_user4 where ename=#{ename}")
    List<User> getUserByuserName(String ename);

    /**
     * 通过id找用户
     * @return
     */
    @Select("select empno,ename,job,perms from tbq_user4 where empno=#{empNo}")
    List<User> getUserById(Integer empNo);
}
