package com.mcoding.base.auth.web.security.impl;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by LiBing on 2014/6/9.
 */
public class CustomFilterSecurityInterceptorImpl extends AbstractSecurityInterceptor implements Filter {

    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation invocation = new FilterInvocation(request, response, filterChain);
        infoke(invocation);
    }

    private void infoke(FilterInvocation fi) throws IOException, ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }
}
