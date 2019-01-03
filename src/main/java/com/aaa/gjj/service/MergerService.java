package com.aaa.gjj.service;

import com.aaa.gjj.entity.PersonsAccountNumberState;
import com.aaa.gjj.entity.personDetail;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * className:MergerService
 * discription:
 * author:qcm
 * createTime:2018-12-12 11:51
 */
public interface MergerService {
    /**
     * 封存、启封、销户 分页
     * @param map
     * @return
     */
    List<PersonsAccountNumberState> SealedPage1(Map map);

    /**
     * 封存、启封 销户 查询信息条数
     * @param map
     * @return
     */
    List<Map> SealedPageCount1(Map map);


    /**
     * 判断唯一性校验  封存 启封 销户 校验   不能重复操作
     * @param map
     * @return
     */
    List<Map> verification(Map map);

    /**
     * 校验贷款的人 不能销户 和 封存
     * @param map
     * @return
     */
    List<Map> loansVerification(Map map);
    /**
     * 封存 启封 销户   操作弹出层查询信息
     * @param map
     * @return
     * element--controller放到service层
     */
    Map operationQuery(Map map);

    /**
     * 获取审核信息   放入审核表中
     * @param map
     * @return
     * element 放到service层
     */
    Map unsealAudit1(Map map, HttpSession session);

    /**
     * 获取审核信息  添加到审核表中
     * @param map
     * @return
     * element
     */
    int unsealAuditAdd1(Map map);

    /**
     * 获取分页数据
     * @param map
     * @return
     */
    List<personDetail> getPage1(Map map);

    /**
     * 获取分页数据总数量
     * @param map
     * @return
     */
    int getPageCount1(Map map);


    //12121648查看审批
    /**
     * 审批工作类别查询
     * @return
     */
    List<Map> accraditation1();

    /**
     * 查询贷款记录表中的信息 录入查看审批表中
     * @param map
     * @return
     */
    List<Map> loans1(Map map);

    /**
     * 查询贷款记录表中的总信息条数
     * @return
     */
    int loansCount1();

    /**
     * 查询封存、启封、销户 记录表
     * @param map
     * @return
     */
    List<Map> breaka1(Map map);

    /**
     * 查询封存、启封、销户 记录表 条数
     * @return
     */
    int breakaCount1();

    /**
     * 查询人员转移记录表中信息   录入查看审批表中
     * @param map
     * @return
     */
    List<Map> transfer(Map map);

    /**
     * 查询人员转移记录表  条数
     * @return
     */
    int transferCount();

    /**
     * 查询公积金提取记录表
     * @param map
     * @return
     */
    List<Map> extract(Map map);
    /**
     * 查询公积金提取记录表
     * @param map
     * @return
     */
    List<Map> extract1(Map map);

    /**
     * 查询公积金提取记录表 条数
     * @return
     */
    int extractCount1();
}
