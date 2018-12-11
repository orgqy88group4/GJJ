package com.aaa.gjj.service;

import com.aaa.gjj.dao.AccountInformationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:AccountInformationServiceImpl
 * discription:
 * author:qcm
 * createTime:2018-12-09 18:52
 */
@Service
public class AccountInformationServiceImpl implements AccountInformationService {
    @Autowired
    private AccountInformationDao Account;

    @Override
    public int unitModification2(Map map) {
        return Account.unitModification2(map);
    }

    //单位信息提交更改按钮
    @Override
    public int unitModification(Map map) {
        return Account.unitModification(map);
    }

    //单位信息修改按钮
    @Override
    public List<Map> tan(Map map) {
        return Account.tan(map);
    }

    //单位信息
    @Override
    public List<Map> UnitInformation(Map map) {
        int pageNo = Integer.parseInt(map.get("page") == null?"1":map.get("page")+"");
        int pageSize = Integer.parseInt(map.get("rows") == null?"10":map.get("rows")+"");
        int start = (pageNo-1)*pageSize;
        map.put("start",start);
        map.put("rows",pageSize);
        //map.remove("page");
        //map.remove("rows");
        return Account.UnitInformation(map);
    }
    //单位信息
    @Override
    public int UnitInformationCount(Map map) {
        List<Map> pageCount = Account.UnitInformationCount(map);
        if(pageCount!=null&&pageCount.size()>0){
            return Integer.parseInt(pageCount.get(0).get("cnt")+"");
        }
        return 0;
    }
    //个人信息查询总条数
    @Override
    public List<Map> AccountInformation(Map map) {
        int pageNo = Integer.parseInt(map.get("page") == null?"1":map.get("page")+"");
        int pageSize = Integer.parseInt(map.get("rows") == null?"10":map.get("rows")+"");
        int start = (pageNo-1)*pageSize;
        map.put("start",start);
        map.put("rows",pageSize);
      //  map.remove("page");
      //  map.remove("rows");
        return Account.AccountInformation(map);
    }
    //个人信息修改完毕提交按钮
    @Override
    public int modification(Map map) {
        return Account.modification(map);
    }
    //个人信息修改完毕提交按钮
    @Override
    public int modification2(Map map) {
        return Account.modification2(map);
    }

    //个人信息修改
    @Override
    public List<Map> particulars(Map map) {
        return Account.particulars(map);
    }
    //个人信息查询总条数
    @Override
    public int AccountInformationCount(Map map) {
        List<Map> pageCount = Account.AccountInformationCount(map);
        if(pageCount!=null&&pageCount.size()>0){
            return Integer.parseInt(pageCount.get(0).get("cnt")+"");
        }
        return 0;
    }
}
