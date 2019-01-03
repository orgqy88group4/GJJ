package com.aaa.gjj.controller;

import com.aaa.gjj.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * className:RoleController
 * discription:
 * author:lin
 * createTime:2018-12-15 09:35
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 角色表
     * @return
     */
    @ResponseBody
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        Map resultMap = new HashMap();
        resultMap.put("pageData",roleService.getPageByParam(map));
        resultMap.put("total",roleService.getPageCount(map));
        return resultMap;
    }
    /**
     * 角色添加
     * @return
     */
    @RequestMapping("/add")
    public void add(@RequestBody Map map, HttpServletResponse response)throws IOException {
        int add = roleService.add(map);
        if(add==-1) {
            response.getWriter().print(0);
        }else {
            response.getWriter().print(1);
        }
    }

    /**
     * 根据ID获取集合对象
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/row")
    public Object getById(@RequestBody Map map){
        return roleService.getById(map);
    }

    /**
     * 角色信息修改
     * @param paramMap
     * @param response
     * @throws IOException
     */
    @RequestMapping("/update")
    public void update(@RequestBody Map paramMap, HttpServletResponse response) throws IOException{
        int update = roleService.update(paramMap);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        if(update==-1) {
            response.getWriter().print(0);
        }else {
            response.getWriter().print(1);
        }
    }
    /**
     * 角色信息修改
     * @param roleId
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/getPower")
    public Object getPowerCheckedTree(@RequestBody String roleId){
        return roleService.getPowerCheckedTree(roleId);
    }

    /**
     * 根据id删除
     * @param paramMap
     * @return
     *
     */
    @RequestMapping("/dele")
    public void delete(@RequestBody Map paramMap,HttpServletResponse response) throws IOException{//单个属性传值，形参的名字必须和jsp页面上的一致
        int id= (int) paramMap.get("roleId");
        int delete = roleService.delete(id);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        if(delete==-1) {
            response.getWriter().print(0);
        }else {
            response.getWriter().print(1);
        }
    }
    /**
     * 角色权限修改
     * @param paramMap
     * @return
     */
    @RequestMapping("/saveRolePower")
    public void saveRolePower(@RequestBody Map paramMap,HttpServletResponse response) throws IOException{
        System.out.println(paramMap);
        int saveRolePower = roleService.addRolePower(paramMap);
        if(saveRolePower==-1){
            response.getWriter().print(0);
        }else {
            response.getWriter().print(1);
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/batchDel/{ids}")
    @ResponseBody
    public Object batchDel(@PathVariable String ids){
        return  roleService.batchDelete(ids);
    }

    /*--------------------------------------------账户管理-----------------------------------------*/
    /**
     * 账户表
     * @return
     */
    @ResponseBody
    @RequestMapping("/count")
    public Object count(@RequestBody Map map){
        Map resultMap = new HashMap();
        resultMap.put("countData",roleService.getPageByParamC(map));
        resultMap.put("total",roleService.getPageCountC(map));
        return resultMap;
    }
    /**
     * 账户表
     * @return
     */
    @ResponseBody
    @RequestMapping("/count1")
    public Object count1(@RequestBody Map map){
        return roleService.getPageByParamC1(map);
    }
    /**
     * 账户添加
     * @return
     */
    @RequestMapping("/addAccount")
    public void addAccount(@RequestBody Map map, HttpServletResponse response)throws IOException {
        int add = roleService.addAccount(map);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        if(add==-1) {
            response.getWriter().print(0);
        }else {
            response.getWriter().print(1);
        }
    }

    /**
     * 角色信息修改
     * @param paramMap
     * @param response
     * @throws IOException
     */
    @RequestMapping("/updateAccount")
    public void updateAccount(@RequestBody Map paramMap, HttpServletResponse response) throws IOException{
        int update = roleService.updateAccount(paramMap);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        if(update==-1) {
            response.getWriter().print(0);
        }else {
            response.getWriter().print(1);
        }
    }
    /**
     * 根据id删除
     * @param paramMap
     * @return
     *
     */
    @RequestMapping("/deleteAccount")
    public void deleteAccount(@RequestBody Map paramMap,HttpServletResponse response) throws IOException{//单个属性传值，形参的名字必须和jsp页面上的一致
        int id= (int) paramMap.get("account_Id");
        int delete = roleService.deleteAccount(id);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        if(delete==-1) {
            response.getWriter().print(0);
        }else {
            response.getWriter().print(1);
        }
    }


}
