package com.aaa.gjj.service;

import java.util.List;
import java.util.Map;

/**
 * className:PayService
 * discription:
 * author:lin
 * createTime:2018-12-19 16:27
 */
public interface PayService {

    /**
     * 分页
     * @param map
     * @return
     */
    List<Map> getPageByParam(Map map);

    /**
     * 分页总数量
     * @param map
     * @return
     */
    int getPageCount(Map map);

    /**
     * 还款页面(还款记录)
     * @param map
     * @return
     */
    List<Map> getRecord(Map map);

    /**
     * 带参分页查询(还款记录)
     * @param map
     * @return
     * 如果使用注解的方式，动态sql必须在标签<script></script>
     * 如果使用<script></script>标签，mybatis大于小于,必须使用&gt;  &lt;
     */
    List<Map> getRecordByParam(Map map);

    /**
     * 分页总数量(还款记录)
     * @param map
     * @return
     */
    int getRecordCount(Map map);

    /**
     * 点击还款   修改还款表待还款信息
     * @param map
     * @return
     */
    int repayMoney(Map map);

    /**
     * 查询还款信息表
     * @param id
     * @return
     */
    List<Map> gettbRepay(Integer id);

    /**
     * 新增到还款记录表
     * @param map
     * @return
     */
    int repayRecord(Map map);

    /**
     * 贷款明细(分页)
     * @param map 可选项  用于拼接查询条件
     * @return
     */
    List<Map> getRepay(Map map);
    /**
     * 获取分页数据数量（贷款明细）
     * @param map
     * @return
     */
    int getRepayCount(Map map);

    /**
     * 逾期修改状态
     * @param map
     * @return
     */
    int updateStatus(Map map);

    /**
     * 逾期信息
     * @param map
     * @return
     */
    Map getOverPay(Map map);

    /**
     * 逾期信息
     * @param map
     * @return
     */
    Map overPay(Map map);
}
