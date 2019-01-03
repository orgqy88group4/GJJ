package com.aaa.gjj.service;




import java.util.List;
import java.util.Map;

/**
 * className:FrontLoginService
 * discription:
 * author:dujihu
 * createTime:2018-12-11 17:10
 */
public interface FrontLoginService {

    /**
     * 查询账户
     * @param map
     * @return
     */
    List<Map> getGRAccount(Map map);

    /**
     * 查询单位账户
     * @param map
     * @return
     */
    List<Map> getDWAccount(Map map);
}
