package com.aaa.gjj.service;

import java.util.List;
import java.util.Map;

/**
 * className:CompanyService
 * discription:
 * author:qcm
 * createTime:2018-12-21 15:01
 */
public interface CompanyService {
    /**
     * 基数调整页面数据
     * @param map
     * @return
     */
    List<Map> UnitAccountInformation(Map map);

    /**
     * 基数调整页面总数量
     * @param map
     * @return
     */
    int UnitAccountInformationCount(Map map);

    /**
     * 点击比例变更按钮，前台传来单位账号
     * @param map
     * @return
     */
    List<Map> getStaffInfo(Map map);

    /**
     * 基数调整，保存调整按钮
     * @param map
     * @return
     */
    int updateMoney(Map map);

    /**
     * 补缴办理页面，补缴办理按钮
     * @param map
     * @return
     */
    Map getUnitPayInfo(Map map);

    /**
     * 补缴办理人员信息
     * @param map
     * @return
     */
    List<Map> getPersonPayInfo(Map map);

    /**
     * 补缴按钮
     * @param map
     * @return
     */
    int updatePersonInfo(Map map);

    /**
     * 批量补缴按钮
     * @param GRZH
     * @return
     */
    int updatePersonInfo99(int GRZH);
    /**
     * 根据单位账号，查找单位名称、缴纳的比例
     * @param DWZH
     * @return
     */
    List<Map> getUnitInfo(String DWZH);

    /**
     * 个人注册时，把个人基本信息存到个人信息表中
     * @param map
     * @return
     */
    int addPersonInfo(Map map);

    /**
     * 得到单位id ，应缴纳金额
     * @param map
     * @return
     */
    int addPersonAccountInfo(Map map);

    /**
     * 公司名称唯一效验
     * @param map
     * @return
     */
    int getUnitInfoList(Map map);

    /**
     * 添加公司信息
     * @param map
     * @return
     */
    int unitRegister(Map map);

    /**
     *  单位注册时的账户信息
     * @param map
     * @return
     */
    int unitAccount(Map map);
}
