package com.mcoding.comp.wechat.msg.service;

import com.mcoding.base.core.BaseService;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.common.McodingMessageRouter;
import com.mcoding.comp.wechat.msg.bean.WxMsgRule;
import com.mcoding.comp.wechat.msg.bean.WxMsgRuleExample;

public interface WxMsgRuleService extends BaseService<WxMsgRule, WxMsgRuleExample> {
	
	public McodingMessageRouter createRouter(AccountConfig account);
}