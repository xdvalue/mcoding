package com.mcoding.comp.sms.tcbj;

import com.mcoding.comp.sms.SmsRequest;
import com.mcoding.comp.sms.SupplierAccount;
import com.mcoding.comp.sms.utils.PropertiesUtils;

public class TcbjAccount implements SupplierAccount{
	
	private String host;
	private Integer port;
	private String apiUrl;
	private String username;
	private String password;
	
	public TcbjAccount(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		
		this.host = PropertiesUtils.getProperty("sms.tcbj.server.host");
		this.port = Integer.valueOf(PropertiesUtils.getProperty("sms.tcbj.server.port"));
		this.apiUrl = PropertiesUtils.getProperty("sms.tcbj.server.url");
		
	}

	public TcbjAccount(String host, Integer port, String apiUrl, String username, String password) {
		super();
		this.host = host;
		this.port = port;
		this.apiUrl = apiUrl;
		this.username = username;
		this.password = password;
	}

	@Override
	public SmsRequest createRequest() {
		return new TcbjSmsRequest(this);
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
