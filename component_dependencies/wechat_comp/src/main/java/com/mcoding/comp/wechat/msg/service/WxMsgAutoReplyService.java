package com.mcoding.comp.wechat.msg.service;

import com.mcoding.base.core.BaseService;
import com.mcoding.comp.wechat.msg.bean.WxMsgAutoReply;
import com.mcoding.comp.wechat.msg.bean.WxMsgAutoReplyExample;

public interface WxMsgAutoReplyService extends BaseService<WxMsgAutoReply, WxMsgAutoReplyExample> {
	
	/**
	 * 设置默认回复的内容
	 * @param originId
	 * @param id
	 */
	public void setDefalutById(String originId, int id);
}