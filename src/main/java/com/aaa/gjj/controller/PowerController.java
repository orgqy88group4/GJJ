package com.aaa.gjj.controller;

import com.aaa.gjj.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * className:PowerController
 * discription:1211后台树
 * author:zz
 * createTime:2018-12-11 10:24
 */
@Controller
@RequestMapping("/power")
public class PowerController {

    //依赖注入
    @Autowired
    private PowerService powerService;

    /**
     * 权限树数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/tree")
    public Object tree(){
        return powerService.getList();
    }
}
