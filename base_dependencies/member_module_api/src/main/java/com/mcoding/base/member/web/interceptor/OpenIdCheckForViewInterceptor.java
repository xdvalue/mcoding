package com.mcoding.base.member.web.interceptor;

import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.service.member.MemberService;
import com.mcoding.base.member.service.wechat.StoreWxRefService;

public class OpenIdCheckForViewInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	protected MemberService memberService;

	@Resource
	protected StoreWxRefService storeWxRefService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String openid = (String) request.getSession().getAttribute("openid");
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = (Integer) request.getSession().getAttribute("memberId");

		/*if (StringUtils.isBlank(openid)) {
			String openidFromParamter = request.getParameter("openid");
			String openidFromAttribute = (String) request.getAttribute("openid");
			openid = openidFromAttribute != null ? openidFromAttribute : openidFromParamter;

			if (openid != null) {
				request.getSession().setAttribute("openid", openid);
			}
		}*/

		if (StringUtils.isNotBlank(openid)) {

			if (member == null) {
				member = this.memberService.createOrEditByOpenId(openid);
				request.getSession().setAttribute("member", member);
				request.getSession().setAttribute("memberId", member.getId());
			}

			if (memberId == null) {
				request.getSession().setAttribute("memberId", member.getId());
			}

			return super.preHandle(request, response, handler);
		}

		String url = request.getRequestURL().toString();
		URL targetUrl = new URL(url);
		String scheme = targetUrl.getProtocol();
		String serverName = targetUrl.getHost();
		int serverPort = targetUrl.getPort();
		String requestUri = targetUrl.getPath();
		String ref = targetUrl.getRef();


//		String scheme = request.getProtocol();
//		String serverName = request.getServerName();
//		int serverPort = request.getRemotePort();
//		String requestUri = request.getRequestURI();
//		String query = request.getpa
//		String ref = targetUrl.getRef();
		
		Map<String, String> params = this.getMapFormQuery(request);

		if (serverPort < 0) {
			serverPort = 80;
		}

		String oauth2AuthorizeUrl = this.storeWxRefService.queryWechatOauthUrlForOpenId(scheme, serverName, serverPort,
				requestUri, params, ref);

		logger.info("not found openid, now redirect to oauth url: " + oauth2AuthorizeUrl);
		response.sendRedirect(oauth2AuthorizeUrl);
		return false;
	}

	private Map<String, String> getMapFormQuery(HttpServletRequest request) {
		Map<String, String> params = new HashMap<>();
		
		Enumeration enu=request.getParameterNames();  
		while(enu.hasMoreElements()){  
		    String paraName=(String)enu.nextElement();  
		    System.out.println(paraName+": "+request.getParameter(paraName));
		    params.put(paraName, request.getParameter(paraName));
		}  

		return params;
		/*if (StringUtils.isBlank(query)) {
			return null;
		}
		Pattern pattern = Pattern.compile("(.*?)\\=(.*?)(&|$)");
		Map<String, String> params = new HashMap<>();
		Matcher matcher = pattern.matcher(query);
		
		while (matcher.find()) {
			String key = matcher.group(1);
			String value = matcher.group(2);
			params.put(key, value);
		}
		return params;*/

	}

}
