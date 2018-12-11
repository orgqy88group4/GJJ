package com.aaa.gjj.service;

import com.aaa.gjj.dao.TheCompanyBusinessDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:TheCompanyBusinessServiceImpl
 * discription:
 * author:qcm
 * createTime:2018-12-10 21:08
 */
@Service
public class TheCompanyBusinessServiceImpl implements TheCompanyBusinessService {

    @Autowired
    private TheCompanyBusinessDao theCompanyBusinessDao;

    @Override
    public int unitRegister(Map map) {
        return theCompanyBusinessDao.unitRegister(map);
    }

    @Override
    public int unitAccount(Map map, int uid) {
        return theCompanyBusinessDao.unitAccount(map,uid);
    }

    @Override
    public List<Map> getUnitInfoList(Map map) {
        return theCompanyBusinessDao.getUnitInfoList(map);
    }
}
