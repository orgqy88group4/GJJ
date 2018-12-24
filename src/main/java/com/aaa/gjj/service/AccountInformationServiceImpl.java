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
    public int unitModification21(Map map) {
        return Account.unitModification21(map);
    }

    //单位信息提交更改按钮
    @Override
    public int unitModification1(Map map) {
        return Account.unitModification1(map);
    }

    //单位信息修改按钮
    @Override
    public List<Map> tan(Map map) {
        return Account.tan(map);
    }
    //单位信息修改按钮
    @Override
    public List<Map> tan1(Map map) {
        return Account.tan1(map);
    }

    //单位信息
    @Override
    public List<Map> UnitInformation1(Map map) {
        return Account.UnitInformation1(map);
    }

    //单位信息
    @Override
    public int UnitInformationCount1(Map map) {
        List<Map> pageCount = Account.UnitInformationCount1(map);
        if(pageCount!=null&&pageCount.size()>0){
            return Integer.parseInt(pageCount.get(0).get("cnt")+"");
        }
        return 0;
    }
    //个人信息查询总条数
    @Override
    public List<Map> AccountInformation1(Map map) {
        return Account.AccountInformation1(map);
    }
    //个人信息修改完毕提交按钮
    @Override
    public int modification(Map map) {
        return Account.modification(map);
    }
    //个人信息修改完毕提交按钮
    @Override
    public int modification1(Map map) {
        return Account.modification1(map);
    }
    //个人信息修改完毕提交按钮
    @Override
    public int modification21(Map map) {
        return Account.modification21(map);
    }

    //个人信息修改
    @Override
    public List<Map> particulars1(Map map) {
        return Account.particulars1(map);
    }
    //个人信息查询总条数
    @Override
    public int AccountInformationCount1(Map map) {
        List<Map> pageCount = Account.AccountInformationCount1(map);
        if(pageCount!=null&&pageCount.size()>0){
            return Integer.parseInt(pageCount.get(0).get("cnt")+"");
        }
        return 0;
    }
}
