package com.mcoding.comp.wechat.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import me.chanjar.weixin.mp.api.WxMpMessageMatcher;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpMessageRouterRule;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;

public class McodingMessageRouterRule extends WxMpMessageRouterRule {

	public McodingMessageRouterRule(WxMpMessageRouter routerBuilder) {
		super(routerBuilder);
	}
	
	private List<WxMpMessageMatcher> matchers;
	
	@Override
	public WxMpMessageRouterRule matcher(WxMpMessageMatcher matcher) {
		if (this.matchers == null) {
			this.matchers = new ArrayList<>();
		}
		
		this.matchers.add(matcher);
	    return this;
	}

	@Override
	protected boolean test(WxMpXmlMessage wxMessage) {
		boolean isMatch = 
				(this.getFromUser() == null || this.getFromUser().equals(wxMessage.getFromUser()))
	            &&
	            (this.getMsgType() == null || this.getMsgType().toLowerCase().equals((wxMessage.getMsgType()==null?null:wxMessage.getMsgType().toLowerCase())))
	            &&
	            (this.getEvent() == null || this.getEvent().toLowerCase().equals((wxMessage.getEvent()==null?null:wxMessage.getEvent().toLowerCase())))
	            &&
	            (this.getEventKey() == null || this.getEventKey().toLowerCase().equals((wxMessage.getEventKey()==null?null:wxMessage.getEventKey().toLowerCase())))
	            &&
	            (this.getContent() == null || this.getContent().equals(wxMessage.getContent() == null ? null : wxMessage.getContent().trim()));
		
		if (!isMatch) {
			return isMatch;
		}
		
		if(StringUtils.isNotBlank(this.getrContent())){
			Pattern pattern = Pattern.compile(this.getrContent());
			isMatch = isMatch && pattern.matcher(wxMessage.getContent()).find();
		}
		
		if (CollectionUtils.isEmpty(this.matchers)) {
			return isMatch;
		}
		
		for (int i = 0; isMatch && i < this.matchers.size(); i++) {
			isMatch = isMatch && this.matchers.get(i).match(wxMessage);
		}
		
		return isMatch;
	}

}
