package com.mcoding.comp.sms.tcbj;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.HttpStatus;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;
import com.mcoding.comp.sms.utils.SmsSendException;

public class AccessTokenManager {
	
	private static AccessToken accessToken = null;
	private static AccessTokenManager accessTokenManager = new AccessTokenManager();
	private static long time_extral = 5000; //预留时间，5秒，如果距离过期少于5秒，就当作过期
	
	private String host;
	private Integer port;
	private String tokenUrl;
	private String appid;
	private String appSecret;
	
	private AccessTokenManager(){
		super();
		this.init();
	}
	
	public static AccessTokenManager getInstance(){
		return accessTokenManager;
	}
	
	public AccessToken getToken() throws Exception{
		if(accessToken == null || 
				accessToken.getExpiredTime() == null || 
				new Date().getTime() > (accessToken.getExpiredTime().getTime() - time_extral)){
			accessToken = this.getTokenFromTcbjEsb();
		}
		
		return accessToken;
	}

	/**
	 * 从汤臣的esb接口，获取accesstoken
	 * @return
	 * @throws Exception
	 */
	private AccessToken getTokenFromTcbjEsb() throws Exception {
		AccessToken accessToken = null;
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod();
		try {
			URI tcbjServer = new URI(this.host+":"+this.port, false);
			
			method.setURI(new URI(tcbjServer, this.tokenUrl, false));
			method.setQueryString(new NameValuePair[] { 
					new NameValuePair("appId", this.appid),
					new NameValuePair("appSecret", this.appSecret)
				});
			
			int result = client.executeMethod(method);
			if (result != HttpStatus.SC_OK) {
				throw new SmsSendException("GET ACCESS TOKEN FAIL. HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
			}
			
			String responseBody = this.getResponseBody(method);
			accessToken = JsonUtilsForMcoding.convertValue(responseBody, AccessToken.class);
			if (!accessToken.getErrorCode().equals(AccessToken.SUCCESS_CODE)) {
				throw new SmsSendException("GET ACCESS TOKEN FAIL. TCBJ RETURN CODE["+accessToken.getErrorCode()+"], MSG["+accessToken.getErrorMessage()+"]");
			}
				
		} catch (Exception e) {
			throw new Exception(e);
			
		} finally {
			method.releaseConnection();
		}
		
		return accessToken;
	}
	
	private String getResponseBody(GetMethod method) throws IOException {
		InputStream in = method.getResponseBodyAsStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = in.read(buffer)) != -1) {
			baos.write(buffer, 0, len);
		}
		
		return baos.toString("UTF8");
	}
	
	private void init(){
//		Properties smsConfigProperties = SpringContextHolder.getBean("smsConfigProperties");
		
		Properties smsConfigProperties = new Properties();
		try {
			InputStream input= this.getClass().getClassLoader().getResourceAsStream("sms.tcbj.properties");
			InputStreamReader reader = new InputStreamReader(input);
			
			if (!reader.ready()) {
				reader = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("/sms.tcbj.properties"));
				if (reader == null) {
					throw new CommonException("无法读取配置文件sms.tcbj.properties");
				}
			}
			smsConfigProperties.load(reader);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		this.host = smsConfigProperties.getProperty("sms.tcbj.server.host");
		this.port = Integer.valueOf(smsConfigProperties.getProperty("sms.tcbj.server.port"));
		
		this.tokenUrl = smsConfigProperties.getProperty("sms.tcbj.token.url");
		this.appid = smsConfigProperties.getProperty("sms.tcbj.token.appid");
		this.appSecret = smsConfigProperties.getProperty("sms.tcbj.token.appSecret");
	}

}
