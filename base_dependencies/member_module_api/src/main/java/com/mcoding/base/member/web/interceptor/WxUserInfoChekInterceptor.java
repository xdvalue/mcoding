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

import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.service.member.MemberService;
import com.mcoding.base.member.service.wechat.StoreWxRefService;

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
		
		URL targetUrl = new URL(request.getRequestURL().toString());
		String scheme = targetUrl.getProtocol();
		String serverName = targetUrl.getHost();
		int serverPort = targetUrl.getPort();
		String requestUri = targetUrl.getPath();
		String ref = targetUrl.getRef();
		
		Map<String, String> params = this.getMapFormQuery(request);
		
		if (serverPort < 0) {
			serverPort = 80;
		}
		
		request.getSession().setAttribute("isGetWxUserInfo", "true");
		
		String oauth2AuthorizeUrl = this.storeWxRefService.queryWechatOauthUrlForWxUserInfo(scheme, serverName, serverPort, requestUri, params, ref);
		
		logger.info("not found openid, now redirect to oauth url: "+ oauth2AuthorizeUrl);
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
