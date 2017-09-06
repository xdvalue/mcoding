package com.mcoding.comp.sms.chuanglan;

import com.mcoding.comp.sms.SmsRequest;
import com.mcoding.comp.sms.SupplierAccount;
import com.mcoding.comp.sms.utils.PropertiesUtils;

public class ChuanglanAccount implements SupplierAccount {
	
	private String serverHost;
	private Integer serverPort;
	private String serverUrl;
	private String account;
	private String pswd;
	
	public ChuanglanAccount(String account, String pswd) {
		super();
		this.account = account;
		this.pswd = pswd;
		this.serverHost = PropertiesUtils.getProperty("sms.chuanglan.server.host");
		this.serverPort = Integer.valueOf(PropertiesUtils.getProperty("sms.chuanglan.server.port"));
		this.serverUrl = PropertiesUtils.getProperty("sms.chuanglan.server.url");
	}

	public ChuanglanAccount(String serverHost, Integer serverPort, String serverUrl, String account, String pswd) {
		super();
		this.serverHost = serverHost;
		this.serverPort = serverPort;
		this.serverUrl = serverUrl;
		this.account = account;
		this.pswd = pswd;
	}

	@Override
	public SmsRequest createRequest() {
		return new ChuangLanRequest(this);
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ChuanglanAccount)){
			return false;
		}
		
		ChuanglanAccount tmp = (ChuanglanAccount) obj;
		
		return tmp.getAccount() != null && tmp.getAccount().equals(this.getAccount()) && 
				tmp.getPswd() != null && tmp.getPswd().equals(this.getPswd()) &&
				tmp.getServerHost() != null && tmp.getServerHost().equals(this.getServerHost()) &&
				tmp.getServerPort() != null && tmp.getServerPort().equals(this.getServerPort());
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public Integer getServerPort() {
		return serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	
}
