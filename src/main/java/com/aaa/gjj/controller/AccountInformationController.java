package com.aaa.gjj.controller;

import com.aaa.gjj.service.AccountInformationService;
import com.aaa.gjj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    @RequestMapping("/AccountInformation")
    private Object AccountInformation(@RequestParam Map map){
        List<Map> accountInformation = Account.AccountInformation(map);
        Map tempMap = new HashMap();
        tempMap.put("rows", accountInformation);
        tempMap.put("total",Account.AccountInformationCount(map));
        return tempMap;
    }
    //个人信息修改
    @ResponseBody
    @RequestMapping("/particulars")
    private Object particulars(@RequestParam Map map){
        List<Map> particulars = Account.particulars(map);
        return particulars;
    }

    //个人信息修改完毕提交按钮
    @RequestMapping("/modification")
    private Object modification(@RequestParam Map map){
        int modification = Account.modification(map);
        System.out.println(modification);
        int modification2 = Account.modification2(map);
        System.out.println(modification2);
        if(modification==1&&modification2==1){
            return "redirect:/PersonalDetails";
        }
        return null;
    }
    //个人信息修改完毕提交按钮跳到个人信息页面
    @RequestMapping("/PersonalDetails")
    public String PersonalDetails(){
        System.out.println("跳到了。。。。");
        return "back/person/PersonDatil";
    }
    //单位信息
    @ResponseBody
    @RequestMapping("/UnitInformation")
    private Object UnitInformation(@RequestParam Map map){
        List<Map> UnitInformation = Account.UnitInformation(map);
        Map tempMap = new HashMap();
        tempMap.put("rows", UnitInformation);
        tempMap.put("total",Account.UnitInformationCount(map));
        return tempMap;
    }
    //单位信息修改按钮
    @ResponseBody
    @RequestMapping("/tan")
    private Object tan(@RequestParam Map map){
        List<Map> tan = Account.tan(map);
        return tan;
    }
    //单位信息提交更改按钮
    @RequestMapping("/unitModification")
    private Object unitModification(@RequestParam Map map, @RequestParam MultipartFile pic){
        if (pic!=null){
            String newFileName = FileUtil.uploadFile(uploadPath, pic);
            map.put("fileName",pic.getOriginalFilename());
            map.put("picPath",newFileName);
        }
//        if(!yz.isEmpty()){
//            String uploadFile = FileUploadUtil.uploadFile("D:\\GJJ\\GJJ\\src\\main\\webapp/files/comreg",yz,request);
//            map.put("yz", uploadFile);
//        }else{
//            map.put("yz", map.get("YYZZ"));
//        }
        int unitModification = Account.unitModification(map);
        System.out.println(unitModification);
        int unitModification2 = Account.unitModification2(map);
        System.out.println(unitModification2);
        if(unitModification == 1&&unitModification2 == 1){
            return "redirect:/UnitInformationa";
        }
        return null;
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
