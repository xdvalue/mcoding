package com.mcoding.comp.wechat.msg.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mcoding.base.ui.utils.spring.SpringContextHolder;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;
import com.mcoding.comp.wechat.msg.bean.WxTemplateMessage;
import com.mcoding.comp.wechat.msg.service.WxTemplateMessageService;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

public class WxMpTemplateMsgUtils {

	private static Logger logger = LoggerFactory.getLogger(WxMpTemplateMsgUtils.class);
	private static WxTemplateMessageService wxTemplateMessageService = SpringContextHolder.getOneBean(WxTemplateMessageService.class);
	
	/**
	 * 发送模板消息，模板消息具体内容自定义
	 * @param account
	 * @param templateMessage
	 * @return
	 */
	public static boolean sendWxMpTemplateMessage(AccountConfig account, WxMpTemplateMessage templateMessage) {

		WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(account);
		String result = null;
		boolean isSuccess = true;
		try {
			result = wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
		} catch (Exception e) {
			logger.info("===== error in sending templateMessage =====" + result);
			e.printStackTrace();
			isSuccess = false;
		}
		logger.info("===== after sending templateMessage =====" + result);
		return isSuccess;
	}

	/**
	 * 发送模板消息，templateId，first，remark根据数据库配置生成，其余自定义
	 * @param account
	 * @param templateType
	 * @param receiverOpenid
	 * @param url
	 * @param keyword1
	 * @param keyword2
	 * @param keyword3
	 * @param keyword4
	 * @param keyword5
	 * @return
	 * @throws Exception
	 */
	public static boolean sendWxMpTemplateMessage(AccountConfig account, String templateType, String receiverOpenid,
			String url, String keyword1, String keyword2, String keyword3, String keyword4, String keyword5)
					throws Exception {
		return sendWxMpTemplateMessage(account, templateType, receiverOpenid, null, null, url, null, keyword1, keyword2, keyword3, keyword4, keyword5);
	}

	/**
	 * 发送模板消息,templateId根据数据库配置，其余字段自定义
	 * @param account
	 * @param templateType
	 * @param receiverOpenid
	 * @param first
	 * @param remark
	 * @param url
	 * @param color
	 * @param keyword1
	 * @param keyword2
	 * @param keyword3
	 * @param keyword4
	 * @param keyword5
	 * @return
	 * @throws Exception
	 */
	public static boolean sendWxMpTemplateMessage(AccountConfig account, String templateType, String receiverOpenid,
			String first, String remark, String url, String color, String keyword1, String keyword2, String keyword3,
			String keyword4, String keyword5) throws Exception {
				
//		WxTemplateMessage tplMsg = getService().queryByAccountAndType(account.getId(), templateType);
		WxTemplateMessage tplMsg = wxTemplateMessageService.queryByAccountAndType(account.getId(), templateType);
		if (StringUtils.isBlank(first)) {
			first = tplMsg.getFirst();
		}
		if (StringUtils.isBlank(remark)) {
			remark = tplMsg.getRemark();
		}
		if (StringUtils.isBlank(color)) {
			color = tplMsg.getColor();
		}
		
		// 推送用户支付成功的模板消息
		WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
		templateMessage.setToUser(receiverOpenid);
		templateMessage.setTemplateId(tplMsg.getTemplateId());
		templateMessage.setUrl(url);
//		templateMessage.setTopColor("#FF0000");
		templateMessage.getData().add(new WxMpTemplateData("first", first, color));
		if (StringUtils.isNotBlank(keyword1)) {
			templateMessage.getData().add(new WxMpTemplateData("keyword1", keyword1, "#000000"));
		}
		if (StringUtils.isNotBlank(keyword2)) {
			templateMessage.getData().add(new WxMpTemplateData("keyword2", keyword2, "#000000"));
		}
		if (StringUtils.isNotBlank(keyword3)) {
			templateMessage.getData().add(new WxMpTemplateData("keyword3", keyword3, "#000000"));
		}
		if (StringUtils.isNotBlank(keyword4)) {
			templateMessage.getData().add(new WxMpTemplateData("keyword4", keyword4, "#000000"));
		}
		if (StringUtils.isNotBlank(keyword5)) {
			templateMessage.getData().add(new WxMpTemplateData("keyword5", keyword5, "#000000"));
		}
		templateMessage.getData().add(new WxMpTemplateData("remark", remark, color));

		return sendWxMpTemplateMessage(account, templateMessage);
	}

	/*private static WxTemplateMessageService getService() {
		WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
		Map<String, WxTemplateMessageService> beanMaps = applicationContext
				.getBeansOfType(WxTemplateMessageService.class);

		return beanMaps.values().iterator().next();
	}*/

}
