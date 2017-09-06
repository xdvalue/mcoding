package com.mcoding.comp.wechat.msg.service;

import com.mcoding.base.core.BaseService;
import com.mcoding.comp.wechat.msg.bean.WxTemplateMessage;
import com.mcoding.comp.wechat.msg.bean.WxTemplateMessageExample;

public interface WxTemplateMessageService extends BaseService<WxTemplateMessage, WxTemplateMessageExample> {
	
	public WxTemplateMessage queryByAccountAndType(int accountId, String type);
}