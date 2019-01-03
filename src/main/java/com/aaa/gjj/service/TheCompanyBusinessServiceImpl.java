package com.aaa.gjj.service;

import com.aaa.gjj.dao.TheCompanyBusinessDao;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * className:TheCompanyBusinessServiceImpl
 * discription:
 * author:qcm
 * createTime:2018-12-10 21:08
 */
@Service
public class TheCompanyBusinessServiceImpl implements TheCompanyBusinessService {


    @Autowired
    private TheCompanyBusinessDao theCompanyBusinessDao;
    @Override
    public List<Map> UnitAccountInformation(Map map) {
        // TODO Auto-generated method stub
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");//设置日期格式
        return theCompanyBusinessDao.UnitAccountInformation(map);
    }

    @Override
    public int  UnitAccountInformationCount(Map map) {
        // TODO Auto-generated method stub
        List<Map> uniList = theCompanyBusinessDao.UnitAccountInformationCount(map);
        if (uniList!=null&&uniList.size()>0) {
            return Integer.valueOf(uniList.get(0).get("cnt")+"");
        }
        return 0;
    }
    /**
     * 汇缴时判断本月是否已经汇缴过了,查询明细查询表中当前单位汇缴的最近一个时间
     */
    @Override
    public List<Map> getRemitTime(Map map) {
        // TODO Auto-generated method stub
        List<Map> time = theCompanyBusinessDao.getRemitTime(map);
//        System.out.println("0000"+time);
        return time;
    }

    /**
     * 汇缴办理完成之后把汇缴的信息存到明细查询表里
     */
    @Override
    public int  unitRemit(Map map) {
        // TODO Auto-generated method stub
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");//设置日期格式
        if (Integer.parseInt(map.get("uARemain")+"")>=Integer.parseInt(map.get("uaOweMonery")+"")){
            int i = theCompanyBusinessDao.updateRemit(map);//修改公司账户表
            int i1 = theCompanyBusinessDao.updateState(map);//单位汇缴之后，个人账户余额会增加 汇缴办理之后单位人员的汇缴状态会变成已汇缴
            int i2=theCompanyBusinessDao.insertDtail(map);
            Map detailList = theCompanyBusinessDao.getDetailList(map);//汇缴之后单位账户表和单位汇缴表里的对应的账户余额会减少
            detailList.put("c",map.get("ubdRemark"));
            detailList.put("hjys",map.get("hjys"));
            theCompanyBusinessDao.insertDtail(detailList);
            if (i==1&&i1==1&&i2==1){
                return 1;
            }else {
                return 0;
            }
        }else{
            return 0;
        }
    }

    /**
     * 明细查询分页数据
     */
    @Override
    public List<Map> MXCXBiao1(Map map) {
        // TODO Auto-generated method stub
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");//设置日期格式
        return theCompanyBusinessDao.MXCXBiao(map);
    }
    /**
     * 明细查询
     */
    @Override
    public int MXCXBiaoCut1(Map map) {
        // TODO Auto-generated method stub
        List<Map> uniList = theCompanyBusinessDao.MXCXBiaoCnt(map);
        if (uniList!=null&&uniList.size()>0) {
            return Integer.valueOf(uniList.get(0).get("cnt")+"");
        }
        return 0;
    }
    /**
     * 查询比例变更时的公司人员
     */
    /**
     * 查询比例变更时的公司人员
     */
    @Override
    public List<Map> getStaffInfo(Map map) {
        // TODO Auto-generated method stub
        return theCompanyBusinessDao.getStaffInfo(map);
    }

    /**
     * 比例变更
     */
    @Override
    public int updateRatio(Map map) {
        // TODO Auto-generated method stub
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");//设置日期格式
        List<Map> list = theCompanyBusinessDao.list(map);////查询单位账号对应的单位id
        int i=theCompanyBusinessDao.updatePRatio(map);//修改个人账户中的比例和基数
        map.put("c",list.get(0).get("uid"));
        int i1=theCompanyBusinessDao.updateURatio(map);//修改单位账户中的比例和基数和应缴纳金额
        if (i==1&&i1==1){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 挂账办理
     */
    @Override
    public int GZBLyewu1(Map map) {
        // TODO Auto-generated method stub
        return theCompanyBusinessDao.GZBLyewuD(map);
    }
}
