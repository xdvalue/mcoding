package com.mcoding.comp.wechat.common;

import java.io.Serializable;

import com.mcoding.comp.wechat.common.utils.XmlUtils;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.util.crypto.WxMpCryptUtil;

public class XmlOutBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public String toXml() {
		return XmlUtils.toXml(this);
	}
	
	/**
	 * 转换成加密的xml格式
	 * 
	 * @return
	 */
	public String toEncryptedXml(WxMpConfigStorage wxMpConfigStorage) {
		String plainXml = this.toXml();
		WxMpCryptUtil pc = new WxMpCryptUtil(wxMpConfigStorage);
		return pc.encrypt(plainXml);
	}


}
