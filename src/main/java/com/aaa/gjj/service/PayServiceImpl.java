package com.aaa.gjj.service;

import com.aaa.gjj.dao.PayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import static com.aaa.gjj.util.AverageCapitalPlusInterestUtils.*;

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
    public int updateStatus(Map map) {
        return payDao.repayStatus(map);
    }

    @Override
    public Map getOverPay(Map map) {
        Double loan_rate= Double.valueOf(map.get("loan_rate")+"");
        Double repay_mmonth= Double.valueOf(map.get("money")+"");
        //获取逾期天数和金额
        Map overPay = payDao.getOverPay(map);

        int days= Integer.valueOf(overPay.get("over_days")+"") ;
        Double over_money= Double.valueOf(overPay.get("over_money")+"") ;
        //保留两位小数
        DecimalFormat df = new DecimalFormat("######0.00");
        Double over_interests=Double.valueOf(df.format(repay_mmonth*(loan_rate*0.01/360*0.15)*days)) ;
        Double pay_money=Double.valueOf(df.format(over_interests+over_money)) ;

        overPay.put("over_interests",over_interests);
        overPay.put("pay_money",pay_money);
        return overPay;
    }

    @Override
    public Map overPay(Map map) {
        return null;
    }

    @Override
    public int repayMoney(Map map) {
        System.out.println("收到的参数=="+map);
        int  periods = Integer.valueOf(map.get("residue_periods")+"");                //剩余期数
        Double loan_money = Double.valueOf(map.get("loan_money")+"") ;                //贷款总额
        Double loan_rate = Double.valueOf(map.get("loan_rate")+"")*0.01 ;                  //贷款利率
        int loan_periods = Integer.valueOf(map.get("loan_periods")+"");               //贷款期数
        Double repay_money = Double.valueOf(map.get("repay_money")+"") ;               //应还本金 总
        Double repay_interests = Double.valueOf(map.get("repay_interests")+"") ;       //应还利息 总
        int repayed_period = Integer.valueOf(map.get("repayed_period")+"") ;         //当前期数
        int repay_state = Integer.valueOf(map.get("repay_state")+"") ;         //还款状态

        String pay_state ="1";

        Double money= 0.00;  //已还本金
        Map  map1=getPerMonthPrincipal(loan_money,loan_rate,loan_periods);
        for (int i = 1; i <=repayed_period; i++) {
            money =money + Double.valueOf(map1.get(i)+"");
        }
        Double interest= 0.00;  //已还利息
        Map  map2=getPerMonthInterest(loan_money,loan_rate,loan_periods);
        for (int i = 1; i <=repayed_period; i++) {
            interest =interest + Double.valueOf(map2.get(i)+"");
        }
        if(repay_state==3){

            Double over_interests = Double.valueOf(map.get("over_interests")+"") ;//逾期利息
            interest=interest+over_interests;
            System.out.println("逾期利息==="+over_interests);
        }
        map.put("repayed_period",repayed_period+1);        //当前期数
        map.put("residue_money",loan_money-money);         //剩余本金
        map.put("residue_interests",repay_interests-interest);//剩余利息
        map.put("repayed_money",money);                  //已还本金
        map.put("repayed_interests",interest);               //已还利息
        map.put("residue_periods",periods-1);                          //剩余期数
//        map.put("repay_month_money",Double.valueOf(getPerMonthPrincipal(loan_money,loan_rate,loan_periods).get(repayed_period+1)+"")); //下月应还本金
//        map.put("repay_month_interest",Double.valueOf(getPerMonthInterest(loan_money,loan_rate,loan_periods).get(repayed_period+1)+"")); //下月应还利息
        map.put("repayed_All_money",money+interest);//已还总额

        if(periods==1||periods=='1') {  //最后一次还款
            System.out.println("最后一次了-----------------------");
            pay_state = "2";
            map.put("repay_state",pay_state);
            map.put("residue_periods",0);
            map.put("repay_month",'0');//月还
            map.put("repay_mmonth",'0');//应还
            map.put("repay_month_money",'0'); //下月应还本金
            map.put("repay_month_interest",'0'); //下月应还利息

            payDao.updRecordState(2,map.get("repay_id")+"");
            int result=payDao.repayMoney(map);
            System.out.println("修改后的参数"+map);
            return result;
        }else if(periods!=1&&repay_state==3){    //逾期计算
            System.out.println("逾期了-----------------------");
            Double over_interests = Double.valueOf(map.get("over_interests")+"") ;//逾期利息
            map.put("repay_state",pay_state);
            map.put("repay_interests",over_interests+repay_interests);  //总利息
            map.put("repay_month_money",Double.valueOf(getPerMonthPrincipal(loan_money,loan_rate,loan_periods).get(repayed_period+1)+"")); //下月应还本金
            map.put("repay_month_interest",Double.valueOf(getPerMonthInterest(loan_money,loan_rate,loan_periods).get(repayed_period+1)+"")); //下月应还利息

            payDao.updRecordState(1,map.get("repay_id")+"");
            int result=payDao.repayMoney(map);
            System.out.println("逾期修改后的参数=="+map);
            return result;
        }else{  //正常还款
            System.out.println("正常-----------------------");
            map.put("repay_state",pay_state);
            map.put("repay_month_money",Double.valueOf(getPerMonthPrincipal(loan_money,loan_rate,loan_periods).get(repayed_period+1)+"")); //下月应还本金
            map.put("repay_month_interest",Double.valueOf(getPerMonthInterest(loan_money,loan_rate,loan_periods).get(repayed_period+1)+"")); //下月应还利息

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
