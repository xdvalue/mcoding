package com.mcoding.comp.wechat.msg;

import com.mcoding.comp.wechat.msg.bean.WxMsgRule;

import me.chanjar.weixin.mp.api.WxMpMessageMatcher;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;

public class WxMpMessageSendTimeMatcher implements WxMpMessageMatcher {
	
	@Override
	public boolean match(WxMpXmlMessage message) {
		
		boolean greatThanStart = wxMsgRule.getMsgStartTime()==null ? true : wxMsgRule.getMsgStartTime().getTime() < message.getCreateTime();
		boolean lessThanStart = wxMsgRule.getMsgEndTime()==null ? true : wxMsgRule.getMsgEndTime().getTime() > message.getCreateTime();
		
		return greatThanStart && lessThanStart;
	}
	
	private WxMsgRule wxMsgRule;

	public WxMpMessageSendTimeMatcher(WxMsgRule wxMsgRule) {
		super();
		this.wxMsgRule = wxMsgRule;
	}

	public WxMsgRule getWxMsgRule() {
		return wxMsgRule;
	}

	public void setWxMsgRule(WxMsgRule wxMsgRule) {
		this.wxMsgRule = wxMsgRule;
	}
	
	

}
