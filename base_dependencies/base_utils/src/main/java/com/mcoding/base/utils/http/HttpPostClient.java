package com.mcoding.base.utils.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.lang.ArrayUtils;

import com.mcoding.base.utils.json.JsonUtilsForMcoding;

public class HttpPostClient {
	
	public static String sendJson(String url, String json) throws NullPointerException, HttpException, IOException{
		return sendJson(url, json, String.class);
	}
	
	public static <T> T sendJson(String url, String json, Class<T> valueType) throws NullPointerException, HttpException, IOException{
		return sendJson(url, null, json, valueType);
	}
	
    public static <T> T sendJson(String url, NameValuePair[] queryParams, String json, Class<T> valueType) throws NullPointerException, HttpException, IOException{
		return sendJson(url, queryParams, null, json, valueType);
	}
    
	public static <T> T sendJson(String url, NameValuePair[] queryParams, Header[] headers, String json, Class<T> valueType) throws NullPointerException, HttpException, IOException{
    	Header[] newHeaders = (Header[]) ArrayUtils.add(headers, new Header("Content-Type", "application/json"));
    	RequestEntity entitie = new ByteArrayRequestEntity(json.getBytes());
		return send(url, queryParams, newHeaders, new RequestEntity[]{entitie}, valueType);
	}
	
	
	public static String send(String url) throws NullPointerException, HttpException, IOException{
		return send(url, String.class);
	}
	
	public static <T> T send(String url, Class<T> valueType) throws NullPointerException, HttpException, IOException{
		return send(url, null, valueType);
	}
	
	public static String send(String url, NameValuePair[] queryParams) throws NullPointerException, HttpException, IOException{
		return send(url, queryParams, String.class);
	}
	
	public static <T> T send(String url, NameValuePair[] queryParams, Class<T> valueType) throws NullPointerException, HttpException, IOException{
		return send(url, queryParams, null, null, valueType);
	}
	
	public static String send(String url, NameValuePair[] queryParams, Header[] headers) throws NullPointerException, HttpException, IOException{
		return send(url, queryParams, headers, null, String.class);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T send(String url, NameValuePair[] queryParams, Header[] headers, RequestEntity[] entities, Class<T> valueType) throws NullPointerException, HttpException, IOException{
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod();
		URI uri = new URI(url, false);
		
		method.setURI(uri);
		if (queryParams != null && queryParams.length >0) {
			method.setQueryString(queryParams);
		}
		
		for(int i=0; headers!=null && i<headers.length; i++){
			method.setRequestHeader(headers[i]);
		}
		
		for(int i=0; entities!=null && i<entities.length; i++){
			method.setRequestEntity(entities[i]);
		}
		
		int result = client.executeMethod(method);
//		if (result != HttpStatus.SC_OK) {
//			throw new HttpException("http post failed, status:" + result);
//		}
		if (result <200 || result >=400) {
			throw new HttpException("http post failed, status:" + result + ", body:" + getResponseBody(method));
		}
		
		String responseBody = getResponseBody(method);
		if (valueType.equals(String.class)) {
			return (T) responseBody;
		}
		return JsonUtilsForMcoding.convertValue(responseBody, valueType);
	}
	
	private static String getResponseBody(PostMethod method) throws IOException {
		InputStream in = method.getResponseBodyAsStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = in.read(buffer)) != -1) {
			baos.write(buffer, 0, len);
		}
		
		return baos.toString("UTF8");
	}
	
	public static void main(String[] args) throws NullPointerException, HttpException, IOException {
//		JsonResult result = HttpPostClient.send("http://my.coding.mobi/imp_crm/test/front/currentMember", JsonResult.class);
//		System.out.println(result.getData());
	}

}
