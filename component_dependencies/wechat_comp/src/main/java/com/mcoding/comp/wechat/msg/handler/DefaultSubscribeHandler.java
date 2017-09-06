package com.mcoding.comp.wechat.msg.handler;

import java.util.Map;

import com.mcoding.base.ui.utils.spring.SpringContextHolder;
import com.mcoding.comp.wechat.member.service.WxMemberService;
import com.mcoding.comp.wechat.msg.bean.WxMsgRule;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

public class DefaultSubscribeHandler extends BaseMsgHandler {

	protected static WxMemberService wxMemberService = SpringContextHolder.getBean("wxMemberService");

	public DefaultSubscribeHandler(WxMsgRule wxMsgRule) {
		super(wxMsgRule);
	}

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {

		WxMpUser wxMpUser = wxMpService.getUserService().userInfo(wxMessage.getFromUser(), null);
		wxMemberService.createOrEditWxMember(wxMpUser, wxMessage.getEventKey(), wxMessage.getToUser());
		return null;
	}

}
