package com.aaa.gjj.service;

import com.aaa.gjj.dao.PayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:PayServiceImpl
 * discription:
 * author:lin
 * createTime:2018-12-19 16:28
 */
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayDao payDao;

    @Override
    public List<Map> getPageByParam(Map map) {
        return payDao.getPageByParam(map);
    }

    @Override
    public int getPageCount(Map map) {
        return payDao.getPageCount(map);
    }

    @Override
    public List<Map> getRecord(Map map) {
        return payDao.getRecord(map);
    }


    @Override
    public List<Map> getRecordByParam(Map map) {
        return payDao.getRecordByParam(map);
    }
    @Override
    public int getRecordCount(Map map) {
        return payDao.getRecordCount(map);
    }

    @Override
    public int repayMoney(Map map) {
        System.out.println("收到的参数=="+map);
        int  periods = Integer.valueOf(map.get("residue_periods")+"");                                //剩余期数
        Double loan_money = Double.valueOf(map.get("loan_money")+"") ;                //贷款总额
        Double repay_month_money = Double.valueOf(map.get("repay_month_money")+"") ;  //yue还本金
        Double repay_month_interest = Double.valueOf( map.get("repay_month_interest")+"");//yue还利息
        Double repayed_period = Double.valueOf(map.get("repayed_period")+"") ;       //已还期数
        Double repay_money = Double.valueOf(map.get("repay_money")+"") ;               //应还本金
        Double repay_interests = Double.valueOf(map.get("repay_interests")+"") ;       //应还利息
        Double loan_periods = Double.valueOf(map.get("loan_periods")+"");             //贷款期数
        Double repayed_money = Double.valueOf(map.get("repayed_money")+"") ;          //已还本金
        Double repayed_interests = Double.valueOf(map.get("repayed_interests")+"") ;//已还利息
//        int repay_month = (int) map.get("repay_month");
//        int repay_mmonth = (int) map.get("repay_mmonth");residue_periods
        String status ="1";
        map.put("repayed_period",repayed_period+1);
        map.put("residue_money",loan_money-(repay_month_money*(repayed_period+1)));         //剩余本金
        map.put("residue_interests",repay_month_interest*(loan_periods-(repayed_period+1)));//剩余利息
        map.put("repayed_money",repay_month_money*(repayed_period+1));                  //已还本金
        map.put("repayed_interests",repay_month_interest*(repayed_period+1));               //已还利息
        map.put("residue_periods",periods-1);                          //剩余期数
        if(periods==1) {
            status = "2";
            map.put("status",status);
            map.put("month",'0');
            map.put("mmonth",'0');
            map.put("repayed_All_money",repay_money+repay_interests);
            payDao.updRecordState(2,map.get("repay_id")+"");
            int result=payDao.repayMoney(map);
            System.out.println("修改后的参数"+map);
            return result;
        }else{
            map.put("repayed_All_money",(repay_month_money+repay_month_interest)*repayed_period);
            int result=payDao.repayMoney(map);
            System.out.println("修改后的参数"+map);
            return result;
        }
    }

    @Override
    public List<Map> gettbRepay(Integer id) {
        // TODO Auto-generated method stub
        return payDao.gettbRepay(id);
    }

    @Override
    public int repayRecord(Map map) {
        // TODO Auto-generated method stub
        return payDao.repayRecord(map);
    }

    @Override
    public List<Map> getRepay(Map map) {
        return payDao.getRepay(map);
    }

    @Override
    public int getRepayCount(Map map) {
        return payDao.getRepayCount(map);
    }
}
