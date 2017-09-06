package com.mcoding.base.auth.web.security;

import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * Created by LiBing on 2014/6/5.
 * 获取系统资源信息借口
 */
public interface ICustomFilterInvocationSecurityMetadataSource extends FilterInvocationSecurityMetadataSource {
    void loadResourceDefine();
}
