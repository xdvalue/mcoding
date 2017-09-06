package com.mcoding.base.auth.web.security.impl;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LiBing on 2014-07-26  17:01
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private String nearbyName="_nearbyName";
    private String nearbyPwd="_nearbyPwd";
    private String loginType="_loginType";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        String nearbyName = obtainNearbyName(request);
        String nearbyPwd = obtainNearbyPwd(request);
        String loginType = obtainLoginType(request);
        if (username == null) {
            username = "";
        }
        if (password == null) {
            password = "";
        }

        if (nearbyName == null) {
            nearbyName = "";
        }
        if (nearbyPwd == null) {
            nearbyPwd = "";
        }
        if (loginType == null) {
            loginType = "";
        }
        username = username.trim();
        nearbyName = nearbyName.trim();
        loginType = loginType.trim();

        UsernamePasswordAuthenticationToken authRequest = new CustomAuthenticationToken(username, password,nearbyName, nearbyPwd, loginType);

        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected String obtainNearbyName(HttpServletRequest request) {
        return request.getParameter(nearbyName);
    }

    protected String obtainNearbyPwd(HttpServletRequest request) {
        return request.getParameter(nearbyPwd);
    }

    protected String obtainLoginType(HttpServletRequest request) {
        return request.getParameter(loginType);
    }
}
