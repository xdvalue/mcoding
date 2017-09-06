package com.mcoding.base.utils.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;

import com.mcoding.base.utils.json.JsonUtilsForMcoding;

public class HttpGetClient {
	
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
		return send(url, queryParams, null, valueType);
	}
	
	public static String send(String url, NameValuePair[] queryParams, Header[] headers) throws NullPointerException, HttpException, IOException{
		return send(url, queryParams, headers, String.class);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T send(String url, NameValuePair[] queryParams, Header[] headers, Class<T> valueType) throws NullPointerException, HttpException, IOException{
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod();
		URI serverUri = new URI(url, false);
		
		method.setURI(serverUri);
		if (queryParams != null && queryParams.length >0) {
			method.setQueryString(queryParams);
		}
		
		for(int i=0; headers!=null && i<headers.length; i++){
			method.setRequestHeader(headers[i]);
		}
		
		int result = client.executeMethod(method);
		if (result != HttpStatus.SC_OK) {
			throw new HttpException("http get failed, status:" + result);
		}
		
		String responseBody = getResponseBody(method);
		if (valueType.equals(String.class)) {
			return (T) responseBody;
		}
		return JsonUtilsForMcoding.convertValue(responseBody, valueType);
	}
	
	private static String getResponseBody(GetMethod method) throws IOException {
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
//		Header header = new Header("accpect", "*");
//		String result = HttpGetClient.send("http://my.coding.mobi/imp_crm/test/front/currentMember");
//		System.out.println(result);
	}

}
