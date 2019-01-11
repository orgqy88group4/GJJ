package com.aaa.gjj.service;

import java.util.List;
import java.util.Map;

/**
 * className:LoanService
 * discription:
 * author:qcm
 * createTime:2018-12-18 11:04
 */
public interface LoanService {
    /**
     * 查询贷款记录细信息表，返回贷款人员账号
     * @return
     */
    List<Map> getgrzh();

    /**
     * 根据前台传来的个人账号，来查询账户信息
     * @param map
     * @return
     */
    List<Map> getList(Map map);

    /**
     * 个人贷款模块 --共同借款人--前台传来共同贷款人账号
     * @param map
     * @return
     */
    List<Map> getTogether(Map map);

    /**
     * 把贷款数据存到数据库里面
     * @param map
     * @return
     */
    int getInfo(Map map);

    /**
     * 贷款初审
     * @param map
     * @return
     */
    List<Map> getPage(Map map);

    /**
     * 查询贷款初审分页数据的总数量 (如果分页数据里面带有条件 )
     * @param map
     * @return
     */
    int getPageCount(Map map);

    /**
     * 点击详细按钮，前台传来个人账户，根据个人账户来查询详细信息
     * @param map
     * @return
     */
    List<Map> getPersonInfo(Map map);

    /**
     * 点击审核通过按钮，将数据插入到终审表 修改状态为2
     * @param map
     * @return
     */
    int PassCheck(Map map);

    /**
     * 初审驳回按钮，将数据插入到终审表 插入时状态为3
     * @param map
     * @return
     */
    int reject(Map map);

    /**
     * 初审通过，将状态修改位2
     * @param map
     * @return
     */
    int PassChangeState(Map map);

    /**
     * 贷款终审
     * @param map
     * @return
     */
    List<Map> getPageFinal(Map map);

    /**
     * 查询贷款终审分页数据的总数量 (如果分页数据里面带有条件 )
     * @param map
     * @return
     */
    int getPageFinalCount(Map map);

    /**
     * 点击贷款终审，确认驳回按钮，状态变为5
     * @param map
     * @return
     */
    int SureReject(Map map);

    /**
     * 终审通过之后  把信息存进还款明细 计算出应还本金 利息等
     * @param map
     * @return
     */
    int FinalPass(Map map);

    /**
     * 终审通过、修改贷款终审表的相关状态
     * @param map
     * @return
     */
    int passFinal(Map map);

    /**
     * 销户审核  后台查询信息
     * @param map
     * @return
     */
    List<Map> QueryAuditInfomation(Map map);

    /**
     * 销户审核  后台查询信息总条数
     * @param map
     * @return
     */
    int QueryAuditInfomationCount(Map map);

    /**
     * 操作审核表 点击通过按钮获取前台传来的值  传到后台用来修改个人账户状态
     * @param map
     * @return
     */
    int PersonAccountState(Map map);

    /**
     * 修改审核表中的audit_name字段，给位通过
     * @param map
     * @return
     */
    int unsealAuditUpdate(Map map);

    /**
     * 修改公司缴存人数
     * @param map
     * @return
     */
    int unitsUpdate(Map map);

    /**
     * 销户，封存 修改公司缴存总金额
     * @param map
     * @return
     */
    int unitsUpdateMoney(Map map);

    /**
     * 启封 修改公司缴存总金额
     * @param map
     * @return
     */
    int unitsMoney(Map map);

    /**
     * 查询审核表中状态位1或2的信息 放入前台
     * @return
     */
    List<Map> unsealAuditSelect(Map map);

    /**
     * 1 为启封  2为封存  0为销户
     * 查询审核表中状态位1和2的
     * @param map
     * @return
     */
    int unsealAuditSelectCount(Map map);

    /**
     * 查询人员转移审核表信息
     * @param map
     * @return
     */
    List<Map> uditTransfer(Map map);

    /**
     * 查询转移人员信息总条数
     * @param map
     * @return
     */
    int uditTransferCount(Map map);

    /**
     * 查询出要转入的公司
     * @param map
     * @return
     */
    int rejects(Map map);

    /**
     * 根据前台传来的id，查询审核表中的信息
     * @param map
     * @return
     */
    int CheckAuditTable(Map map);

    /**
     * 添加到审核记录表
     * @param map
     * @return
     */
    int addInfoToRecord(Map map);

    /**
     * 个人贷款驳回，删除记录
     * @param map
     * @return
     */
    int RejectDelete(Map map);

    /**
     * 通过转入公司名称查询到要转入公司id
     * @param name
     * @return
     */
    List<Map> seletId(String name);

    /**
     * 根据获取的个人账号查询到自己公司id 在修改为要转入的公司id
     * @param GRZH
     * @param uid
     * @return
     */
    int upDateId(String GRZH, int uid);

    /**
     * 公司缴存人数  查询到当前公司账户状态为正常的个数  把公司账户信息表 应缴纳人数修改了
     * @param uname
     * @return
     */
    int unitsUpdatec(String uname);

    /**
     * 获取还款信息表
     * @param id
     * @return
     */
    List<Map> getrepayinfo(Integer id);
    /**
     * 终审通过之后  把还款信息表内容添加到还款表  计算出应还本金 利息 应还等
     * @param map
     * @return
     */
    int insertRepay(Map map);

    /**
     * 查询还款信息表
     * @param GRZH
     * @return
     */
    List<Map> updateGRZHBalance(String GRZH);

    /**
     * 终审通过之后 修改个人账户余额
     * @param map
     * @return
     */
    int updateBalance(Map map);
}
