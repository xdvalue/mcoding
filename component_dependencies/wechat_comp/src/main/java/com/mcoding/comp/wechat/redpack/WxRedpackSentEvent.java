package com.mcoding.comp.wechat.redpack;

import org.springframework.context.ApplicationEvent;

import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.redpack.bean.WxRedpack;

import me.chanjar.weixin.mp.bean.pay.request.WxPaySendRedpackRequest;
import me.chanjar.weixin.mp.bean.pay.result.WxPaySendRedpackResult;

public class WxRedpackSentEvent extends ApplicationEvent {

	private WxPaySendRedpackResult inMessage;
	private WxPaySendRedpackRequest outMessage;
	private WxRedpack wxRedpack;
	private AccountConfig accountConfig;

	public WxRedpackSentEvent(String event) {
		super(event);
	}

	public WxPaySendRedpackResult getInMessage() {
		return inMessage;
	}

	public void setInMessage(WxPaySendRedpackResult inMessage) {
		this.inMessage = inMessage;
	}

	public WxPaySendRedpackRequest getOutMessage() {
		return outMessage;
	}

	public void setOutMessage(WxPaySendRedpackRequest outMessage) {
		this.outMessage = outMessage;
	}

	public WxRedpack getWxRedpack() {
		return wxRedpack;
	}

	public void setWxRedpack(WxRedpack wxRedpack) {
		this.wxRedpack = wxRedpack;
	}

	public AccountConfig getAccountConfig() {
		return accountConfig;
	}

	public void setAccountConfig(AccountConfig accountConfig) {
		this.accountConfig = accountConfig;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
