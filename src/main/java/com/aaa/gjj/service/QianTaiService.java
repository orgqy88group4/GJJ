package com.aaa.gjj.service;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:QianTaiService
 * discription:
 * author:qcm
 * createTime:2018-12-26 20:28
 */
public interface QianTaiService {
    /**
     * 个人账号登录前台
     * @param map
     * @return
     */
    List<Map> ChackPersonLogin(Map map);

    /**
     * 查询缴存记录
     * @param map
     * @return
     */
    List<Map> SelectCheckJiLu(Map map);

    /**
     * 查询缴存记录
     * @param map
     * @return
     */
    List<Map> SelectCheckDaiKuanJiLu(Map map);

    /**
     * 单位账号登录前台
     * @param map
     * @return
     */
    List<Map> ChackUnitLogin(Map map);

    /**
     * 单位账号登录前台
     * @param map
     * @return
     */
    List<Map> ChackUnitLogin1(Map map);

    /**
     * 单位账号缴纳记录
     * @param map
     * @return
     */
    List<Map> UnitJiaoNaJiLu(Map map);

    /**
     * 查看单个小新闻
     * @param id
     * @return
     */
    List<Map> Selnews(int id);

    //=================新闻模块=============================================

    /**
     * 查询新闻信息
     * @param map
     * @return
     */
    List<Map> informationList(Map map);

    /**
     * 查询新闻总数量
     * @param map
     * @return
     */
    int informationListCount(Map map);

    /**
     * 修改新闻
     * @param map
     * @return
     */
    int upInformation(Map map);

    /**
     * 新闻添加
     * @param map
     * @return
     */
    int information(Map map);

    /**
     * 新闻删除
     * @param id
     * @return
     */
    int delInformation(int id);

    /**
     * 把后台发布的新闻传输到前台
     * @param tid
     * @return
     */
    List<Map> selnewgall(int tid);
    /**
     * 核实银行卡信息是否正确
     */
    List<Map> YHK(Map map);

    /**
     * 还款后更新数据
     */
    int XGSJ(Map map);

    /**
     * 单位汇缴后更新数据
     */
    int DWHJUpdate(Map map);
    /**
     * 单位汇缴后更新详细表数据
     */
    int DWHJUpdateXX(Map map);
}
