package com.mcoding.comp.wechat.pay.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

/**
 * 支付结果 通用输入参数
 * @author hzy
 *
 */
@XStreamAlias("xml")
public class WxMpXmlInOrderQueryMessage{
	
	@XStreamAlias("return_code")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String returnCode;
	
	@XStreamAlias("return_msg")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String returnMsg;
	
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
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String errCodeDes;
	
	@XStreamAlias("openid")
	private String openid;
	
	@XStreamAlias("is_subscribe")
	private String isSubscribe;
	
	@XStreamAlias("trade_type")
	private String tradeType;
	
	@XStreamAlias("trade_state")
	private String tradeState;
	
	@XStreamAlias("bank_type")
	private String bankType;
	
	@XStreamAlias("total_fee")
	private Integer totalFee;
	
	@XStreamAlias("fee_type")
	private String feeType;
	
	@XStreamAlias("cash_fee")
	private Integer cashFee;
	
	@XStreamAlias("cash_fee_type")
	private String cashFeeType;
	
	@XStreamAlias("coupon_fee")
	private Integer couponFee;
	
	@XStreamAlias("coupon_count")
	private Integer couponCount;
	
	@XStreamAlias("coupon_batch_id_$n")
	private String couponBatchId_$n;
	
	@XStreamAlias("coupon_id_$n")
	private String couponId_$n;
	
	@XStreamAlias("coupon_fee_$n")
	private Integer couponFee_$n;
	
	@XStreamAlias("transaction_id")
	private String transactionId;
	
	@XStreamAlias("out_trade_no")
	private String outTradeNo;
	
	@XStreamAlias("attach")
	private String attach;
	
	@XStreamAlias("time_end")
	private String timeEnd;
	
	@XStreamAlias("trade_state_desc")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String tradeStateDesc;

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

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public Integer getCashFee() {
		return cashFee;
	}

	public void setCashFee(Integer cashFee) {
		this.cashFee = cashFee;
	}

	public String getCashFeeType() {
		return cashFeeType;
	}

	public void setCashFeeType(String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}

	public Integer getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(Integer couponFee) {
		this.couponFee = couponFee;
	}

	public Integer getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(Integer couponCount) {
		this.couponCount = couponCount;
	}

	public String getCouponBatchId_$n() {
		return couponBatchId_$n;
	}

	public void setCouponBatchId_$n(String couponBatchId_$n) {
		this.couponBatchId_$n = couponBatchId_$n;
	}

	public String getCouponId_$n() {
		return couponId_$n;
	}

	public void setCouponId_$n(String couponId_$n) {
		this.couponId_$n = couponId_$n;
	}

	public Integer getCouponFee_$n() {
		return couponFee_$n;
	}

	public void setCouponFee_$n(Integer couponFee_$n) {
		this.couponFee_$n = couponFee_$n;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getTradeStateDesc() {
		return tradeStateDesc;
	}

	public void setTradeStateDesc(String tradeStateDesc) {
		this.tradeStateDesc = tradeStateDesc;
	}

}

