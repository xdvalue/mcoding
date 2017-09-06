package com.mcoding.base.member.web.interceptor;

import java.net.URL;
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

import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.service.member.MemberService;
import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.base.utils.http.HttpRequestUtils;

/**
 * 提交Ajax请求时候，检查openId的拦截器
 * @author hzy
 *
 */
public class WxUserInfoChekInterceptor extends HandlerInterceptorAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(WxUserInfoChekInterceptor.class); 
	
	@Resource
	protected MemberService memberService;
	
	@Resource
	protected StoreWxRefService storeWxRefService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String isGetWxUserInfo = (String) request.getSession().getAttribute("isGetWxUserInfo");
		
		String openid = (String) request.getSession().getAttribute("openid");
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = (Integer) request.getSession().getAttribute("memberId");
		
		if (StringUtils.isNotBlank(isGetWxUserInfo) && 
				StringUtils.isNotBlank(openid) &&
				member != null) {
			return super.preHandle(request, response, handler);
		}
		
		if(StringUtils.isBlank(openid)){
			String openidFromParamter = request.getParameter("openid");
			String openidFromAttribute = (String) request.getAttribute("openid");
			openid = openidFromAttribute != null ? openidFromAttribute : openidFromParamter;
			
			if (openid != null) {
				request.getSession().setAttribute("openid", openid);
			}
		}
		
		if(StringUtils.isNotBlank(openid) && member == null){
			member = this.memberService.createOrEditByOpenId(openid);
			request.getSession().setAttribute("member", member);
			request.getSession().setAttribute("memberId", member.getId());
			
			if (memberId == null) {
				request.getSession().setAttribute("memberId", member.getId());
			}
		}
		String url = request.getRequestURL().toString();
		URL targetUrl = new URL(url);
		Matcher matcher = Pattern.compile("(http|https)://(.*?)/").matcher(StoreUtils.getStoreFromThreadLocal().getStoreDomain().split(";")[0]);
		
		String serverName = null;
		if (matcher.find()) {
			serverName = matcher.group(2);
		}else{
		    serverName = targetUrl.getHost();
		}
		
		String scheme = targetUrl.getProtocol();
//		String serverName = targetUrl.getHost();
		int serverPort = targetUrl.getPort();
		String requestUri = targetUrl.getPath();
		String ref = targetUrl.getRef();
		
		Map<String, String> params = HttpRequestUtils.getQueryParam(request);
		
		if (serverPort < 0) {
			serverPort = 80;
		}
		
		request.getSession().setAttribute("isGetWxUserInfo", "true");
		
		String oauth2AuthorizeUrl = this.storeWxRefService.queryWechatOauthUrlForWxUserInfo(scheme, serverName, serverPort, requestUri, params, ref);
		
		logger.info("not found openid, now redirect to oauth url: "+ oauth2AuthorizeUrl);
		response.sendRedirect(oauth2AuthorizeUrl);
		return false;
	}
	
}
