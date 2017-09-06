package com.mcoding.comp.wechat.msg.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mcoding.base.core.Constant;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;
import com.mcoding.comp.wechat.msg.bean.WxMsgAutoReply;
import com.mcoding.comp.wechat.msg.bean.WxMsgAutoReplyExample;
import com.mcoding.comp.wechat.msg.bean.WxMsgReplyNews;
import com.mcoding.comp.wechat.msg.bean.WxMsgRule;
import com.mcoding.comp.wechat.msg.service.WxMsgAutoReplyService;
import com.mcoding.comp.wechat.msg.service.WxMsgReplyNewsService;
import com.mcoding.comp.wechat.qrcode.bean.WxQrcode;
import com.mcoding.comp.wechat.qrcode.service.WxQrcodeService;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage.Item;

public class AutoReplyHandler extends BaseMsgHandler{
	
	private static Logger logger = LoggerFactory.getLogger(AutoReplyHandler.class);

	public AutoReplyHandler(WxMsgRule wxMsgRule) {
		super(wxMsgRule);
		// TODO Auto-generated constructor stub
	}

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		
		String keywords = "";  //关键字
		if (WxConsts.XML_MSG_TEXT.equals(wxMessage.getMsgType())) {
			//如果是文本消息
			keywords = wxMessage.getContent();
		
		}else if (WxConsts.XML_MSG_EVENT.equals(wxMessage.getMsgType())) {
			
			if(WxConsts.EVT_SUBSCRIBE.equals(wxMessage.getEvent()) || WxConsts.EVT_SCAN.equals(wxMessage.getEvent())){
				//如果是扫码或者关注
				String qrcodeKey = wxMessage.getEventKey().replaceAll("qrscene_", "");
				WxQrcode qrcode = SpringContextHolder.getOneBean(WxQrcodeService.class).queryByKey(wxMessage.getToUser(), qrcodeKey);
				if (qrcode != null) keywords = qrcode.getReplyContent(); 
			}
			
			if(WxConsts.EVT_CLICK.equals(wxMessage.getEvent())){
				keywords = wxMessage.getEventKey();
			}
		}
		
		if (StringUtils.isBlank(keywords)) {
			//如果内容是空，就返回默认的消息
			return this.reply(this.getDefaultAutoReply(wxMessage.getToUser()), wxMessage);
		}
		
		List<WxMsgAutoReply> list = this.getAutoReplyList(wxMessage.getToUser());
		for(int i=0; CollectionUtils.isNotEmpty(list) && i<list.size() ; i++){
			if (!this.isMatch(list.get(i), keywords)) {
				continue;
			}
			return this.reply(list.get(i), wxMessage);
		}
		
		return this.reply(this.getDefaultAutoReply(wxMessage.getToUser()), wxMessage);
	}
	
	private boolean isMatch(WxMsgAutoReply autoReply, String content){
		String[] keywordsArray = autoReply.getKeywords().split("\\s*,\\s*");
		for(int i=0; i< keywordsArray.length; i++){
			if (WxMsgRule.MATCH_TYPE_REGEX.toString().equals(autoReply.getMatchType())) {
				if (content.contains(keywordsArray[i])) {
					return true;
				}
			}else{
				if (content.trim().equals(keywordsArray[i])) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private WxMpXmlOutMessage reply(WxMsgAutoReply autoReply, WxMpXmlMessage wxMessage){
		if (autoReply == null) {
			return this.replyBlank(wxMessage);
		}
		
		if (WxMsgAutoReply.REPLY_TYPE_NEWS.equals(autoReply.getReplyType())) {
			return this.replyNews(autoReply.getReplyContent(), wxMessage);
		}else{
			return this.replyText(autoReply.getReplyContent(), wxMessage);
		}
	}
	
	private WxMpXmlOutMessage replyBlank(WxMpXmlMessage wxMessage){
		logger.warn("auto reply, reply blank msg, msg from user: " + wxMessage );
		
		WxMpXmlOutTextMessage outMessage = new WxMpXmlOutTextMessage();
		outMessage.setCreateTime(new Date().getTime());
		outMessage.setFromUserName(wxMessage.getToUser());
		outMessage.setToUserName(wxMessage.getFromUser());
		return outMessage;
	}
	
	private WxMpXmlOutMessage replyText(String replyContent, WxMpXmlMessage wxMessage) {
		WxMpXmlOutTextMessage outMessage = new WxMpXmlOutTextMessage();
		outMessage.setCreateTime(new Date().getTime());
		outMessage.setFromUserName(wxMessage.getToUser());
		outMessage.setToUserName(wxMessage.getFromUser());
		
		outMessage.setContent(replyContent);
		return outMessage;
	}

	private WxMpXmlOutMessage replyNews(String replyContent, WxMpXmlMessage wxMessage){
        String[] ids = replyContent.split("\\s*,\\s*");
		
        List<Item> articleList = new ArrayList<>();
		for(int i=0; i<ids.length; i++){
			WxMsgReplyNews msgNews = SpringContextHolder.getOneBean(WxMsgReplyNewsService.class).queryObjById(Integer.valueOf(ids[i]));
			if (msgNews == null) {
				continue;
			}
			Item item = new Item();
			item.setTitle(msgNews.getTitle());
			item.setPicUrl(msgNews.getCoverImg());
			item.setDescription(msgNews.getSummary());
			item.setUrl(msgNews.getUrl());
			articleList.add(item);
		}
		
		WxMpXmlOutNewsMessage newsMsg = new WxMpXmlOutNewsMessage();
		newsMsg.setFromUserName(wxMessage.getToUser());
		newsMsg.setToUserName(wxMessage.getFromUser());
		newsMsg.setMsgType(WxConsts.XML_MSG_NEWS);
		newsMsg.setCreateTime(new Date().getTime());
		
		for (int i = 0; CollectionUtils.isNotEmpty(articleList) && i < articleList.size(); i++) {
			newsMsg.addArticle(articleList.get(i));
		}
		
		return newsMsg;
	}
	
	private List<WxMsgAutoReply> getAutoReplyList(String originId){
		WxMsgAutoReplyExample example = new WxMsgAutoReplyExample();
		example.createCriteria()
		       .andWxAccountOriginIdEqualTo(originId);
		example.setOrderByClause("priority DESC");
		List<WxMsgAutoReply> list = SpringContextHolder.getOneBean(WxMsgAutoReplyService.class).queryAllObjByExample(example);
		return list;
	}
	
	private WxMsgAutoReply getDefaultAutoReply(String originId){
		WxMsgAutoReplyExample example = new WxMsgAutoReplyExample();
		example.createCriteria()
		       .andWxAccountOriginIdEqualTo(originId)
		       .andIsDefaultEqualTo(Constant.YES_INT);
		
		example.setOrderByClause("priority DESC");
		List<WxMsgAutoReply> list = SpringContextHolder.getOneBean(WxMsgAutoReplyService.class).queryAllObjByExample(example);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

}
