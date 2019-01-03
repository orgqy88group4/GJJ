package com.aaa.gjj.service;

import com.aaa.gjj.dao.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:CompanyService
 * discription:
 * author:qcm
 * createTime:2018-12-21 14:59
 */
@Service
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private CompanyDao companyDao;

    /**
     * 基数调整页面数据
     * @param map
     * @return
     */
    @Override
    public List<Map> UnitAccountInformation(Map map) {
        List<Map> list = companyDao.UnitAccountInformation(map);
        if (list.size()>0){
            for (Map map1 : list) {
                if (map1.get("uaState").equals(1)){
                    map1.put("uaState","正常");
                }else{
                    map1.put("uaState","异常");
                }
            }
        }
        return list;
    }

    /**
     * 基数调整页面总数量
     * @param map
     * @return
     */
    @Override
    public int UnitAccountInformationCount(Map map) {
        List<Map> pageCount = companyDao.UnitAccountInformationCount(map);
        if(pageCount!=null&&pageCount.size()>0){
            return Integer.valueOf(pageCount.get(0).get("count")+"");
        }
        return 0;
    }

    /**
     * 点击比例变更按钮，前台传来单位账号
     * @param map
     * @return
     */
    @Override
    public List<Map> getStaffInfo(Map map) {
        return companyDao.getStaffInfo(map);
    }

    /**
     * 基数调整，保存调整按钮
     * @param map
     * @return
     */
    @Override
    public int updateMoney(Map map) {
        List<Map> list = companyDao.updateMoney(map);
        Map tempMap = new HashMap();
        tempMap.put("uid",list.get(0).get("uid"));
        tempMap.put("DWZH",map.get("DWZH"));
        int i1 = companyDao.updateMoney1(map);
        System.out.println("有值吗："+i1);
        int i = companyDao.updateMoney2(tempMap);
        return i;
    }

    /**
     * 补缴办理页面，补缴办理按钮
     * @param map
     * @return
     */
    @Override
    public Map getUnitPayInfo(Map map) {
        List<Map> unitPayInfo = companyDao.getUnitPayInfo(map);
        Map tempmap = new HashMap();
        tempmap.put("payPerson", unitPayInfo.get(0).get("payPerson"));
        return tempmap;
    }

    /**
     * 补缴办理人员信息
     * @param map
     * @return
     */
    @Override
    public List<Map> getPersonPayInfo(Map map) {
        List<Map> personPayInfo = companyDao.getPersonPayInfo(map);
        if (personPayInfo.size()>0){
            for (Map map2 : personPayInfo) {
                if(map2.get("peraccState").equals(1)){
                    map2.put("peraccState", "正常");
                }
                if(map2.get("peraccState").equals(0)){
                    map2.put("peraccState", "销户");
                }
                if(map2.get("peraccState").equals(2)){
                    map2.put("peraccState", "封存");
                }
                if(map2.get("yinterestBal").equals(0)){
                    map2.put("yinterestBal", "未缴纳");
                }
                if(map2.get("yinterestBal").equals(1)){
                    map2.put("yinterestBal", "已缴纳");
                }
            }
        }
        return personPayInfo;
    }

    /**
     * 补缴按钮
     * @param map
     * @return
     */
    @Override
    public int updatePersonInfo(Map map) {
        List<Map> list = companyDao.updatePersonInfo(map);
        Map tempMap = new HashMap();
        tempMap.put("yDrawAmt",list.get(0).get("yDrawAmt"));
        tempMap.put("GRZH",map.get("GRZH"));
        System.out.println("tempMap="+tempMap);
        companyDao.updatePersonInfo1(tempMap);
        int i = companyDao.updatePersonInfo2(map);
        return i;
    }

    /**
     * 根据单位账号，查找单位名称、缴纳的比例
     * @param DWZH
     * @return
     */
    @Override
    public List<Map> getUnitInfo(String DWZH) {
        return companyDao.getUnitInfo(DWZH);
    }

    /**
     * 个人注册，把账户信息存到账户信息表中
     * @param map
     * @return
     */
    @Override
    public int addPersonInfo(Map map) {
        int i = companyDao.addPersonInfo(map);
        if (i==1){
            return companyDao.addPersonInfo1();
        }else {
            return 0;
        }
    }

    /**
     * 得到单位id ，应缴纳金额
     * @param map
     * @return
     */
    @Override
    public int addPersonAccountInfo(Map map) {
        List<Map> list = companyDao.addPersonAccountInfo(map);
        map.put("uid",list.get(0).get("uid"));
        System.out.println("修改后的map值："+map);
        companyDao.addPersonAccountInfo1(map);
        companyDao.addPersonAccountInfo2(map);
        int i = companyDao.addPersonAccountInfo3(map);
        return i;
    }

    /**
     * 公司名称唯一效验
     * @param map
     * @return
     */
    @Override
    public int getUnitInfoList(Map map) {
        List<Map> unitInfoList = companyDao.getUnitInfoList();
        if (unitInfoList!=null&&unitInfoList.size()>0){
            for (Map map1 : unitInfoList) {
                if (map1.get("uname").equals(map.get("uName"))){
                    return 1;
                }
                if (map1.get("uNetworkCode").equals(map.get("uNetworkCode"))){
                    return 1;
                }
            }
        }
        return 0;
    }

    /**
     * 添加公司信息
     * @param map
     * @return
     */
    @Override
    public int unitRegister(Map map) {
        int i = companyDao.unitRegister(map);//业务操作人员
        if (i==1){
            return companyDao.unitRegister1();
        }
        return 0;
    }

    /**
     *  单位注册时的账户信息
     * @param map
     * @return
     */
    @Override
    public int unitAccount(Map map) {
        if (map.get("uaState").equals("正常")){
            map.put("uaState1",1);
        }else{
            map.put("uaState1",0);
        }
        return companyDao.unitAccount(map);
    }
}
