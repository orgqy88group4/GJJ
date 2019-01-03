package com.aaa.gjj.controller;

import com.aaa.gjj.service.GJJExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * className:GJJExtractController
 * discription:
 * author:dujihu
 * createTime:2018-12-13 15:22
 */
@Controller
@RequestMapping("/extract")
public class GJJExtractController {

    @Autowired
    private GJJExtractService gjjExtractService;

    /**
     * 跳转分页列表
     * @return
     */
    @RequestMapping("/toList")
    public String toList(){

        return "GJJExtract/list";
    }

    /**
     * 跳转约定提取分页列表
     * @return
     */
    @RequestMapping("/toPage")
    public String toPage(){

        return "GJJExtract/appointExtract";
    }

    /**
     * 跳转普通提取审核分页列表
     * @return
     */
    @RequestMapping("/toExamine")
    public String toExamine(){

        return "GJJExtract/ExtractAudit";
    }
    /**
     * 跳转约定提取审核分页列表
     * @return
     */
    @RequestMapping("/toAppointExamine")
    public String toAppointExamine(){

        return "GJJExtract/appointExtractAudit";
    }
    /**
     * 跳转提取还贷页面
     * @return
     */
    @RequestMapping("/toTQHD")
    public String toTQHD(){

        return "GJJExtract/TQHD";
    }


    /**
     * 提取分页
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Object list(@RequestBody Map map ){
        Map resultMap = new HashMap();
        resultMap.put("pageData",gjjExtractService.getPageByParam(map));
        resultMap.put("total",gjjExtractService.getPageCount(map));
        return resultMap;
    }

    /**
     * 约定提取分页
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/page")
    public Object page(@RequestBody Map map ){
        Map resultMap = new HashMap();
        resultMap.put("pageData",gjjExtractService.getAppointPageByparam(map));
        resultMap.put("total",gjjExtractService.getAppointPageCount(map));
        return resultMap;
    }



    /**
     * 公积金提取
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public Object addExtract(@RequestBody Map map){
        return  gjjExtractService.addExtract(map);
    }

    /**
     * 获取详情
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/views")
    public Object view(@RequestBody Map map ){
        Map resultMap = new HashMap();
        resultMap.put("pageData",gjjExtractService.getView(map));
        return resultMap ;
    }
    /**
     * 普通审核分页
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/examine")
    public Object examine(@RequestBody Map map ){
        Map resultMap = new HashMap();
        resultMap.put("pageData",gjjExtractService.getExaminePageByparam(map));
        resultMap.put("total",gjjExtractService.getExaminePageCount(map));
        return resultMap;
    }

    /**
     * 审核通过
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/auditPass")
    public Object audit(@RequestBody Map map ){
        return  gjjExtractService.BFtongGuo(map);
    }
    /**
     * 审核驳回
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/auditLose")
    public Object auditLose(@RequestBody Map map ){
        return gjjExtractService.BFboHui(map);
    }

    /**
     * 约定审核通过
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/appointAuditPass")
    public Object appointAuditPass(@RequestBody Map map ){
        return  gjjExtractService.YDTQtongguo(map);
    }
    /**
     * 约定审核驳回
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/appointAuditLose")
    public Object appointAuditLose(@RequestBody Map map ){
        return gjjExtractService.YDTQbohui(map);
    }

    /**
     * 销户审核通过
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/XHAuditPass")
    public Object XHAuditPass(@RequestBody Map map ){
        return gjjExtractService.xiaoHu(map);
    }

    /**
     * 验证约定提取人员
     * @return
     */
    @ResponseBody
    @RequestMapping("/yanZhengYDTQ")
    public Object yanZhengYDTQrenyuan(@RequestBody Map map) {
        int i = gjjExtractService.yanZhengYDTQrenyuan(map);
        return i;
    }

    /**
     * 提取还贷
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/repayLoan")
    public Object repayLoan(@RequestBody Map map ){
        Map resultMap = new HashMap();
        resultMap.put("pageData",gjjExtractService.getTQHD(map));
        return resultMap ;
    }

    /**
     * 提取还贷修改表
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/tqhdEdit")
    public Object tqhdEdit(@RequestBody Map map ){
        return gjjExtractService.TQHDEdit(map);
    }


    /**
     * 添加共同还款人
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/addPerson")
    public Object addPerson(@RequestBody Map map ){
        Map resultMap = new HashMap();
        resultMap.put("pageData",gjjExtractService.addPerson(map));
        resultMap.put("total",gjjExtractService.addPersonCount(map));
        return resultMap;
    }

    /**
     * 批量添加
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/batchAdd")
    public Object batchAdd(@RequestBody Map map){

        return  gjjExtractService.addBatchPerson(map);
    }

    /**
     * 查询共同还款人
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPerson")
    public Object getPerson(@RequestBody Map map ){
        Map resultMap = new HashMap();
        resultMap.put("pageData",gjjExtractService.getBatchPerson(map));
        return resultMap ;
    }

    /**
     * 批量删除
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/batchDel")
    public Object batchDel(@RequestBody Map map){
        return gjjExtractService.delBatchPerson(map);
    }

}
