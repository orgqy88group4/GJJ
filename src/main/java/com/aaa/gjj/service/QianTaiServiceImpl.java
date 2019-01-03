package com.aaa.gjj.service;

import com.aaa.gjj.dao.QianTaiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:QianTaiServiceImpl
 * discription:
 * author:qcm
 * createTime:2018-12-26 20:28
 */
@Service
public class QianTaiServiceImpl implements QianTaiService {
    @Autowired
    private QianTaiDao qianTaiDao;

    /**
     * 个人账号登录前台
     * @param map
     * @return
     */
    @Override
    public List<Map> ChackPersonLogin(Map map) {
        return qianTaiDao.ChackPersonLogin(map);
    }

    /**
     * 查询缴存记录
     * @param map
     * @return
     */
    @Override
    public List<Map> SelectCheckJiLu(Map map) {
        return qianTaiDao.SelectCheckJiLu(map);
    }

    /**
     * 查询缴存记录
     * @param map
     * @return
     */
    @Override
    public List<Map> SelectCheckDaiKuanJiLu(Map map) {
        return qianTaiDao.SelectCheckDaiKuanJiLu(map);
    }

    /**
     * 单位账号登录前台
     * @param map
     * @return
     */
    @Override
    public List<Map> ChackUnitLogin(Map map) {
        return qianTaiDao.ChackUnitLogin(map);
    }

    /**
     * 单位账号登录前台
     * @param map
     * @return
     */
    @Override
    public List<Map> ChackUnitLogin1(Map map) {
        return qianTaiDao.ChackUnitLogin1(map);
    }

    /**
     * 单位账号缴纳记录
     * @param map
     * @return
     */
    @Override
    public List<Map> UnitJiaoNaJiLu(Map map) {
        return qianTaiDao.UnitJiaoNaJiLu(map);
    }

    /**
     * 查看单个小新闻
     * @param id
     * @return
     */
    @Override
    public List<Map> Selnews(int id) {
        return qianTaiDao.Selnews(id);
    }

    //=================新闻模块=============================================

    /**
     * 查询新闻信息
     * @param map
     * @return
     */
    @Override
    public List<Map> informationList(Map map) {
        return qianTaiDao.informationList(map);
    }

    /**
     * 查询新闻总数量
     * @param map
     * @return
     */
    @Override
    public int informationListCount(Map map) {
        List<Map> pageCountFinal = qianTaiDao.informationListCount(map);
        if(pageCountFinal!=null&&pageCountFinal.size()>0){
            return Integer.valueOf(pageCountFinal.get(0).get("count")+"");
        }
        return 0;
    }

    /**
     * 修改新闻
     * @param map
     * @return
     */
    @Override
    public int upInformation(Map map) {
        return qianTaiDao.upInformation(map);
    }

    /**
     * 新闻添加
     * @param map
     * @return
     */
    @Override
    public int information(Map map) {
        return qianTaiDao.information(map);
    }

    /**
     * 新闻删除
     * @param id
     * @return
     */
    @Override
    public int delInformation(int id) {
        return qianTaiDao.delInformation(id);
    }

    /**
     * 把后台发布的新闻传输到前台
     * @param tid
     * @return
     */
    @Override
    public List<Map> selnewgall(int tid) {
        return qianTaiDao.selnewgall(tid);
    }
}
