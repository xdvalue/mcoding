package com.mcoding.base.auth.web.security.impl;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by LiBing on 2014-07-26  17:09
 */
public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {
	private static final long serialVersionUID = 1L;
	
	private String nearbyName;
    private String nearbyPwd;
    private String loginType;

    public CustomAuthenticationToken(Object principal, Object credentials, String nearbyName, String nearbyPwd, String loginType) {
        super(principal, credentials);
        this.nearbyName = nearbyName;
        this.nearbyPwd = nearbyPwd;
        this.loginType = loginType;
    }

    public CustomAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String nearbyName, String nearbyPwd, String loginType) {
        super(principal, credentials, authorities);
        this.nearbyName = nearbyName;
        this.nearbyPwd = nearbyPwd;
        this.loginType = loginType;
    }

    public String getNearbyName() {
        return nearbyName;
    }

    public void setNearbyName(String nearbyName) {
        this.nearbyName = nearbyName;
    }

    public String getNearbyPwd() {
        return nearbyPwd;
    }

    public void setNearbyPwd(String nearbyPwd) {
        this.nearbyPwd = nearbyPwd;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
