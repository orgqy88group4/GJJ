package com.aaa.gjj.controller;

import com.aaa.gjj.entity.PersonsAccountNumberState;
import com.aaa.gjj.entity.personDetail;
import com.aaa.gjj.service.MergerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:MergeController
 * discription:
 * author:qcm
 * createTime:2018-12-12 11:58
 */
@Controller
@RequestMapping("/AccountMerge")
public class MergeController {
    @Autowired
    private MergerService mergerService;
    /**
     * element
     * @param map
     * @return
     * controller --放到service层
     */
    @ResponseBody
    @RequestMapping("/SealedPage1")
    public Object list(@RequestBody Map map){
        System.out.println("前台传来的值："+map);
        if(map!=null&&map.size()>0){
            List<PersonsAccountNumberState> userList = mergerService.SealedPage1(map);//获取分页查询的值
            for (PersonsAccountNumberState personsAccountNumberState : userList) {
                System.out.println("==========="+personsAccountNumberState.getPeraccState());
            }
            Map resultMap = new HashMap();
            resultMap.put("pageDate",userList);
            resultMap.put("total",mergerService.SealedPageCount1(map).get(0).get("cnt"));
            return resultMap;
        }else {
            return "0";
        }
    }

    /**
     * 封存 启封 销户  操作弹出层查询信息
     * @param map
     * @return
     * 根据个人账号信息来查询
     * element
     * 放到service层
     */
    @ResponseBody
    @RequestMapping("/operationQuery1")
    public Object operationQuery1(@RequestBody Map map){//RequestBody
        List<Map> verification = mergerService.verification(map);  //查询到  封存 启封 销户 审核表信息
        List<Map> loansVerification = mergerService.loansVerification(map);//校验贷款的人 不能封存和销户  查询贷款人员信息
        Map operationMap = mergerService.operationQuery(map);  //查询到个人信息 用于封存 启封 销户   操作弹出层查询信息
        Map maps = new HashMap();
        if(loansVerification!=null&&loansVerification.size()>0){ //判断按照GRZH查询贷款人 如有贷款人 进去 把这条信息传到前台 前台进行判断
            maps.put("daikan","0");
        }else{
            if(verification!=null&&verification.size()>0){ //判断按照GRZH查询封存 销户 启封 是否重复操作 查询是否有这条信息 在判断
                maps.put("grzh","0");
            }else{
                return operationMap;
            }
        }
        System.out.println("element的结果"+maps);
        return maps;
    }

    /**
     * 封存 启封 销户 每个按钮修改的信息
     * @param map
     * @param session
     * @return
     * element controller放到service层
     */
    @ResponseBody
    @RequestMapping("/Archive1")
    public Object Archive1(@RequestBody Map map,HttpSession session){
        Map unsealAudit = mergerService.unsealAudit1(map,session);  //把查询的信息和页面信息  传带服务层  然后放入临时的map  把map添加到启封审核表中
        int unsealAuditAdd = mergerService.unsealAuditAdd1(unsealAudit);	//获取审核信息 添加到审核表中
        return unsealAuditAdd;
    }

    /**
     * 明细查询分页列表
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/page1")
    public Object userPage1(@RequestBody Map map){
        List<personDetail> userList = mergerService.getPage1(map);//获取分页查询的值
        Map tempMap = new HashMap();
        //绑定数据 分页数据
        tempMap.put("pageDate", userList);
        //总数量
        tempMap.put("total",mergerService.getPageCount1(map));
        return tempMap;
    }

    //12121658查看审批
    @ResponseBody
    @RequestMapping("/accraditation1")
    public Object accraditation1(){
        List<Map> list = mergerService.accraditation1();//查询查看审批表的 工作类型信息
        System.out.println(list);
        return list;
    }

    /**
     * 查看贷款审批信息   放入 查看审批页面
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/loans1")
    public Object loans1(@RequestBody Map map){
        List<Map> loans = mergerService.loans1(map);
        System.out.println(loans);
        Map tempMap = new HashMap();
        //绑定数据 分页数据
        tempMap.put("pageDate", loans);
        //总数量
        tempMap.put("total",mergerService.loansCount1());
        return tempMap;
    }

    /**
     * 查看封存、启封、销户审批记录信息    放入 查看审批页面
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/breaka1")
    public Object breaka1(@RequestBody Map map){
        List<Map> breaka = mergerService.breaka1(map);
        Map tempMap = new HashMap();
        //绑定数据 分页数据
        tempMap.put("pageDate", breaka);
        //总数量
        tempMap.put("total",mergerService.breakaCount1());
        System.out.println(tempMap);
        return tempMap;
    }

    /**
     * 查看个人转移审批信息    放入 查看审批页面
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/transfer1")
    public Object transfer1(@RequestBody Map map){
        List<Map> transfer = mergerService.transfer(map);
        Map tempMap = new HashMap();
        //绑定数据 分页数据
        tempMap.put("pageDate", transfer);
        //总数量
        tempMap.put("total",mergerService.transferCount());
        System.out.println(tempMap);
        return tempMap;
    }

    /**
     * 查看公积金提取记录信息    放入 查看审批页面
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/extract1")
    public Object extract1(@RequestBody Map map){
        List<Map> transfer = mergerService.extract1(map);
        Map tempMap = new HashMap();
        //绑定数据 分页数据
        tempMap.put("pageDate", transfer);
        //总数量
        tempMap.put("total",mergerService.extractCount1());
        return tempMap;
    }
}
