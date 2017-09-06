package com.mcoding.base.auth.web.security.impl;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mcoding.base.auth.bean.Role;

/**
 * 操作权限判断器：用于对比 操作需要的权限(url请求) 与 用户拥有的权限，判断出用户是否有权限进行该操作。
 * @author hzy
 *
 */
@Service("customAccessDecisionManager")
public class CustomAccessDecisionManagerImpl implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (null == configAttributes) {
            return;
        }

        //判断是否是超级管理员账号,如果是则直接跳过
        Object obj = authentication.getPrincipal();
        if(obj instanceof UserDetails){
            UserDetails details = (UserDetails) obj;
            if("admin".equals(details.getUsername())){
                return;
            }
        }

        //访问该uri所需要的角色列表
        Iterator<ConfigAttribute> attrs = configAttributes.iterator();

        while (attrs.hasNext()) {
            ConfigAttribute ca = attrs.next();
            String needRole = ((Role) ca).getAttribute();//访问该资源所需要的权限
            for (GrantedAuthority gra : authentication.getAuthorities()) {//gra:该用户拥有的权限
                if (needRole.trim().equals(gra.getAuthority().trim())) {
                    //放行
                    return;
                }
            }
        }
        //该用户没有权限访问该资源
        throw new AccessDeniedException("Access Denied");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
