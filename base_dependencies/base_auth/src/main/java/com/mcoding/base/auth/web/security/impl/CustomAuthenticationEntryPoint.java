package com.mcoding.base.auth.web.security.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.utils.http.HttpRequestUtils;
import com.mcoding.base.utils.http.HttpResponseUtils;

/**
 * 默认的登录入口
 * @author hzy
 *
 */
public class CustomAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

	public CustomAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		if (HttpRequestUtils.isAjaxRequest(request)) {
			
			JsonResult<String> result = new JsonResult<>();
			result.setStatus("401");
			result.setMsg("用户还没有登录");
			HttpResponseUtils.responseResutlAsJson(response, result);
			return;
		}
		
		super.commence(request, response, authException);
	}

}
