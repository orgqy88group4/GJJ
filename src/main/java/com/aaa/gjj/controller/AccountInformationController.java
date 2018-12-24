package com.aaa.gjj.controller;

import com.aaa.gjj.service.AccountInformationService;
import com.aaa.gjj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
 * className:AccountInformationController
 * discription:
 * author:qcm
 * createTime:2018-12-09 18:54
 */
@Controller
public class AccountInformationController {
    @Autowired
    private AccountInformationService Account;

    private final ResourceLoader resourceLoader;
    @Autowired
    public AccountInformationController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    @Value("${upload.path}")
    private String uploadPath;

    //个人信息
    @ResponseBody
    @RequestMapping("/AccountInformation1")
    private Object AccountInformation1(@RequestBody Map map){
        List<Map> accountInformation = Account.AccountInformation1(map);
        Map tempMap = new HashMap();
        tempMap.put("pageDate", accountInformation);
        tempMap.put("total",Account.AccountInformationCount1(map));
        return tempMap;
    }

    //个人信息修改
    @ResponseBody
    @RequestMapping("/particulars1")
    private Object particulars1(@RequestBody Map map){
        System.out.println("map参数是"+map);
        List<Map> particulars = Account.particulars1(map);
        System.out.println("返回前台的值为"+particulars);
        return particulars;
    }

    //个人信息修改完毕提交按钮
    @ResponseBody
    @RequestMapping("/modification1")
    private Object modification1(@RequestBody Map map){
        System.out.println("页面修改完毕前台传来的值element"+map);
        int modification = Account.modification1(map);
        System.out.println(modification);
        int modification2 = Account.modification21(map);
        System.out.println(modification2);
        if(modification==1&&modification2==1){
            return 1;
        }
        return 0;
    }
    //单位信息
    @ResponseBody
    @RequestMapping("/UnitInformation1")
    private Object UnitInformation1(@RequestBody Map map){
        System.out.println("00000"+map);
        List<Map> UnitInformation = Account.UnitInformation1(map);
        Map tempMap = new HashMap();
        tempMap.put("pageDate", UnitInformation);
        tempMap.put("total",Account.UnitInformationCount1(map));
        return tempMap;
    }
    //单位信息修改按钮
    @ResponseBody
    @RequestMapping("/tan1")
    private Object tan1(@RequestBody Map map){
        List<Map> tan = Account.tan1(map);
        return tan;
    }
    //单位信息提交更改按钮
    @ResponseBody
    @RequestMapping("/unitModification1")
    private Object unitModification1(@RequestBody Map map){
        int unitModification = Account.unitModification1(map);
        System.out.println(unitModification);
        int unitModification2 = Account.unitModification21(map);
        System.out.println(unitModification2);
        if(unitModification == 1&&unitModification2 == 1){
            return 1;
        }
        return 0;
    }
    //显示图片
    @RequestMapping("show")
    public ResponseEntity show(String fileName){
        System.out.println("进入了show");
        try {
            // 由于是读取本机的文件，file是一定要加上的， uploadPath是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + uploadPath + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
}
