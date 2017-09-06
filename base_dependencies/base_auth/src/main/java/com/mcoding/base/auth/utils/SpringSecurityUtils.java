package com.mcoding.base.auth.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.mcoding.base.auth.bean.User;

/**
 * Created by LiBing on 2014-07-04  14:30
 */
public class SpringSecurityUtils {
    //获取资源的基本路径
//    public static String getBaseRootPath(){
//        String baseRootPath = "http://localhost:8080/EMIS/";
//        if(!log.isDebugEnabled()){
//            baseRootPath = "http://v.merryplus.com/";
//        }
//        return baseRootPath;
//    }
    //获取用户Id
    public static int getLoginUserId() {
        int userId = -1;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object obj = authentication.getPrincipal();
        if (obj instanceof User) {
            User user = (User) obj;
            userId = user.getUserId();
        }
        return userId;
    }

    //获取登录用户对象
    public static User getLoginUser() {
        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object obj = authentication.getPrincipal();
        if (obj instanceof User) {
            user = (User) obj;
        }
        return user;
    }

    //获取登录用户登录账号
    public static String getLoginUserName() {
        String loginName = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object obj = authentication.getPrincipal();
        if (obj instanceof User) {
            User user = (User) obj;
            if (user != null) {
                loginName = user.getLoginName();
            }
        }
        return loginName;
    }

    //获取登录用户的角色名称列表
    @SuppressWarnings("unchecked")
	public static List<String> getLoginUserRoleNameList() {
        List<String> roleNames = new ArrayList<String>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            String roleName = authority.getAuthority();
            roleNames.add(roleName);
        }
        return roleNames;
    }

    //判断登录用户是否是管理员身份(是管理员身份就可以看该栏目的所有数据，否则只能看到自己的数据)
    public static  boolean isManagerLoginUser(){
        if("admin".equals(getLoginUserName())){
            return true;
        }
        List<String> roles = getLoginUserRoleNameList();
        if(roles.contains("系统管理员")){
            return true;
        }
        return false;
    }

}
