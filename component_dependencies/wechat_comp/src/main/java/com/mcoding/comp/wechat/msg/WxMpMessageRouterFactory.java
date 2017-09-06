package com.mcoding.comp.wechat.msg;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.Constant;
import com.mcoding.comp.wechat.common.McodingMessageRouter;
import com.mcoding.comp.wechat.common.McodingMessageRouterRule;
import com.mcoding.comp.wechat.msg.bean.WxMsgRule;
import com.mcoding.comp.wechat.msg.handler.AutoReplyHandler;
import com.mcoding.comp.wechat.msg.handler.BaseMsgHandler;
import com.mcoding.comp.wechat.msg.handler.DefaultSubscribeHandler;
import com.mcoding.comp.wechat.msg.handler.DefaultUnsubscribeHandler;
import com.mcoding.comp.wechat.msg.handler.ReplyTextHandler;
import com.mcoding.comp.wechat.msg.handler.ScanQrcodeHandler;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;

/**
 * 消息路由的工厂类，根据消息规则创建响应的消息路由
 * @author hzy
 *
 */
public class WxMpMessageRouterFactory {
	
	private static Logger logger = LoggerFactory.getLogger(WxMpMessageRouterFactory.class);
	
	public static final String handler_separator = "\\s*;\\s*";
	
	private static WxMsgRuleComparator wxMsgRuleComparator = new WxMsgRuleComparator();
	private static List<WxMsgRule> defaultMsgRuleList;
	
	private WxMpMessageRouterFactory(){
		super();
	}
	
	public static McodingMessageRouter build(List<WxMsgRule> ruleList, WxMpService wxMpService) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		McodingMessageRouter router = new McodingMessageRouter(wxMpService);
		
		//没有配置就返回默认处理
		if (CollectionUtils.isEmpty(ruleList)) {
			ruleList = new ArrayList<>();
		}
		CollectionUtils.addAll(ruleList, getDefaultRuleList().iterator());
		
		Collections.sort(ruleList, wxMsgRuleComparator);
		
		for(int i=0; i<ruleList.size(); i++){
			logger.debug("start setting rule:" + ruleList.get(i).getName());
			if (i < ruleList.size()-1 ) {
				setRule(router, ruleList.get(i)).next();
			}else{
				setRule(router, ruleList.get(i)).end();
			}
			logger.debug("finish setting rule:" + ruleList.get(i).getName());
		}
		return router;
	}
	
	@SuppressWarnings("unchecked")
	private static McodingMessageRouterRule setRule(WxMpMessageRouter router, WxMsgRule wxMsgRule) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
//	    WxMpMessageRouterRule routerRule = router.rule();
		McodingMessageRouterRule routerRule = new McodingMessageRouterRule(router);
		if (StringUtils.isNotBlank(wxMsgRule.getFromUserName())) {
			routerRule.fromUser(wxMsgRule.getFromUserName());
		}
		
		if (StringUtils.isNotBlank(wxMsgRule.getMsgType())) {
			routerRule.msgType(wxMsgRule.getMsgType());
		}
		
		if (StringUtils.isNotBlank(wxMsgRule.getContent())) {
			if (wxMsgRule.getMatchType()!= null && wxMsgRule.getMatchType().equals(WxMsgRule.MATCH_TYPE_REGEX)) {
				routerRule.matcher(new McodingMsgMutilRegexMatcher(wxMsgRule.getContent()));
				
			}else{
				routerRule.matcher(new McodingMsgMutilMatcher(wxMsgRule.getContent()));
			}
		}
		
		if (StringUtils.isNotBlank(wxMsgRule.getEvent())) {
			routerRule.event(wxMsgRule.getEvent());
		}
		
		if (StringUtils.isNotBlank(wxMsgRule.getEventKey())) {
			if (wxMsgRule.getMatchType()!= null && wxMsgRule.getMatchType().equals(WxMsgRule.MATCH_TYPE_REGEX)) {
				routerRule.matcher(new McodingMsgMutilRegexMatcher(wxMsgRule.getEventKey()));
				
			}else{
				routerRule.matcher(new McodingMsgMutilMatcher(wxMsgRule.getEventKey()));
			}
		}
		
		if (wxMsgRule.getMsgStartTime() != null || wxMsgRule.getMsgEndTime() !=null) {
			routerRule.matcher(new WxMpMessageSendTimeMatcher(wxMsgRule));
		}
		
		if (Constant.YES_INT.equals(wxMsgRule.getIsSycn())) {
			routerRule.async(true);
		}else{
			routerRule.async(false);
		}

		if (StringUtils.isBlank(wxMsgRule.getHandlers()) && StringUtils.isBlank(wxMsgRule.getReplyContent())) {
			//如果既没有回复的文本，也没有配置处理器
//			routerRule.handler(new DefaultMsgHandler(wxMsgRule));
			throw new CommonException("该规则无效，因为没有配置文本，也没有配置处理器");
		}
		
		if (StringUtils.isNotBlank(wxMsgRule.getReplyContent()) && StringUtils.isBlank(wxMsgRule.getHandlers())) {
			//如果没有配置处理，但是配置回复的文本，设置默认的的文本处理器
			routerRule.handler(new ReplyTextHandler(wxMsgRule));
			
		}else{
			
			//设置配置的处理器
			String[] handlers = wxMsgRule.getHandlers().split(handler_separator);
			for(int i=0; i<handlers.length; i++){
				Class<?> handlerClass = Class.forName(handlers[i]);
				if (!BaseMsgHandler.class.isAssignableFrom(handlerClass)) {
					throw new CommonException("该规则无效，因为因为配置的处理，没有继承BaseMsgHandler");
				}
				
				Constructor<BaseMsgHandler> constructor = (Constructor<BaseMsgHandler>) handlerClass.getConstructor(WxMsgRule.class);
				BaseMsgHandler handler = constructor.newInstance(wxMsgRule);
				
				routerRule.handler(handler);
			}
			
		}
		
//		routerRule.end();
//		routerRule.next();
		return routerRule;
	}
	
	public static List<WxMsgRule> getDefaultRuleList(){
		if (defaultMsgRuleList != null) {
			return defaultMsgRuleList;
		}
		
		defaultMsgRuleList = new ArrayList<>();
		
//		WxMsgRule defaultTextMsgRule = new WxMsgRule();
//		defaultTextMsgRule.setName("defaultTextMsgRule");
//		defaultTextMsgRule.setMsgType(WxConsts.XML_MSG_TEXT);
//		defaultTextMsgRule.setPriority(-1);
//		defaultTextMsgRule.setHandlers(ReplyTextHandler.class.getName());
//		defaultMsgRuleList.add(defaultTextMsgRule);
		
		WxMsgRule defaultSubscribeMsgRule = new WxMsgRule();
		defaultSubscribeMsgRule.setName("defaultSubcribeMsgRule");
		defaultSubscribeMsgRule.setMsgType(WxConsts.XML_MSG_EVENT);
		defaultSubscribeMsgRule.setEvent(WxConsts.EVT_SUBSCRIBE);
		defaultSubscribeMsgRule.setIsSycn(Constant.YES_INT);
		defaultSubscribeMsgRule.setPriority(10);
		defaultSubscribeMsgRule.setHandlers(DefaultSubscribeHandler.class.getName());
		defaultMsgRuleList.add(defaultSubscribeMsgRule);
		
		WxMsgRule defaultUnsubscribeMsgRule = new WxMsgRule();
		defaultUnsubscribeMsgRule.setName("defaultUnsubcribeMsgRule");
		defaultUnsubscribeMsgRule.setMsgType(WxConsts.XML_MSG_EVENT);
		defaultUnsubscribeMsgRule.setEvent(WxConsts.EVT_UNSUBSCRIBE);
		defaultUnsubscribeMsgRule.setIsSycn(Constant.YES_INT);
		defaultUnsubscribeMsgRule.setPriority(10);
		defaultUnsubscribeMsgRule.setHandlers(DefaultUnsubscribeHandler.class.getName());
		defaultMsgRuleList.add(defaultUnsubscribeMsgRule);
		
		WxMsgRule defaultScanQrcodeMsgRule = new WxMsgRule();
		defaultScanQrcodeMsgRule.setName("defaultScanQrcodeMsgRule");
		defaultScanQrcodeMsgRule.setMsgType(WxConsts.XML_MSG_EVENT);
		defaultScanQrcodeMsgRule.setIsSycn(Constant.YES_INT);
		defaultScanQrcodeMsgRule.setPriority(10);
		defaultScanQrcodeMsgRule.setHandlers(ScanQrcodeHandler.class.getName());
		defaultMsgRuleList.add(defaultScanQrcodeMsgRule);
		
		WxMsgRule defaultTextMsgRule = new WxMsgRule();
		defaultTextMsgRule.setName("defaultTextMsgRule");
		defaultTextMsgRule.setPriority(-1);
		defaultTextMsgRule.setHandlers(AutoReplyHandler.class.getName());
		defaultMsgRuleList.add(defaultTextMsgRule);
		
		return defaultMsgRuleList;
	}
	
	/**
	 * 消息处理规则 的排序规则
	 * @author hzy
	 *
	 */
	protected static class WxMsgRuleComparator implements Comparator<WxMsgRule>{

		@Override
		public int compare(WxMsgRule o1, WxMsgRule o2) {
			if (Constant.YES_INT.equals(o1.getIsSycn()) && !Constant.YES_INT.equals(o2.getIsSycn())) {
				return -1;
				
			}else if (!Constant.YES_INT.equals(o1.getIsSycn()) && Constant.YES_INT.equals(o2.getIsSycn())) {
				return 1;
			}
			
			if (o1.getPriority() == null) {
				o1.setPriority(0);
			}
			
			if (o2.getPriority() == null) {
				o2.setPriority(0);
			}
			
			return o1.getPriority() > o2.getPriority() ? -1 : 1;
		}
	}
	
//	
//	public static void main(String[] args) throws ClassNotFoundException {
//		System.out.println("className:" + DefaultMsgHandler.class.getName());
//		
//	}

}
