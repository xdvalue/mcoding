package com.mcoding.comp.wechat.pay.bean;

import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

import com.mcoding.comp.wechat.common.WxConstant;
import com.mcoding.comp.wechat.common.XmlOutBean;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

/**
 * 统一下单 输出参数
 * @author hzy
 *
 */
@XStreamAlias("xml")
public class WxMpXmlUnifiedOrderOutMessage extends XmlOutBean{

	/**
<xml> 
    <appid>wx2421b1c4370ec43b</appid> 
    <attach>支付测试</attach>
    <body>JSAPI支付测试</body> 
    <mch_id>10000100</mch_id>
    <nonce_str>1add1a30ac87aa2db72f57a2375d8fec</nonce_str>
    <notify_url>http://wxpay.weixin.qq.com/pub_v2/pay/notify.v2.php</notify_url> 
    <openid>oUpF8uMuAJO_M2pxb1Q9zNjWeS6o</openid>
    <out_trade_no>1415659990</out_trade_no>
    <spbill_create_ip>14.23.150.211</spbill_create_ip>
    <total_fee>1</total_fee> 
    <trade_type>JSAPI</trade_type>
    <sign>0CB01533B8C1EF103065174F50BCA001</sign> 
</xml>
	 */
	private static final long serialVersionUID = 1L;
	
	@XStreamAlias("appid")
	private String appid;

	@XStreamAlias("mch_id")
	private String mch_id;
	
	@XStreamAlias("device_info")
	private String device_info;

	@XStreamAlias("nonce_str")
	private String nonce_str;
	
	@XStreamAlias("sign")
	private String sign;
	
	@XStreamAlias("body")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String body;
	
	@XStreamAlias("detail")
	private String detail;

	@XStreamAlias("attach")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String attach;
	
	@XStreamAlias("out_trade_no")
	private String out_trade_no;
	
	@XStreamAlias("fee_type")
	private String fee_type;
	
	@XStreamAlias("total_fee")
	private Integer total_fee;
	
	@XStreamAlias("spbill_create_ip")
	private String spbill_create_ip;
	
	@XStreamAlias("time_start")
	private String time_start;
	
	@XStreamAlias("time_expire")
	private String time_expire;
	
	@XStreamAlias("goods_tag")
	private String goods_tag;

	@XStreamAlias("notify_url")
	private String notify_url;
	
	@XStreamAlias("trade_type")
	private String trade_type;
	
	@XStreamAlias("product_id")
	private String product_id;
	
	@XStreamAlias("limit_pay")
	private String limit_pay;

	@XStreamAlias("openid")
	private String openid;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getMchId() {
		return mch_id;
	}

	public void setMchId(String mchId) {
		this.mch_id = mchId;
	}

	public String getNonceStr() {
		return nonce_str;
	}

	public void setNonceStr(String nonceStr) {
		this.nonce_str = nonceStr;
	}

	public String getNotifyUrl() {
		return notify_url;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notify_url = notifyUrl;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getOutTradeNo() {
		return out_trade_no;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.out_trade_no = outTradeNo;
	}

	public String getSpbillCreateIp() {
		return spbill_create_ip;
	}

	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbill_create_ip = spbillCreateIp;
	}

	public Integer getTotalFee() {
		return total_fee;
	}

	public void setTotalFee(Integer totalFee) {
		this.total_fee = totalFee;
	}

	public String getTradeType() {
		return trade_type;
	}

	public void setTradeType(String tradeType) {
		this.trade_type = tradeType;
	}

	public String getDeviceInfo() {
		return device_info;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.device_info = deviceInfo;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getFeeType() {
		return fee_type;
	}

	public void setFeeType(String feeType) {
		this.fee_type = feeType;
	}

	public String getTimeStartStr() {
		return time_start;
	}

	public void setTimeStartStr(String timeStartStr) {
		this.time_start = timeStartStr;
	}

	public String getTimeExpire() {
		return time_expire;
	}

	public void setTimeExpire(String timeExpire) {
		this.time_expire = timeExpire;
	}

	public String getGoodsTag() {
		return goods_tag;
	}

	public void setGoodsTag(String goodsTag) {
		this.goods_tag = goodsTag;
	}

	public String getProductId() {
		return product_id;
	}

	public void setProductId(String productId) {
		this.product_id = productId;
	}

	public String getLimitPay() {
		return limit_pay;
	}

	public void setLimitPay(String limitPay) {
		this.limit_pay = limitPay;
	}

	public static void main(String[] args) {
		WxMpXmlUnifiedOrderOutMessage msg = new WxMpXmlUnifiedOrderOutMessage();
		msg.setAppid("abc");
		msg.setAttach("abcd");
		System.out.println(msg.toXml());
	}

	public void setValueFromWxMpOrderInfo(WxMpOrder wxMpOrder) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		
		this.setBody(wxMpOrder.getProductBody());
		this.setDetail(wxMpOrder.getProductDetail());
		this.setAttach(wxMpOrder.getAttach());
		this.setOutTradeNo(wxMpOrder.getOutTradeNo());
		this.setTotalFee(wxMpOrder.getTotalFee());
		this.setGoodsTag(wxMpOrder.getGoodsTag());
		this.setOpenid(wxMpOrder.getOpenId());
		
		if (StringUtils.isNotBlank(wxMpOrder.getFeeType())) {
			this.setFeeType(wxMpOrder.getFeeType());
		}else{
			this.setFeeType(WxConstant.FEE_TYPE_CNY);
		}
		
		if(wxMpOrder.getTimeStart() != null){
			this.setTimeStartStr(format.format(wxMpOrder.getTimeStart()));
		}
		if (wxMpOrder.getTimeExpire() != null) {
			this.setTimeExpire(format.format(wxMpOrder.getTimeExpire()));
		}
	}

}
