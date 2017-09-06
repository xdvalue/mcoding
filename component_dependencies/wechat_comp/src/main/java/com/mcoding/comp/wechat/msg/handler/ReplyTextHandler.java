package com.mcoding.comp.wechat.msg.handler;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.mcoding.comp.wechat.msg.bean.WxMsgRule;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;

public class ReplyTextHandler extends BaseMsgHandler {
	
	private String replyContent = "欢迎!! ^_^";
	
    public ReplyTextHandler(WxMsgRule wxMsgRule) {
    	super(wxMsgRule);
    	
    	if (wxMsgRule!=null && 
    			StringUtils.isNotBlank(wxMsgRule.getReplyContent())) {
    		
    		this.replyContent = wxMsgRule.getReplyContent();
		}
	}

	@Override
	public WxMpXmlOutMessage handle(
			WxMpXmlMessage inMessage,
			Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		
		WxMpXmlOutTextMessage outMessage = new WxMpXmlOutTextMessage();
		outMessage.setCreateTime(new Date().getTime());
		outMessage.setFromUserName(inMessage.getToUser());
		outMessage.setToUserName(inMessage.getFromUser());
		
		outMessage.setContent(this.replyContent);
		return outMessage;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	
	

}
