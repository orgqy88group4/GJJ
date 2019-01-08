package com.aaa.gjj.controller;

import com.aaa.gjj.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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
    public Object tree(@RequestBody Map map){
        return powerService.getList(map);
    }

    /**
     * 跳转权限树页面
     * @return
     */
    @RequestMapping("/toadd")
    public String toadd(){
        return "back/power/add";
    }

    /**
     * 权限菜单添加
     * @param paramMap
     * @param response
     * @throws IOException
     */
    @RequestMapping("/add")
    public void add(@RequestBody Map paramMap, HttpServletResponse response) throws IOException {
        System.out.println(paramMap);
        int update = powerService.add(paramMap);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        if(update==-1) {
            response.getWriter().print(0);
        }else {
            //response.getWriter().print("<script>window.parent.parent.location.href=window.parent.parent.location.href; </script>");
            response.getWriter().print(1);
        }
    }

    /**
     * 跳转更新页面
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/toUpdate")
    public Object toUpdate(int id){
        Map<String,Object> map=powerService.getById(id);
        return powerService.getById(id);
    }

    /**
     * 权限菜单修改
     * @param paramMap
     * @param response
     * @throws IOException
     */
    @RequestMapping("/update")
    public void update(@RequestBody Map paramMap, HttpServletResponse response) throws IOException{
        int update = powerService.update(paramMap);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        if(update==-1) {
            response.getWriter().print(0);
        }else {
            //response.getWriter().print("<script>window.parent.parent.location.href=window.parent.parent.location.href; </script>");
            response.getWriter().print(1);
        }
    }

    /**
     * 根据id删除
     * @param paramMap
     * @param response
     * @throws IOException
     */
    @RequestMapping("/delete")
    public void delete(@RequestBody Map paramMap,HttpServletResponse response) throws IOException{//单个属性传值，形参的名字必须和jsp页面上的一致
        int id= (int) paramMap.get("id");
        int delete = powerService.delete(id);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        if(delete==-1) {
            response.getWriter().print(0);
        }else {
            response.getWriter().print(1);
        }
    }

}
