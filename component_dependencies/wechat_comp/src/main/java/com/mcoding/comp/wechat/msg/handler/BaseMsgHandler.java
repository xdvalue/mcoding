package com.mcoding.comp.wechat.msg.handler;

import java.util.Map;

import com.mcoding.comp.wechat.msg.bean.WxMsgRule;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

public abstract class BaseMsgHandler implements WxMpMessageHandler {

	@Override
	public abstract WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException;

	private WxMsgRule wxMsgRule;

	public BaseMsgHandler(WxMsgRule wxMsgRule) {
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
