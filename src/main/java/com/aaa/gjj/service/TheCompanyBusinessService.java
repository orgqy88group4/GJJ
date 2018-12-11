package com.aaa.gjj.service;

import java.util.List;
import java.util.Map;

/**
 * className:TheCompanyBusinessService
 * discription:
 * author:qcm
 * createTime:2018-12-10 21:07
 */
public interface TheCompanyBusinessService {
    /**
     * 单位开户公司效验
     * @param map
     * @return
     */
    List<Map> getUnitInfoList(Map map);

    /**
     * 单位开户  保存按钮
     * @param map
     * @return
     */
    int unitRegister(Map map);
    int unitAccount(Map map, int uid);
}
