package com.aaa.gjj.service;




import java.util.List;
import java.util.Map;

/**
 * className:GJJExtractService
 * discription:
 * author:dujihu
 * createTime:2018-12-13 15:17
 */
public interface GJJExtractService {


    /**
     * 带参分页查询
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
     * 添加提取申请
     * @param
     * @return
     */
    int addExtract(Map map);


    /**
     * 公积金约定提取带参分页查询
     * @param map
     * @return
     * 如果使用注解的方式 动态sql必须在<script></script>里
     * 如果使用<script></script>标签 mybatis的大于小于 要使用&gt; &lt;
     */
    List<Map> getAppointPageByparam(Map map);

    /**
     * 公积金约定提取查询分页总数量
     * @param map
     * @return
     */
    int getAppointPageCount(Map map);

    /**
     * 获取约定提取信息
     * @param map
     * @return
     */
    List<Map>  getView(Map map);

    /**
     * 公积金普通提取审核带参分页查询
     * @param map
     * @return
     */
    List<Map> getExaminePageByparam(Map map);

    /**
     * 公积金约定普通提取审核查询分页总数量
     * @param map
     * @return
     */
    int getExaminePageCount(Map map);

    /**
     *部分提取通过
     * @param map
     * @return
     */
    int BFtongGuo(Map map);

    /**
     * 部分提取驳回
     * @param map
     * @return
     */
    int BFboHui(Map map);

    /**
     * 约定提取通过
     * @param map
     * @return
     */
    int YDTQtongguo(Map map);


    /**
     * 约定提取驳回
     * @param map
     * @return
     */
    int YDTQbohui(Map map);


    /**
     * 销户提取通过
     * @param map
     * @return
     */
    int xiaoHu(Map map);

    /**
     * 验证约定提取人员
     * @param map
     * @return
     */
    int yanZhengYDTQrenyuan(Map map);

    /**
     * 提取还贷
     * @param map
     * @return
     */
    List<Map>  getTQHD(Map map);

    /**
     * 提取还贷修改还款表
     * @param map
     * @return
     */
    int  TQHDEdit(Map map);

    /**
     * 添加共同还贷人分页
     * @param map
     */

    List<Map> addPerson(Map map);

    /**
     * 添加共同还贷人分页总数量
     * @param map
     * @return
     */
    int addPersonCount(Map map);

    /**
     * 添加共同还贷人
     * @param
     * @return
     */
    int addBatchPerson(Map map);

    /**
     * 查询共同还贷人
     * @param
     * @return
     */
    List<Map> getBatchPerson(Map map);

    /**
     * 移除共同还贷人
     * @param
     * @return
     */
    int delBatchPerson(Map map);


}
