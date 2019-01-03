package com.aaa.gjj.service;

import com.aaa.gjj.dao.FrontLoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:FrontLoginServiceImpl
 * discription:
 * author:dujihu
 * createTime:2018-12-11 17:11
 */
@Service
public class FrontLoginServiceImpl implements FrontLoginService {
    @Autowired
    private FrontLoginDao frontLoginDao;


    @Override
    public List<Map> getDWAccount(Map map) {
        return frontLoginDao.getDWAccount(map);
    }

    @Override
    public List<Map> getGRAccount(Map map) {

        return frontLoginDao.getGRAccount(map);
    }
}
