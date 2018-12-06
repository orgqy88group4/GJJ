package com.aaa.gjj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * className:Test
 * discription:
 * author:qcm
 * createTime:2018-12-03 14:42
 */
@RestController
public class Test {
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
