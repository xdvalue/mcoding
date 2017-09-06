package com.mcoding.comp.wechat.msg;

import java.util.regex.Pattern;

import me.chanjar.weixin.mp.api.WxMpMessageMatcher;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;

public class WxMpMessageREventKeyMatcher implements WxMpMessageMatcher {
	
	private String rEventKey;

	public WxMpMessageREventKeyMatcher(String eventKey) {
		this.rEventKey = eventKey;
	}

	@Override
	public boolean match(WxMpXmlMessage message) {
		return Pattern.matches(this.rEventKey, message.getEventKey() == null ? "" : message.getEventKey());
	}

	public String getrEventKey() {
		return rEventKey;
	}

	public void setrEventKey(String rEventKey) {
		this.rEventKey = rEventKey;
	}
	
	

}
