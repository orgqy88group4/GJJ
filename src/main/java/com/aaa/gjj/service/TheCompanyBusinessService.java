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
     * 获取单位账号信息表分页数据
     * @param map
     * @return
     */
    List<Map> UnitAccountInformation(Map map);


    /**
     * 获取单位账号信息总数量
     * @param map
     * @return
     */
    int UnitAccountInformationCount(Map map);
    /**
     * 汇缴时判断本月是否已经汇缴过了,查询明细查询表中当前单位汇缴的最近一个时间
     */
    List<Map> getRemitTime(Map map);
    /**
     * 汇缴办理完成之后把汇缴的信息存到明细查询表里
     */
    int  unitRemit(Map map);

    /**
     * 获取单位账号信息表分页数据
     * @param map
     * @return
     */
    List<Map> MXCXBiao1(Map map);

    /**
     * 获取单位账号信息总数量
     * @param map
     * @return
     */
    int MXCXBiaoCut1(Map map);
    /**
     * 比例变更时，查找员工的信息传到前台
     * @param map
     * @return
     */
    List<Map> getStaffInfo(Map map);
    /**
     * 修改比例
     */
    int updateRatio(Map map);
    /**
     * 挂账办理业务
     * @param map
     * @return
     */
    int GZBLyewu1(Map map);
}
