package com.aaa.gjj.service;

import com.aaa.gjj.dao.UserDao;
import com.aaa.gjj.entity.Person;
import com.aaa.gjj.entity.User;
import com.sun.xml.internal.xsom.impl.scd.Iterators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    //1211
    @Override
    public List<Map> submitVerify1(Map map) {
        return userDao.submitVerify1(map);
    }


    @Override
    public List<Map> Trustee1(int id) {
        return userDao.Trustee1(id);
    }

    @Override
    public List<Map> UserIDSelect1(String id) {
        return userDao.UserIDSelect1(id);
    }

    @Override
    public List<Map> UserSelect1(Map map) {
        System.out.println("======"+map);
        return userDao.UserSelect1(map);
    }

    @Override
    public int UserCount12(Map map) {
        List<Map> pageCount = userDao.UserCount12(map);
        //判断总数量列表是否有值
        if(pageCount!=null&&pageCount.size()>0){
            return Integer.parseInt(pageCount.get(0).get("cnt")+"");
        }
        return 0;
    }

    @Override
    public List<Person> getPage1(Map map) {
        List<Map> page = userDao.getPage1(map);
        System.out.println("page====="+page);
        List<Person> userList = new ArrayList<Person>(); //自定义一个集合把分页查询的值放入  把状态转为中文

        if(page!=null&page.size()>0){		//把状态的值转会为中文 穿到前台
            Person person = null;
            String pState = "";
            for (int i = 0; i < page.size(); i++) {
                Map maps = page.get(i);
                if(maps.get("peraccState").equals(1)){
                    pState="正常";
                }else if(maps.get("peraccState").equals(2)){
                    pState="封存";
                }else{
                    pState="销户";
                }
                person = new Person(maps.get("tb_pName")+"", maps.get("tb_idNUmber")+"",maps.get("dalance")+"",pState);
                userList.add(person);
            }
            return userList;
        }else {
            return null;
        }
    }

    @Override
    public int getPageCount1(Map map) {
        List<Map> pageCount = userDao.getPageCount1(map);
        //判断总数量列表是否有值
        if(pageCount!=null&&pageCount.size()>0){
            return Integer.parseInt(pageCount.get(0).get("cnt")+"");
        }
        return 0;
    }

    @Override
    public List<Map> shift(int id) {
        return userDao.shift(id);
    }
    @Override
    public List<Map> shift1(int id) {
        return userDao.shift1(id);
    }

    @Override
    public List<Map> uname(int id) {
        return userDao.uname(id);
    }
    @Override
    public List<Map> uname1(int id) {
        return userDao.uname1(id);
    }

    @Override
    public int addShift1(Map map) {
        return userDao.addShift1(map);
    }
    @Override
    public List<Map> select() {
        return userDao.select();
    }
}
