package com.mcoding.comp.wechat.pay.bean;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import me.chanjar.weixin.common.util.xml.XStreamInitializer;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.util.crypto.WxMpCryptUtil;

/**
 * 查询订单的输出参数
 * @author hzy
 *
 */
@XStreamAlias("xml")
public class WxMpXmlOutOrderQueryMessage {
	
	@XStreamAlias("appid")
	private String appid;

	@XStreamAlias("mch_id")
	private String mch_id;
	
	@XStreamAlias("transaction_id")
	private String transaction_id;
	
	@XStreamAlias("out_trade_no")
	private String out_trade_no;
	
	@XStreamAlias("nonce_str")
	private String nonce_str;
	
	@XStreamAlias("sign")
	private String sign;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMchId() {
		return mch_id;
	}

	public void setMchId(String mchId) {
		this.mch_id = mchId;
	}

	public String getTransactionId() {
		return transaction_id;
	}

	public void setTransactionId(String transactionId) {
		this.transaction_id = transactionId;
	}

	public String getOutTradeNo() {
		return out_trade_no;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.out_trade_no = outTradeNo;
	}

	public String getNonceStr() {
		return nonce_str;
	}

	public void setNonceStr(String nonceStr) {
		this.nonce_str = nonceStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public String toXml() {
		XStream xstream = XStreamInitializer.getInstance();
	    xstream.processAnnotations(WxMpXmlOutOrderQueryMessage.class);
	    return xstream.toXML(this).replaceAll("__", "_");
	}

	/**
	 * 转换成加密的xml格式
	 * 
	 * @return
	 */
	public String toEncryptedXml(WxMpConfigStorage wxMpConfigStorage) {
		String plainXml = toXml();
		WxMpCryptUtil pc = new WxMpCryptUtil(wxMpConfigStorage);
		return pc.encrypt(plainXml);
	}
	

}
