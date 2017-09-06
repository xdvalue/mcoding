package com.mcoding.comp.wechat.jssdk.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * H5支付中，js的参数
 * 
 * @author hzy
 *
 */
public class WxJsPayParams {

	private String appId;
	
	/**
	 * 当前的时间,单位 秒
	 */
	private String timeStamp;
	private String nonceStr;
	
	@JsonProperty("package")
	private String packageStr;
	private String signType;
	private String paySign;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPackageStr() {
		return packageStr;
	}

	public void setPackageStr(String packageStr) {
		this.packageStr = packageStr;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

}
