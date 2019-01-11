package com.aaa.gjj.controller;

import com.aaa.gjj.service.TheCompanyBusinessService;
import com.aaa.gjj.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:TheCompanyBusinessController
 * discription:
 * author:qcm
 * createTime:2018-12-10 21:10
 */
@Controller
@RequestMapping("/company1")
public class TheCompanyBusinessController {
    @Autowired
    private TheCompanyBusinessService theCompanyBusinessService;
    /**
     * 分页显示公司信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/sss")
    public Object sss(@RequestBody Map map) {
        System.out.println(map);
        List<Map> userList = theCompanyBusinessService.UnitAccountInformation(map);
        int cnt = theCompanyBusinessService.UnitAccountInformationCount(map);
        Map tempmap = new HashMap();
        if(userList!=null&&userList.size()>0){
            for (Map map2 : userList) {
                if(map2.get("uaState").equals(1)){
                    map2.put("uaState", "正常");
                }else{
                    map2.put("uaState", "异常");
                }
            }
        }
        tempmap.put("pageData", userList);
        tempmap.put("total",cnt);
        return tempmap;
    }

    /**
     * 汇缴办理
     * @param map
     * @return
     */
    /**
     * 汇缴时判断本月是否已经汇缴过了
     */
    @ResponseBody
    @RequestMapping("/getRemit")
    public Object getRemit(@RequestBody Map map){
        System.out.println("前台传来的值："+map);
        Map tempMap = new HashMap();
        //取数据库里的交至年月
        List<Map> remitTime = theCompanyBusinessService.getRemitTime(map);
        System.out.println("后台返回的值："+remitTime);
        if(remitTime!=null&&remitTime.size()>0){
            try {
                if((remitTime.get(0).get("time"))==null){
                    tempMap.put("issc", false);
                }else{
                    String ot =remitTime.get(0).get("time")+"";
                    String oldTime =  ot.replace("-", "").substring(0,6);
                    //获取当前时间
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
                    String df = dateFormat.format(date);
                    String nt = df.toString();
                    if(oldTime.equals(nt)){
                        tempMap.put("issc", true);
                        tempMap.put("info", "本月已汇缴");
                    }else{
                        tempMap.put("issc", false);
                    }
                }
            } catch (Exception e) {

            }
            return tempMap;
        }else{
            tempMap.put("issc", false);
        }
        return tempMap;
    }


    /**
     * 汇缴办理完成之后把汇缴的信息存到明细查询表里，然后跳转到明细查询界面
     */
    @ResponseBody
    @RequestMapping("/unitRemit")
    public Object unitRemit(@RequestBody Map map){
        int i = theCompanyBusinessService.unitRemit(map);
        System.out.println("汇缴办理后台返回：="+i);
        if(i==1){
            return 1;
        }else{
            return 0;
        }
    }
    /**
     * 明细查询
     * @param map
     * @return
     */
    @RequestMapping("/MXCXController")
    @ResponseBody
    public Object MXCXController(@RequestBody Map map) {
        List<Map> userList = theCompanyBusinessService.MXCXBiao1(map);
        if(userList!=null&&userList.size()>0){
            for (Map map2 : userList) {
                if(map2.get("ubdSettleStates").equals(1)){
                    map2.put("ubdSettleStates", "正常");
                }else{
                    map2.put("ubdSettleStates", "异常");
                }
            }
        }
        Map resultMap = new HashMap();
        resultMap.put("pageData", userList);
        resultMap.put("total",theCompanyBusinessService.MXCXBiaoCut1(map));
        return resultMap;
    }

    /**
     * 比例变更时把员工信息显示出来
     */
    @ResponseBody
    @RequestMapping("/getStaffInfo")
    public Object getInfo(@RequestBody Map map){
        List<Map> list = theCompanyBusinessService.getStaffInfo(map);
        Map tempMap = new HashMap();
        if(list!=null&&list.size()>0){
            tempMap.put("rows", list);
        }
        return list;
    }

    /**
     * 比例变更
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/ratioChange")
    public String ratioChange(@RequestBody Map map){
        int result = theCompanyBusinessService.updateRatio(map);
        if(result==-1){
            return "0";
        }else{
            return "1";
        }

    }
    /**
     * 挂账办理
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/GZBLYewu")
    public Object GZBLYewu(@RequestBody Map map) {
        return  theCompanyBusinessService.GZBLyewu1(map);

    }
}
