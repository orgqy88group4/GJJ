package com.aaa.gjj.service;

import com.aaa.gjj.dao.RoleDao;
import com.aaa.gjj.entity.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:RoleServiceImpl
 * discription:
 * author:lin
 * createTime:2018-12-15 09:26
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Map> getPageByParam(Map map) {
        return roleDao.getPageByParam(map);
    }

    @Override
    public List<Map> getPowerCheckedTree(String roleId) {
        List<Map> powersByRoleId = roleDao.powersByRoleId(roleId);
        return powersByRoleId;
    }

    @Override
    public int addRolePower(Map paramMap) {
        String ids=paramMap.get("ids").toString();
        String roleId = paramMap.get("roleId").toString();
        boolean suc=false;
        //删除原来的关联项目
        roleDao.delPowersByRoleId(roleId);
        //批量添加角色和权限关联
        if(ids!=null&&!"".equals(ids)){
            String[] pids = ids.split(",");
            for(String id:pids){
                int i=roleDao.addRolePower(roleId,id);
                if(i==-1)
                    suc=true;
            }
        }
        if(suc){
            return -1;
        }
        return 0;
    }

    @Override
    public int getPageCount(Map map) {
        return roleDao.getPageCount(map);
    }


    @Override
    public int add(Map map) {
        return roleDao.add(map);
    }

    @Override
    public int update(Map map) {
        return roleDao.update(map);
    }

    @Override
    public int batchDelete(String ids) {
        String[] idsArray = ids.split(",");// [1,2,3,4]
        List list = new ArrayList();
        for (String s : idsArray) {
            list.add(s);
        }
        return roleDao.batchDelete(list);
    }

    @Override
    public int delete(int roleId) {
        return roleDao.delete(roleId);
    }

    @Override
    public Map<String, Object> getById(Map map) {
        return roleDao.getById(map);
    }

    /*--------------------------------------------账户管理-----------------------------------------*/
    @Override
    public List<Map> getPageByParamC(Map map) {
        return roleDao.getPageByParamC(map);
    }

    @Override
    public int getPageByParamC1(Map map) {
        List<Map> pageByParamC1 = roleDao.getPageByParamC1();
        for (Map map1 : pageByParamC1) {
            if (map1.get("userName").equals(map.get("userName"))){//重复不能使用，向前台返回1
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int getPageCountC(Map map) {
        return roleDao.getPageCountC(map);
    }

    @Override
    public int addAccount(Map map) {
        return roleDao.addAccount(map);
    }

    @Override
    public int updateAccount(Map map) {
        return roleDao.updateAccount(map);
    }

    @Override
    public int deleteAccount(int account_Id) {
        return roleDao.deleteAccount(account_Id);
    }
}
