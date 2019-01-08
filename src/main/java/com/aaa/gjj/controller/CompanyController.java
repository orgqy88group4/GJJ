package com.aaa.gjj.controller;

import com.aaa.gjj.service.CompanyService;
import com.aaa.gjj.util.FtpConfig;
import com.aaa.gjj.util.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:CompanyController
 * discription:
 * author:qcm
 * createTime:2018-12-21 14:58
 */
@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    //依赖注入ftp工具类
    @Autowired
    private FtpUtil ftpUtil;

    @Autowired
    private FtpConfig ftpConfig;

    @Autowired
    private  ResourceLoader resourceLoader;

    /**
     * 基数调整页面
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/NumberAdjust")
    public Object NumberAdjust(@RequestBody Map map){
        System.out.println("基数调整页面前台传来的值："+map);
        Map tempMap = new HashMap();
        tempMap.put("pageDate",companyService.UnitAccountInformation(map));
        tempMap.put("total",companyService.UnitAccountInformationCount(map));
        System.out.println("基数调整页面后台返回的数据："+tempMap);
        return tempMap;
    }

    /**
     * 基数调整按钮
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/ChangeNumber")
    public Object ChangeNumber(@RequestBody Map map){
        System.out.println("基数调整按钮前台传来的值："+map);
        List<Map> staffInfo = companyService.getStaffInfo(map);
        System.out.println("后台返回的值："+staffInfo);
        return staffInfo;
    }

    /**
     * 基数确定保存按钮
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateMoney")
    public Object UpdateMoney(@RequestBody Map map){
        System.out.println("保存基数按钮前台传来的值："+map);
        return companyService.updateMoney(map);
    }

    /**
     * 补缴办理按钮
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/payFees")
    public Object PayFees(@RequestBody Map map){
        System.out.println("补缴办理前台传来的值："+map);
        Map unitPayInfo = companyService.getUnitPayInfo(map);
        System.out.println("后台返回的值为："+unitPayInfo);
        return unitPayInfo;
    }

    /**
     * 补缴办理人员信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/payFees1")
    public Object PayFees1(@RequestBody Map map){
        System.out.println("补缴办理人员信息前台传来的值："+map);
        return companyService.getPersonPayInfo(map);
    }

    /**
     * 补缴
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/payFees2")
    public Object PayFees2(@RequestBody Map map){
        System.out.println("补缴按钮前台传来的值："+map);
        return companyService.updatePersonInfo(map);
    }

    /**
     * 根据前台输入的公司账号来查询相应的公司信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/UnitInfo")
    public Object UnitInfo(@RequestBody Map map){
        System.out.println("个人开户前台传来的值："+map);
        return companyService.getUnitInfo(map.get("DWZH") + "");
    }

    /**
     * 把个人信息添加到个人信息表中
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/AddPersonInfo")
    public Object AddPersonInfo(@RequestBody Map map){
        System.out.println("个人开户保存按钮前台传来的值："+map);
        int i = companyService.addPersonInfo(map);
        System.out.println("个人开户保存按钮后台返回的值："+i);
        map.put("pid",i);
        System.out.println("添加后的值："+map);
        int i1 = companyService.addPersonAccountInfo(map);
        return i1;
    }

    /**
     * 公司名称、机构代码唯一性效验
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/unitCheckName")
    public Object UnitCheckName(@RequestBody Map map){
        return companyService.getUnitInfoList(map);
    }

    /**
     * 公司开户保存
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/registerInfo")
    public Object RegisterInfo(@RequestBody Map map){
        System.out.println("map="+map);
        int i = companyService.unitRegister(map);
        map.put("uid",i);
        int i1 = companyService.unitAccount(map);//催缴人
        return i1;
    }
    /**
     * 上传方法
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("/upLoadPic")
    public Object upLoadPic(@RequestParam MultipartFile file){
        System.out.println("file="+file);
        String s = ftpUtil.upLoad(file);//调用上传方法
        System.out.println("s="+s);
        return s;
    }
    /**
     * 显示Ftp图片
     * @param fileName
     * @return
     */
    @RequestMapping("show")
    public ResponseEntity show(String fileName){
        System.out.println("fileName="+fileName);
        try {
            //  ftp://192.168.1.14/98f20a5d-7304-41c7-ac5a-db07d2aaffd3.png
            return ResponseEntity.ok(resourceLoader.getResource("ftp://"+ftpConfig.getFtpUserName()+":"+ftpConfig.getFtpPassWord()+"@"+ ftpConfig.getRemoteIp()+ftpConfig.getRemotePath() + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
}
