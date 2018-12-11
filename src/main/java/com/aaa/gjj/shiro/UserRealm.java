package com.aaa.gjj.shiro;

import com.aaa.gjj.entity.User;
import com.aaa.gjj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * className:UserRealm
 * discription:自定义Realm
 * author:fhm
 * createTime:2018-12-04 20:26
 */
public class UserRealm extends AuthorizingRealm{
    //注入业务层
    @Autowired
    private UserService userService;

    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0){
        System.out.println("执行授权逻辑");
        //给资源授权
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

        //添加资源的授权字符串
        //info.addStringPermission("user:add");

        //到数据库查询当前登录用户的授权字符串
        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        User dbuser = userService.getUserById(user.getEmpNo());
        System.out.println(dbuser.getPerms());
        info.addStringPermission(dbuser.getPerms());
        return info;
    }

    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //假设数据库的用户名和密码
       /* String name="scott";
        String password="tiger";*/

        //编写shiro判断逻辑，判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token=(UsernamePasswordToken)arg0;
        System.out.println(token.getUsername());
        User user = userService.getUserByuserName(token.getUsername());
        //System.out.println(user.getEmpNo()+" "+user.getEname());

        if(user==null){
            //用户名不存在
            return null; //shiro底层会抛出UnknownAccountException
        }
        //2.判断密码
        return new SimpleAuthenticationInfo(user,user.getJob(),"");
    }
}
