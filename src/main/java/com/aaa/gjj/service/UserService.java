package com.aaa.gjj.service;

import com.aaa.gjj.entity.User;


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

    /**
     * 通过id找用户
     * @return
     */
    User getUserById(Integer empNo);
}
