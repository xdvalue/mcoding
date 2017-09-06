package com.mcoding.comp.wechat.common;

import java.io.Serializable;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

public class McodingMessageRouter extends WxMpMessageRouter implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final McodingMsgDeuplicateChecker messageDuplicateChecker = new McodingMsgDeuplicateChecker();

	public McodingMessageRouter(WxMpService wxMpService) {
		super(wxMpService);
	}

	@Override
	protected boolean isDuplicateMessage(WxMpXmlMessage wxMessage) {
		boolean isDuplicate = messageDuplicateChecker.isDuplicate(this.getMessageId(wxMessage));
		if (!isDuplicate) {
			messageDuplicateChecker.setMsgStatus(getMessageId(wxMessage), McodingMsgDeuplicateChecker.MSG_STATUS_PROCESS);
		}
		
		return isDuplicate;
	}
	
	protected String getMessageId(WxMpXmlMessage wxMessage){
		String messageId = "";
	    if (wxMessage.getMsgId() == null) {
	      messageId = String.valueOf(wxMessage.getCreateTime())
	          + "-" + wxMessage.getFromUser()
	          + "-" + String.valueOf(wxMessage.getEventKey() == null ? "" : wxMessage.getEventKey())
	          + "-" + String.valueOf(wxMessage.getEvent() == null ? "" : wxMessage.getEvent())
	      ;
	    } else {
	      messageId = String.valueOf(wxMessage.getMsgId());
	    }

	    return messageId;
	}

	@Override
	public WxMpXmlOutMessage route(WxMpXmlMessage wxMessage) {
		WxMpXmlOutMessage outMsg = null;
		try {
			outMsg = super.route(wxMessage);
		
		} catch (Exception e) {
			e.printStackTrace();
			messageDuplicateChecker.setMsgStatus(this.getMessageId(wxMessage), McodingMsgDeuplicateChecker.MSG_STATUS_ERROR);
			throw e;
		}
		
		messageDuplicateChecker.setMsgStatus(this.getMessageId(wxMessage), McodingMsgDeuplicateChecker.MSG_STATUS_FINISH);
		return outMsg;
	}
	
	
}
