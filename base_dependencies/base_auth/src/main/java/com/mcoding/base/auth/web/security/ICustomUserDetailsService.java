package com.mcoding.base.auth.web.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Libing on 2014/6/5.
 * 获取系统用户登录相关信息接口
 */
public interface ICustomUserDetailsService {
    UserDetails loadUserByUsername(String loginType, String userName, String pwd, String nearbyName, String nearbyPwd) throws UsernameNotFoundException;
}
