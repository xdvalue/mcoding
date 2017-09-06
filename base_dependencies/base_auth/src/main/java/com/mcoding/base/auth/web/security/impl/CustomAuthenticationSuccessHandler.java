package com.mcoding.base.auth.web.security.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.utils.http.HttpRequestUtils;
import com.mcoding.base.utils.http.HttpResponseUtils;

/**
 * 登录成功处理器，区分页面登录，与ajax登录的不同的结果
 * @author hzy
 *
 */
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		if (HttpRequestUtils.isAjaxRequest(request)) {
			JsonResult<String> result = new JsonResult<>();
			result.setStatus("00");
			result.setMsg("login successful!");
			HttpResponseUtils.responseResutlAsJson(response, result);
			return;
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
}
