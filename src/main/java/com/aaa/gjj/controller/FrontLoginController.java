package com.aaa.gjj.controller;

import com.aaa.gjj.service.FrontLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * className:FrontLoginController
 * discription:
 * author:dujihu
 * createTime:2018-12-11 17:13
 */
@Controller
@RequestMapping("/frontLogin")
public class FrontLoginController {
    @Autowired
    private FrontLoginService frontLoginService;

    @RequestMapping("/login")
    public Object frontLogin(@RequestParam Map paramMap, HttpSession session){
        List<Map> account = frontLoginService.getGRAccount(paramMap);
        if(account!=null&&account.size()>0){
            session.setAttribute("user",account.get(0));
            return "redirect:/szzfgjj/www.szzfgjj.com/index.htm";
        }
        return "redirect:/szzfgjj/www.szzfgjj.com/zmhd/zxts/login.htm" ;
    }

    @RequestMapping("/danwei")
    public Object danWeiLogin(@RequestParam Map paramMap, HttpSession session){
        List<Map> account = frontLoginService.getDWAccount(paramMap);
        if(account!=null&&account.size()>0){
            session.setAttribute("user",account.get(0));
            return "redirect:/szzfgjj/www.szzfgjj.com/index.htm";
        }
        return "redirect:/szzfgjj/www.szzfgjj.com/zmhd/zxts/login1.htm" ;
    }


}
