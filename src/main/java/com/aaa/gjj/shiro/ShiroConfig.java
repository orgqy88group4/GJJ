package com.aaa.gjj.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * className:ShiroConfig
 * discription:Shiro配置类
 * author:fhm
 * createTime:2018-12-04 20:00
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加shiro内置过滤器：可以实现权限相关的拦截器
        //常用的过滤器：
        //            1.anon:无需认证（登陆）可以访问
        //            2.authc:必须认证才可以访问
        //            3.user:如果使用rememberMe的功能可以直接访问
        //            4.perms:该资源必须得到资源权限才可以访问
        //            5.role:该资源必须得到角色权限才可以访问
        Map<String,String> filterMap=new LinkedHashMap<String,String>();
        /*filterMap.put("/user/toAdd","authc");
        filterMap.put("/user/toUpdate","authc");*/
        //某些页面放行
        filterMap.put("/user/testThymeleaf","anon");
        //放行user/login
        filterMap.put("/user/login","anon");

        //授权过滤器
        //注意：当授权拦截后，shiro会自动跳转到未授权页面
        filterMap.put("/user/toAdd","perms[user:add]");
        filterMap.put("/user/toUpdate","perms[user:update]");

        //拦截
        filterMap.put("/user/*","authc");

        //设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/user/unAuth");

        //修改调整的登录界面
        shiroFilterFactoryBean.setLoginUrl("/user/toLogin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return  securityManager;
    }
    /**
     * 创建Realm
     */
    @Bean(name="userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
}
