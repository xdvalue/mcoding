package com.mcoding.comp.wechat.pay.bean;import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;

/**
 * 统一下单接口 输入参数
 * @author hzy
 *
 */
@XStreamAlias("xml")
public class WxMpXmlUnifiedOrderInMessage {
/**
<xml>
   <return_code><![CDATA[SUCCESS]]></return_code>
   <return_msg><![CDATA[OK]]></return_msg>
   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
   <mch_id><![CDATA[10000100]]></mch_id>
   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>
   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>
   <result_code><![CDATA[SUCCESS]]></result_code>
   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>
   <trade_type><![CDATA[JSAPI]]></trade_type>
</xml>
 */
	
	@XStreamAlias("appid")
    private String appid;
	
	@XStreamAlias("mch_id")
    private String mchId;
	
	@XStreamAlias("device_info")
    private String deviceInfo;
	
	@XStreamAlias("nonce_str")
	private String nonceStr;
	
	@XStreamAlias("sign")
	private String sign;
	
	@XStreamAlias("result_code")
	private String resultCode;
	
	@XStreamAlias("err_code")
	private String errCode;
	
	@XStreamAlias("err_code_des")
	private String errCodeDes;
	
	@XStreamAlias("trade_type")
	private String tradeType;
	
	@XStreamAlias("prepay_id")
	private String prepayId;
	
	@XStreamAlias("code_url")
    private String codeUrl;
	
	@XStreamAlias("return_code")
	@XStreamConverter(value=XStreamCDataConverter.class)
    private String returnCode;
	
	@XStreamAlias("return_msg")
	@XStreamConverter(value=XStreamCDataConverter.class)
    private String returnMsg;
    
    
    public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrCodeDes() {
		return errCodeDes;
	}

	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public static WxMpXmlUnifiedOrderInMessage fromXml(String xml) {
    	XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(WxMpXmlUnifiedOrderInMessage.class);
        Object obj = xstream.fromXML(xml);
		return (WxMpXmlUnifiedOrderInMessage) obj;
	}
	
	public String toXml() {
		XStream xstream = XStreamInitializer.getInstance();
	    xstream.processAnnotations(WxMpXmlUnifiedOrderInMessage.class);
	    return xstream.toXML(this).replaceAll("__", "_");
	}
	
	public static void main(String[] args) {
		String xml = "<xml>"
				+ "<return_code><![CDATA[SUCCESS]]></return_code>"
				+ "<return_msg><![CDATA[OK]]></return_msg>"
				+ "<appid><![CDATA[wxae047a7cc79e6811]]></appid>"
				+ "<mch_id><![CDATA[1261537501]]></mch_id>"
				+ "<device_info><![CDATA[WEB]]></device_info>"
				+ "<nonce_str><![CDATA[6K7io0BNZRuy8H6u]]></nonce_str>"
				+ "<sign><![CDATA[A2D9549B29F32D027819F1721EAD25B8]]></sign>"
				+ "<result_code><![CDATA[SUCCESS]]></result_code>"
				+ "<prepay_id><![CDATA[wx20150908092207873c39b6060500719224]]></prepay_id>"
				+ "<trade_type><![CDATA[NATIVE]]></trade_type>"
				+ "<code_url><![CDATA[weixin://wxpay/bizpayurl?pr=lbhb9lG]]></code_url>"
				+ "</xml>";
		WxMpXmlUnifiedOrderInMessage inMsg = fromXml(xml);
		System.out.println(inMsg.getReturnCode()+":" +inMsg.getReturnMsg());
	}

}
