package com.mcoding.base.auth.web.security.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.utils.http.HttpRequestUtils;
import com.mcoding.base.utils.http.HttpResponseUtils;

/**
 * 用户登录失败的处理器,添加ajax请求的处理
 * @author hzy
 *
 */
public class CustomAuthenticationFailureHandler extends ExceptionMappingAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		if (HttpRequestUtils.isAjaxRequest(request)) {
			JsonResult<String> result = new JsonResult<>();
			result.setStatus("403");
			result.setMsg(this.getErrMsg(exception));
			
			HttpResponseUtils.responseResutlAsJson(response, result);
			return;
		}

		super.onAuthenticationFailure(request, response, exception);
	}

	/**
	 * 获取登录失败的原因
	 * @param exception
	 * @return
	 */
	private String getErrMsg(AuthenticationException exception) {
		if (exception instanceof AccountExpiredException) {
			return "账户过期";
		}else if (exception instanceof DisabledException) {
			return "账户已禁用";
		}else if (exception instanceof LockedException) {
			return "帐号被锁定";
		}else if (exception instanceof CredentialsExpiredException) {
			return "密码过期";
		}else if (exception instanceof AccountStatusException) {
			return "用户状态异常";
		}else if (exception instanceof BadCredentialsException) {
			return "密码错误";
		}else if (exception instanceof UsernameNotFoundException) {
			return "找不到用户名";
		}else if (exception instanceof SessionAuthenticationException) {
			return "session 状态异常";
		}else {
			return "登录失败";
		}
	}

}
