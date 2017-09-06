package com.mcoding.comp.wechat.pay.service;

import com.mcoding.comp.wechat.pay.bean.WxMpXmlInOrderQueryMessage;

/**
 * 支付成功的处理器
 * @author hzy
 *
 */
public interface WechatPayNotifyService {
	
	/**
	 * 支付成功后，回调接口
	 * @param inMsg
	 */
	public void handlePaidNotifyMsg(WxMpXmlInOrderQueryMessage inMsg);

}
