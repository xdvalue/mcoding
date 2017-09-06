package com.mcoding.base.session;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.session.web.http.DefaultCookieSerializer;

import com.mcoding.base.ui.utils.spring.SpringContextHolder;

public class McodingCookieSerializer extends DefaultCookieSerializer {
	
	public void writeCookieValue(CookieValue cookieValue) {
		HttpServletRequest request = cookieValue.getRequest();
		String domain = request.getServerName();
		if (domain.contains("localhost") || domain.contains("127.0.0.1")) {
			this.setDomainNamePattern("(localhost|127\\.0\\.0\\.1)");
		}else{
			Properties sysConfig = SpringContextHolder.getBean("sysConfig");
			this.setDomainNamePattern(sysConfig.getProperty("http.session.cookie.domain.pattern"));
		}
		
		super.writeCookieValue(cookieValue);
		
	}

}
