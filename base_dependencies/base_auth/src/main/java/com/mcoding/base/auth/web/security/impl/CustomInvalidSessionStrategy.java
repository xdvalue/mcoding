package com.mcoding.base.auth.web.security.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.utils.http.HttpRequestUtils;
import com.mcoding.base.utils.http.HttpResponseUtils;

/**
 * 登录session异常的处理器
 * @author hzy
 *
 */
public class CustomInvalidSessionStrategy implements InvalidSessionStrategy {

	private final Log logger = LogFactory.getLog(getClass());
	private final String destinationUrl;
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private boolean createNewSession = true;

	public CustomInvalidSessionStrategy(String invalidSessionUrl) {
		Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl),
				"url must start with '/' or with 'http(s)'");
		this.destinationUrl = invalidSessionUrl;
	}

	public void onInvalidSessionDetected(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		logger.debug("Starting new session (if required) and redirecting to '"
				+ destinationUrl + "'");
		if (createNewSession) {
			request.getSession();
		}
		
		if(HttpRequestUtils.isAjaxRequest(request)){
			JsonResult<String> result = new JsonResult<>();
			result.setStatus("401");
			result.setMsg("用户未登录 或登录已过期");
			HttpResponseUtils.responseResutlAsJson(response, result);
			return;
		}
		
		redirectStrategy.sendRedirect(request, response, destinationUrl);
	}

	/**
	 * Determines whether a new session should be created before redirecting (to avoid
	 * possible looping issues where the same session ID is sent with the redirected
	 * request). Alternatively, ensure that the configured URL does not pass through the
	 * {@code SessionManagementFilter}.
	 *
	 * @param createNewSession defaults to {@code true}.
	 */
	public void setCreateNewSession(boolean createNewSession) {
		this.createNewSession = createNewSession;
	}

}
