package com.mcoding.base.auth.web.security.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mcoding.base.auth.bean.User;
import com.mcoding.base.auth.bean.UserExample;
import com.mcoding.base.auth.bean.UserRole;
import com.mcoding.base.auth.service.UserRoleService;
import com.mcoding.base.auth.service.UserService;
import com.mcoding.base.auth.utils.AuthConstant;

/**
 * 给用户增加对应权限
 * @author hzy
 *
 */
@Service("customUserDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Resource
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;
    
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		//取得登录用户
    	UserExample userExample = new UserExample();
    	userExample.createCriteria()
    	           .andLoginNameEqualTo(userName);
    	List<User> list = this.userService.queryAllObjByExample(userExample);
    	//账号被禁用时不可登陆
        if (list==null ||  list.size()==0) {
            throw new UsernameNotFoundException( "The user name "  + userName + " can not be found!" );
        }

        User user = list.get(0);
        if (user == null) {
        	throw new UsernameNotFoundException( "The user name "  + userName + " can not be found!" );
		}
        
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        //取得用户相关的权限信息
        List<UserRole> userRoleList = userRoleService.queryUserRoleList(user.getUserId());
        for (UserRole userRole : userRoleList) {
            GrantedAuthority authority = new SimpleGrantedAuthority(userRole.getRoleId().toString());
            authorities.add(authority);
        }
        authorities.add(new SimpleGrantedAuthority(AuthConstant.DEFAULT_ROLE.getRoleId().toString()));
        user.setAuthorities(authorities);

        return user;
	}
}
