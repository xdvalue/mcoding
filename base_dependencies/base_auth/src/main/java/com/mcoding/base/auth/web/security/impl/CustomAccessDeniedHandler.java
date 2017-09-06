package com.mcoding.base.auth.web.security.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.utils.http.HttpRequestUtils;
import com.mcoding.base.utils.http.HttpResponseUtils;

/**
 * 用户没有权限时的操作
 * @author hzy
 *
 */
public class CustomAccessDeniedHandler extends AccessDeniedHandlerImpl {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
			throws IOException, ServletException {
		
		// 如果是ajax请求
		if (HttpRequestUtils.isAjaxRequest(request)) {

			JsonResult<String> result = new JsonResult<>();
			result.setStatus("403");
			result.setMsg("没有权限操作");
			
			HttpResponseUtils.responseResutlAsJson(response, result);
			return;
			
		} else {
			super.handle(request, response, accessDeniedException);
		}
	}

}
