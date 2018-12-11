package com.aaa.gjj.service;

import java.util.List;
import java.util.Map;

/**
 * className:AccountInformationService
 * discription:
 * author:qcm
 * createTime:2018-12-09 18:51
 */
public interface AccountInformationService {
    //个人信息
    List<Map> AccountInformation(Map map);
    int  AccountInformationCount(Map map);
    //个人信息修改
    List<Map>  particulars(Map map);
    //个人信息修改完毕提交按钮
    int  modification(Map map);
    int  modification2(Map map);

    //单位信息
    List<Map> UnitInformation(Map map);
    int  UnitInformationCount(Map map);
    //单位信息修改按钮
    List<Map> tan(Map map);
    //单位信息提交更改按钮
    int  unitModification(Map map);
    int  unitModification2(Map map);
}
