package com.mcoding.comp.wechat.qrcode.service;

import java.io.IOException;

import com.mcoding.base.core.BaseService;
import com.mcoding.comp.wechat.qrcode.bean.WxQrcode;
import com.mcoding.comp.wechat.qrcode.bean.WxQrcodeExample;

import me.chanjar.weixin.common.exception.WxErrorException;

public interface WxQrcodeService extends BaseService<WxQrcode, WxQrcodeExample> {
	
	/**
	 * 创建公众号的临时二维码
	 * @param sceneId 场景id 临时二维码时为32位非0整型
	 * @param accountOriginId 公众号原始id
	 * @param name
	 * @param expiredSeconds 二维码有效时间，以秒为单位。 最大不超过2592000
	 * @return
	 * @throws WxErrorException
	 * @throws IOException
	 */
//	public WxQrcode createLimitQrcode(int sceneId, String accountOriginId, String name, Integer expiredSeconds) throws WxErrorException, IOException;
	public WxQrcode createLimitQrcode(WxQrcode qrcode) throws WxErrorException, IOException;
	
	/**
	 * 创建永久二维码
	 * @param sceneStr 场景值ID，长度限制为1到64
 	 * @param accountOriginId
	 * @param name
	 * @return
	 * @throws WxErrorException
	 * @throws IOException
	 */
//	public WxQrcode createUnLimitQrcode(String sceneStr, String accountOriginId, String name) throws WxErrorException, IOException;

	public WxQrcode createUnLimitQrcode(WxQrcode wxQrcode) throws WxErrorException, IOException;
	
	/**
	 * 根据key查询出二维码
	 * @param key
	 * @return
	 */
	public WxQrcode queryByKey(String originId, String key);
	
	/**
	 * 增加二维码的扫描数
	 * @param id
	 */
	public void addScanQrcode(int id);
}