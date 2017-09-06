package com.mcoding.base.auth.web.security.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.mcoding.base.auth.bean.Role;
import com.mcoding.base.auth.bean.RoleExample;
import com.mcoding.base.auth.service.RoleRightService;
import com.mcoding.base.auth.service.RoleService;
import com.mcoding.base.auth.utils.AuthConstant;

/**
 * 操作权限生成器，针对于用户操作(例如：url请求),定义操作所需要的权限。
 * @author hzy
 *
 */
@Component("customFilterInvocationSecurityMetadataSource")
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private static Pattern pattern = Pattern.compile("(.+?)\\?.+");
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private RoleRightService roleRightService;

	@Override
	public Collection<ConfigAttribute> getAttributes(Object Obj) throws IllegalArgumentException {
		String url = ((FilterInvocation) Obj).getRequestUrl().trim();
		if (StringUtils.isBlank(url)) {
			return null;
		}
		if ("/".equals(url)) {
			return null;
		}
		
		if (url.contains("?")) {
			Matcher matcher = pattern.matcher(url);
			url = matcher.find() ? matcher.group(1) : url; 
		}
		
		List<Role> roleList = this.roleRightService.getRoleByOperatorUrl(url);
		Collection<ConfigAttribute> tmpList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(roleList)) {
			CollectionUtils.addAll(tmpList, roleList.iterator());
			
		}else {
			tmpList.add(AuthConstant.DEFAULT_ROLE);
		}
		
		return tmpList;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		RoleExample roleExample = new RoleExample();
//		List<Role> roleList = this.roleMapper.selectByExample(roleExample);
		List<Role> roleList = this.roleService.queryAllObjByExample(roleExample);
		
		Collection<ConfigAttribute> tmpList = new ArrayList<>();
		CollectionUtils.addAll(tmpList, roleList.iterator());
		return tmpList;
		
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}
	
	
}
