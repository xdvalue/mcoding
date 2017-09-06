package com.mcoding.comp.wechat.msg.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;
import com.mcoding.comp.wechat.msg.bean.WxMsgReplyNews;
import com.mcoding.comp.wechat.msg.bean.WxMsgRule;
import com.mcoding.comp.wechat.msg.service.WxMsgReplyNewsService;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage.Item;

public class ReplyNewsHandler extends BaseMsgHandler{
	
	private List<Item> articleList ;

	public ReplyNewsHandler(WxMsgRule wxMsgRule) throws JsonParseException, JsonMappingException, IOException {
		super(wxMsgRule);
		
		String content = wxMsgRule.getReplyContent();
		if (StringUtils.isBlank(content)) {
			return;
		}
		articleList = new ArrayList<>();
		String[] ids = content.split("\\s*,\\s*");
		
		for(int i=0; i<ids.length; i++){
			WxMsgReplyNews msgNews = SpringContextHolder.getOneBean(WxMsgReplyNewsService.class).queryObjById(Integer.valueOf(ids[i]));
			Item item = new Item();
			item.setTitle(msgNews.getTitle());
			item.setPicUrl(msgNews.getCoverImg());
			item.setDescription(msgNews.getSummary());
			item.setUrl(msgNews.getUrl());
			articleList.add(item);
		}
		
//		JavaType listClass = JsonUtilsForMcoding.getCollectionType(List.class, ArrayList.class, Item.class); 
//		articleList = JsonUtilsForMcoding.convertCollection(content, List.class, ArrayList.class, Item.class);
		
		
	}

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		
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

}
