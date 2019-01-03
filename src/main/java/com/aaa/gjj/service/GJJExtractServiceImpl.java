package com.aaa.gjj.service;

import com.aaa.gjj.dao.GJJExtractDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * className:GJJExtractServiceImpl
 * discription:
 * author:dujihu
 * createTime:2018-12-13 15:19
 */
@Service
public class GJJExtractServiceImpl implements GJJExtractService {


    @Autowired
    private GJJExtractDao gjjExtractDao;


    @Override
    public int yanZhengYDTQrenyuan(Map map) {
        return gjjExtractDao.yanZhengYDTQrenyuan(map);
    }

    @Override
    public int xiaoHu(Map map) {
        gjjExtractDao.updateUnitPeople(map);
        gjjExtractDao.updateUaOweMonery(map);
        gjjExtractDao.updatePaccountutil(map);
        return gjjExtractDao.xiaoHu(map);
    }
    @Override
    public List<Map> getPageByParam(Map map) {

        return gjjExtractDao.getPageByparam(map);
    }

    @Override
    public List<Map> getExaminePageByparam(Map map) {
        return gjjExtractDao.getExaminePageByparam(map);
    }

    @Override
    public int BFtongGuo(Map map) {
        gjjExtractDao.reviseMoney(map);
        return gjjExtractDao.BFtongGuo(map);
    }

    @Override
    public int BFboHui(Map map) {
        return gjjExtractDao.BFboHui(map);
    }

    @Override
    public int getExaminePageCount(Map map) {
        return gjjExtractDao.getExaminePageCount(map);
    }

    @Override
    public List<Map> getView(Map map) {
        return gjjExtractDao.getView(map);
    }

    @Override
    public int getPageCount(Map map) {

        return gjjExtractDao.getPageCount(map);
    }

    @Override
    public int YDTQtongguo(Map map) {
        int ydqs =Integer.valueOf(map.get("ydqs")+"");
        int ydrq =Integer.valueOf(map.get("ydrq")+"");
        Object month = null;
        Object year = new SimpleDateFormat("yyyy").format(new Date());
        if(ydrq>new Date().getDate()){
            month = new Date().getMonth()+1;

        }else {
            if(new Date().getMonth()+1==12){
                month = 1;
                year = Integer.valueOf(year+"") +1;
                System.out.println("22222222");
            }else{
                month = new Date().getMonth()+2;
                System.out.println("22222");

            }
        }

        for(int i = 1;i<=ydqs;i++){
            if(month!=null){
                map.put("year",year);
                map.put("month",month);
                gjjExtractDao.reviseAppointMoney(map);
                if(month.equals(12)){
                    year = Integer.valueOf(year+"") +1;
                    month=1;
                }else {
                    month=Integer.valueOf(month+"")+1;
                }
                if(month.equals(12)){

                }
                System.out.println(year);
                System.out.println(month);
            }
        }
        return gjjExtractDao.YDTQtongguo(map);
    }

    @Override
    public int YDTQbohui(Map map) {
        return gjjExtractDao.YDTQbohui(map);
    }

    @Override
    public int addExtract(Map map) {
        Object tiquCause = map.get("tiquCause");
        Object tiqutype = map.get("tiqutype");
        if(tiqutype!=null&&!tiqutype.equals("")){
            if(tiqutype.equals("2")){
                return  gjjExtractDao.addCloseExtract(map);
            }else if(tiquCause!=null&&!tiquCause.equals("")){
                if(tiquCause.equals("3")){
                 return   gjjExtractDao.addAppointExtract(map);
                }else {
                    return  gjjExtractDao.addExtract(map);
                }
            }
            return 0;
        }
        return 0;
    }

    @Override
    public List<Map> getTQHD(Map map) {
        return gjjExtractDao.getTQHD(map);
    }

    @Override
    public List<Map> addPerson(Map map) {
        return gjjExtractDao.addPerson(map);
    }

    @Override
    public int delBatchPerson(Map map) {
        return gjjExtractDao.delBatchPerson(map);
    }

    @Override
    public List<Map> getBatchPerson(Map map) {
        return gjjExtractDao.getBatchPerson(map);
    }

    @Override
    public int addBatchPerson(Map map) {
        String s1 = (String) map.get("_s");
        List<String> s = Arrays.asList(s1.split(","));
        for (String s2 : s) {
            map.put("GRZH",s2);
            int  i=  gjjExtractDao.addBatchPerson(map);
            if(i==0){
                return 0;
            }
        }
        return 1;
    }

    @Override
    public int addPersonCount(Map map) {
        return gjjExtractDao.addPersonCount(map);
    }

    @Override
    public List<Map> TQHDEdit(Map map) {
        int repayType = Integer.valueOf(map.get("repayType")+"");
        gjjExtractDao.TQHDReviseMoney(map);
        gjjExtractDao.addRecord(map);
        if(repayType==1){
            return gjjExtractDao.getTQHDYC(map);
        }else {
            return gjjExtractDao.getTQHDBF(map);
        }
    }

    @Override
    public List<Map> getAppointPageByparam(Map map) {
        return gjjExtractDao.getAppointPageByparam(map);
    }

    @Override
    public int getAppointPageCount(Map map) {
        return gjjExtractDao.getAppointPageCount(map);
    }

}
