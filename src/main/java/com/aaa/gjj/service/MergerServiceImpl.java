package com.aaa.gjj.service;

import com.aaa.gjj.dao.MergeDao;
import com.aaa.gjj.entity.PersonsAccountNumberState;
import com.aaa.gjj.entity.personDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * className:MergerServiceImpl
 * discription:
 * author:qcm
 * createTime:2018-12-12 11:53
 */
@Service
public class MergerServiceImpl implements MergerService{
    @Autowired
    private MergeDao mergeDao;

    /**
     * 封存、启封
     * @param map
     * @return
     */
    @Override
    public List<PersonsAccountNumberState> SealedPage1(Map map) {
        System.out.println("serviceImpl====="+map);
        List<Map> page = mergeDao.SealedPage1(map);
        System.out.println("serviceImpl====page==="+page);
        List<PersonsAccountNumberState> userList = new ArrayList<>(); //自定义一个集合把分页查询的值放入  把状态转为中文
        if(page!=null&page.size()>0) {        //把状态的值转会为中文 传到前台
            PersonsAccountNumberState state = null;
            String pState = "";  //个人状态
            String uRatio = "";  //单位缴存比例
            String pRatio = "";  //个人缴存比例
            for (int i = 0; i < page.size(); i++) {
                Map maps = page.get(i);
                //转换状态信息
                if(maps.get("peraccState").equals(1)){
                    pState="正常";
                }else if(maps.get("peraccState").equals(2)){
                    pState="封存";
                }else{
                    pState="销户";
                }
                uRatio = maps.get("ubitNrop")+"%";
                pRatio = maps.get("indiNrop")+"%";
                state = new PersonsAccountNumberState(maps.get("GRZH")+"",maps.get("tb_pName")+"",uRatio,pRatio,Double.parseDouble(maps.get("baseNummber")+""),Double.parseDouble(maps.get("dalance")+""),pState,maps.get("lastNaydate")+"");
                userList.add(state);
            }
        }
        return userList;
    }
    //element
    @Override
    public List<Map> SealedPageCount1(Map map) {
        return mergeDao.SealedPageCount1(map);
    }

    @Override
    public List<Map> verification(Map map) {
        return mergeDao.verification(map);
    }

    @Override
    public List<Map> loansVerification(Map map) {
        return mergeDao.loansVerification(map);
    }

    //element controller --service层
    @Override
    public Map operationQuery(Map map) {
        List<Map> operationMap = mergeDao.operationQuery(map);
        Map maps = new HashMap();
        if (operationMap.size()>0&&operationMap!=null){
            String pState = "";  //个人状态
            for (Map tempMap : operationMap) {
                maps.put("DWZH", tempMap.get("DWZH"));	//单位账号
                maps.put("tb_pName", tempMap.get("tb_pName")); //个人名称
                maps.put("uname", tempMap.get("uname")); //单位名称
                maps.put("GRZH", tempMap.get("GRZH")); //个人账号
                if(tempMap.get("peraccState").equals(1)){  //判断状态  修改成汉字
                    pState="正常";
                }else if(tempMap.get("peraccState").equals(2)){
                    pState="封存";
                }else{
                    pState="销户";
                }
                maps.put("peraccState",pState);
            }
            return maps;
        }else {
            return null;
        }
    }

    //element
    @Override
    public Map unsealAudit1(Map map,HttpSession session) {
        String name = session.getAttribute("name")+"";   //获取操作人员
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //设置日期格式
        List<Map> unsealAudit = mergeDao.unsealAudit1(map);
        String pdstype = map.get("pdstype")+"";
        System.out.println("==========="+pdstype);
        String pState=" ";
        if(pdstype.equals("封存")){          //把文字 转成 数字 存入审核表
            pState="2";
        }else if(pdstype.equals("销户")){
            pState="0";
        }else if(pdstype.equals("启封")){
            pState="1";
        }

        Map maps = new HashMap();
        if(unsealAudit!=null&&unsealAudit.size()>0){
            for (Map tempMap : unsealAudit) {
                maps.put("tb_pName", tempMap.get("tb_pName"));		//（启封/销户）人姓名
                maps.put("uname", tempMap.get("uname"));			//所在单位
                maps.put("tb_pSex", tempMap.get("tb_pSex"));		//性别
                maps.put("tb_Profession", tempMap.get("tb_Profession"));//工作职位
                maps.put("tb_pIphone", tempMap.get("tb_pIphone"));	//手机号
                maps.put("tb_idNUmber", tempMap.get("tb_idNUmber"));//身份证号
                maps.put("tb_pDate", tempMap.get("tb_pDate"));		//出生日期
                maps.put("GRZH", tempMap.get("GRZH"));				//销户人个人账户
                maps.put("resson", map.get("resson"));				//启封 或 销户 原因
                maps.put("operator",name);							//操作人
                maps.put("create_time", df.format(new Date()));		//创建日期时间
                maps.put("audit_state",map.get("pdstype"));			//审核状态
                maps.put("state",pState);
            }
        }
        return maps;
    }
    //element
    @Override
    public int unsealAuditAdd1(Map map) {
        return mergeDao.unsealAuditAdd1(map);
    }

    @Override
    public List<personDetail> getPage1(Map map) {
        List<Map> page = mergeDao.getPage1(map);
        List<personDetail> userList = new ArrayList<personDetail>(); //自定义一个集合把分页查询的值放入  把状态转为中文
        if(page!=null&page.size()>0){		//把状态的值转会为中文 穿到前台
            personDetail personDetail = null;
            String pState = "";
            for (int i = 0; i < page.size(); i++) {
                Map maps = page.get(i);
                personDetail = new personDetail(maps.get("GRZH")+"",maps.get("tb_pName")+"",maps.get("uname")+"",maps.get("paOp")+"",Double.parseDouble(maps.get("unitMonPaysum")+""),Double.parseDouble(maps.get("perMonPaysum")+""),maps.get("openDate")+"");
                userList.add(personDetail);
            }
        }
        return userList;
    }

    @Override
    public int getPageCount1(Map map) {
        List<Map> pageCount = mergeDao.getPageCount1(map);
        //判断总数量列表是否有值
        if(pageCount!=null&&pageCount.size()>0){
            return Integer.parseInt(pageCount.get(0).get("cnt")+"");
        }
        return 0;
    }

    //12121652查看审批

    @Override
    public List<Map> accraditation1() {
        List<Map> list = mergeDao.accraditation1();
        mergeDao.addAccraditationCount(mergeDao.accraditationCount(),"个人贷款");//把信息个数   存入个人贷款类型信息
        mergeDao.addAccraditationCount(mergeDao.accraditationCountb(),"人员转移审批");//把信息个数   存入人员转移审批类型信息
        mergeDao.addAccraditationCount(mergeDao.accraditationCountt(),"公积金提取");//把信息个数   存入公积金提取类型信息
        mergeDao.addAccraditationCount(mergeDao.accraditationCounts(),"封存、启封、销户审批");//把信息个数   存入公积金提取类型信息
        return list;
    }

    @Override
    public List<Map> loans1(Map map) {
        return mergeDao.loans1(map);
    }

    @Override
    public int loansCount1() {
        List<Map> breakaCount = mergeDao.loansCount1();
        //判断总数量列表是否有值
        if(breakaCount!=null&&breakaCount.size()>0){
            return Integer.parseInt(breakaCount.get(0).get("cou")+"");
        }
        return 0;
    }

    @Override
    public List<Map> breaka1(Map map) {
        return mergeDao.breaka1(map);
    }

    @Override
    public int breakaCount1() {
        List<Map> breakaCount = mergeDao.breakaCount1();
        //判断总数量列表是否有值
        if(breakaCount!=null&&breakaCount.size()>0){
            return Integer.parseInt(breakaCount.get(0).get("cou")+"");
        }
        return 0;
    }

    @Override
    public List<Map> transfer(Map map) {
        System.out.println("前台传来的值："+map);
        List<Map> transfer = mergeDao.transfer(map);
        return transfer;
    }

    @Override
    public int transferCount() {
        List<Map> pageCount = mergeDao.transferCount();
        //判断总数量列表是否有值
        if(pageCount!=null&&pageCount.size()>0){
            return Integer.parseInt(pageCount.get(0).get("cou")+"");
        }
        return 0;
    }

    @Override
    public List<Map> extract(Map map) {
        //当前页数
        int pageNo = Integer.parseInt(map.get("page") == null?"1":map.get("page")+"");
        //每页显示数量
        int pageSize = Integer.parseInt(map.get("rows") == null?"10":map.get("rows")+"");
        //计算开始值
        int start = (pageNo-1)*pageSize;
        map.put("start",start);
        map.put("rows",pageSize);
        //map.remove("page");
        //map.remove("rows");
        return mergeDao.extract(map);
    }
    @Override
    public List<Map> extract1(Map map) {
        return mergeDao.extract1(map);
    }

    @Override
    public int extractCount1() {
        List<Map> extractCount = mergeDao.extractCount1();
        //判断总数量列表是否有值
        if(extractCount!=null&&extractCount.size()>0){
            return Integer.parseInt(extractCount.get(0).get("cou")+"");
        }
        return 0;
    }
}
