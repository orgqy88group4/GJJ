package com.aaa.gjj.controller;

import com.aaa.gjj.service.TheCompanyBusinessService;
import com.aaa.gjj.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
public class TheCompanyBusinessController {
    @Autowired
    private TheCompanyBusinessService theCompanyBusinessService;

    /**
     * 单位开户 公司效验
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("unitCheckout")
    public Object unitRegisterCheckout(@RequestParam Map map){
        Map tempmap = new HashMap();
        List<Map> list = theCompanyBusinessService.getUnitInfoList(map);
        if(list!=null&&list.size()>0){
            for (Map map2 : list) {
                if(map2.get("uname").equals(map.get("uName"))){
                    tempmap.put("issc", false);
                    tempmap.put("error", "输入的公司名称已存在！");
                }
            }
        }
        return tempmap;
    }

    /**
     * 单位开户 机构代码效验
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("unitCheckout1")
    public Object unitRegisterCheckout1(@RequestParam Map map){
        Map tempmap = new HashMap();
        List<Map> list = theCompanyBusinessService.getUnitInfoList(map);
        if(list!=null&&list.size()>0){
            for (Map map2 : list) {
                if(map2.get("uNetworkCode").equals(map.get("uNetworkCode"))){
                    tempmap.put("issc", false);
                    tempmap.put("error1", "输入的机构代码已存在！");
                }
            }
        }
        return tempmap;
    }

    /**
     * 公司开户保存按钮
     * @param map
     * @param request
     * @param model
     * @param YYZZ
     * @return
     */
    @RequestMapping("registerInfo")
    public String registerInfo(@RequestParam Map map, HttpServletRequest request, Model model, @RequestParam MultipartFile YYZZ){
        String filePath = FileUploadUtil.uploadFile("files/comreg",YYZZ,request);
        map.put("YYZZ", filePath);
        int uid = theCompanyBusinessService.unitRegister(map);//���ʱ���ص�����
        if(uid>0){
            int num=theCompanyBusinessService.unitAccount(map,uid);//��λ�˺ű��������
            if(num>0){
                return "redirect:/UnitInformationa01";
            }else{
                return "注册出现错误了！！！";
            }
        }else{
            return "注册出现错误了！！！";
        }
    }
}
