package com.aaa.gjj.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * className:UserController
 * discription:
 * author:fhm
 * createTime:2018-12-04 18:19
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 跳转到登陆页面
     *
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    /**
     * 跳转到登陆页面
     *
     * @return
     */
    @RequestMapping("/unAuth")
    public String unAuth() {
        return "noAuth";
    }

    /**
     * 登陆逻辑处理
     *
     * @return
     */
    @RequestMapping("/login")
    public String login(String name, String password,Model model) {
        System.out.println("用户名："+name+"，密码："+password);
        /**
         * 使用shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();

        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);

        //3.执行登陆方法
        try {
            subject.login(token);
            //登陆成功,跳转到主页
            return "redirect:/Page/toTree";

        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            //登录失败，用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "forward:/user/toLogin";
        } catch (IncorrectCredentialsException e){
            //e.printStackTrace();
            //登录失败，密码错误
            model.addAttribute("msg","密码不正确");
            return "forward:/user/toLogin";
        }
    }
}
