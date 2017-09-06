package com.mcoding.base.utils.http;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

public class HttpRequestUtils {

	public static String getBasePath(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/";
		return basePath;
	}
	
	@SuppressWarnings("rawtypes")
	public static Map<String, String> getQueryParam(HttpServletRequest request){
		Map<String, String> params = new HashMap<>();
		
		Enumeration enu=request.getParameterNames();  
		while(enu.hasMoreElements()){  
			String paraName=(String)enu.nextElement();  
			System.out.println(paraName+": "+request.getParameter(paraName));
			params.put(paraName, request.getParameter(paraName));
		}  
		
		return params;
	}
	
	/**
	 * 获取request的请求体
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String getRequestBody(HttpServletRequest request) throws IOException{
		request.setCharacterEncoding("UTF-8");
		
		StringBuffer bodyBuffer = new StringBuffer();
		ServletInputStream in = request.getInputStream();
		
		byte[] tmp = new byte[1024];
		while (in.read(tmp) != -1) {
			bodyBuffer.append(new String(tmp, "utf-8"));
		}
		
		return bodyBuffer.toString();
	}
	
	/**
	 * 判断请求是否是ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {

		boolean isAjax = "XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"));
		String ajax = request.getParameter("ajax");

		return isAjax || "true".equalsIgnoreCase(ajax);
	}


}
