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
    List<Map> AccountInformation1(Map map);
    int  AccountInformationCount1(Map map);
    //个人信息修改
    List<Map>  particulars1(Map map);
    //个人信息修改完毕提交按钮
    int  modification(Map map);
    //个人信息修改完毕提交按钮
    int  modification1(Map map);
    //    int  modification2(Map map);
    int  modification21(Map map);

    //单位信息
    List<Map> UnitInformation1(Map map);

    int  UnitInformationCount1(Map map);
    //单位信息修改按钮
    List<Map> tan(Map map);
    //单位信息修改按钮
    List<Map> tan1(Map map);

    //单位信息提交更改按钮
    int  unitModification1(Map map);

    int  unitModification21(Map map);
}
