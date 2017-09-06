package com.mcoding.base.utils.http;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.mcoding.base.utils.json.JsonUtilsForMcoding;

public class HttpResponseUtils {
	
	/**
	 * 把对象转化为json字符串，返回给客户端
	 * @param response
	 * @param result
	 * @throws IOException
	 */
	public static void responseResutlAsJson(HttpServletResponse response, Object result) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.print(JsonUtilsForMcoding.writeValueAsString(result));
		out.flush();
		out.close();
	}

}
