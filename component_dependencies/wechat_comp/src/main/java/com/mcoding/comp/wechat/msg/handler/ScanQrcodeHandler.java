package com.mcoding.comp.wechat.msg.handler;

import java.util.Map;

import com.mcoding.base.ui.utils.spring.SpringContextHolder;
import com.mcoding.comp.wechat.msg.bean.WxMsgRule;
import com.mcoding.comp.wechat.qrcode.bean.WxQrcode;
import com.mcoding.comp.wechat.qrcode.service.WxQrcodeService;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

public class ScanQrcodeHandler extends BaseMsgHandler {

	public ScanQrcodeHandler(WxMsgRule wxMsgRule) {
		super(wxMsgRule);
		// TODO Auto-generated constructor stub
	}

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		if (!WxConsts.XML_MSG_EVENT.equals(wxMessage.getMsgType())) {
			return null;
		}
		
		if (!WxConsts.EVT_SCAN.equals(wxMessage.getEvent()) && !WxConsts.EVT_SUBSCRIBE.equals(wxMessage.getEvent())) {
			return null;
		}
		
		String key = wxMessage.getEventKey().replaceAll("qrscene_", "");
		WxQrcodeService wxQrcodeService = SpringContextHolder.getOneBean(WxQrcodeService.class);
		WxQrcode wxQrcode = wxQrcodeService.queryByKey(wxMessage.getToUser(), key);
		if (wxQrcode != null) {
			wxQrcodeService.addScanQrcode(wxQrcode.getId());
		}
		return null;
	}

}
