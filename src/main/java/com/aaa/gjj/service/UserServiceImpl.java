package com.aaa.gjj.service;

import com.aaa.gjj.dao.UserDao;
import com.aaa.gjj.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * className:UserServiceImpl
 * discription:
 * author:fhm
 * createTime:2018-12-06 21:24
 */
@Service
public class UserServiceImpl implements UserService {
    //依赖注入
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByuserName(String ename) {
        List<User> userList = userDao.getUserByuserName(ename);
        //System.out.println(userList.get(0).getEmpNo()+"----------"+userList.get(0).getEname());
        if (userList!=null&&userList.size()>0){
            return userList.get(0);
        }
        return null;
    }

    @Override
    public User getUserById(Integer empNo) {
        List<User> userList = userDao.getUserById(empNo);
        if (userList!=null&&userList.size()>0){
            return userList.get(0);
        }
        return null;
    }
}
