package com.mcoding.base.ui.web.interceptor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HttpLogInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private ThreadLocal<Long> threadTime = new ThreadLocal<>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 获取输入参数
//		Map inputParamMap = request.getParameterMap();

		// 获取请求地址
		String schemem = request.getScheme();
		String serverName = request.getServerName();
		int serverPort = request.getServerPort();
		String requestPath = request.getRequestURI();
		String targetUrl = schemem + "://" + serverName + ":" + serverPort + requestPath;

//		String queryStr = request.getQueryString();
		String paramStr = this.getParams(request);
		
		if (StringUtils.isNotBlank(paramStr)) {
			targetUrl = targetUrl + "?" + paramStr;
		}
		
		
		logger.debug("start handle, url:" + targetUrl);
		long l1 = System.currentTimeMillis();
		threadTime.set(l1);
		return super.preHandle(request, response, handler);
	}
	
	private String getParams(HttpServletRequest request){
		Map<String, Object> params = request.getParameterMap();
		List<String> paramList = new ArrayList<>(params.size());
		Iterator<String> iterator = params.keySet().iterator();
		
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			String[] values = (String[]) params.get(key);
			if (values != null && values.length >0) {
				paramList.add(key + "=" + values[0]);
			}
		}
		
		return StringUtils.join(paramList, "&");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 获取输入参数
//		Map inputParamMap = request.getParameterMap();

		// 获取请求地址
		String schemem = request.getScheme();
		String serverName = request.getServerName();
		int serverPort = request.getServerPort();
		String requestPath = request.getRequestURI();
		String targetUrl = schemem + "://" + serverName + ":" + serverPort + requestPath;

//		String queryStr = request.getQueryString();
		String queryStr = this.getParams(request);
		if (StringUtils.isNotBlank(queryStr)) {
			targetUrl = targetUrl + "?" + queryStr;
		}
		long l1 = threadTime.get();
		logger.debug("end handle, cost["+(System.currentTimeMillis() -l1)+"ms] url:" + targetUrl);
		super.afterCompletion(request, response, handler, ex);
	}

}
