package com.mcoding.comp.wechat.common;

/**
 * 微信参数
 * @author hzy
 *
 */
public class WxConstant {
	
	/**统一下单接口**/
	public static final String URL_UNIFIED_ORDER= "https://api.mch.weixin.qq.com/pay/unifiedorder";

	/**支付订单查询接口*/
	public static final String URL_ORDER_QUERY= "https://api.mch.weixin.qq.com/pay/orderquery";
	
	/**异步支付回调接口**/
	public static final String URL_PAID_NOTIFY = "wechatPay/front/notifyUrl";
	
	/**发送普通红包接口**/
	public static final String URL_SEND_REDPACK = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
	
	/**支付 通信标识 成功**/
	public static final String RETURN_CODE_SUCCESS = "SUCCESS";
	/**支付 通信标识 失败**/
	public static final String RETURN_CODE_FAIL = "FAIL";
	
	/**支付成功**/
	public static final String TRADE_STATUS_SUCCESS = "SUCCESS";
	/**转入退款**/
	public static final String TRADE_STATUS_REFUND = "REFUND";
	/**未支付**/
	public static final String TRADE_STATUS_NOTPAY = "NOTPAY";
	/**—已关闭**/
	public static final String TRADE_STATUS_CLOSED = "CLOSED";
	/**已撤销（刷卡支付）**/
	public static final String TRADE_STATUS_REVOKED = "REVOKED";
	/**用户支付中**/
	public static final String TRADE_STATUS_USERPAYING = "USERPAYING";
	/**PAYERROR--支付失败(其他原因，如银行返回失败)**/
	public static final String TRADE_STATUS_PAYERROR = "PAYERROR";
	/**支付通知模板ID**/
	public static final String WX_TEMPLATE_MSG_ID_PAY = "Ld_R4Jakqse4dvpyKfulgfIs_fr_iafJdv0OiMVS1mA";
	
	/**
	 * 获取微信会员信息的接收地址<br/>
	 * /wecahtOauth/front/oauth2WxUserInfo
	 */
	public static final String OAUTH_2_GET_WX_USER_INFO = "/wecahtOauth/front/oauth2WxUserInfo";
	
	/**
	 * 获取微信会员openId的接收地址<br/>
	 * /wecahtOauth/front/oauth2Openid
	 */
	public static final String OAUTH_2_GET_OPENID = "/wecahtOauth/front/oauth2Openid";
	
	/**
	 * 传递的参数key,TU。表示目标地址
	 */
	public static final String PARAMS_MAP_KEY_TARGET_URL = "TU"; //targetUrl
	/**
	 * 传递公众号的appid
	 */
	public static final String PARAMS_MAP_KEY_TARGET_APPID = "APPID"; //targetUrl
	
	public static final Integer WX_MEMBER_SEX_MAN = 1;
	public static final Integer WX_MEMBER_SEX_WOMAN = 2;
	
	public static final String DEVICE_INFO_WEB = "WEB";
	
	public static final String TRADE_TYPE_JSAPI = "JSAPI";
	public static final String TRADE_TYPE_NATIVE = "NATIVE";
	public static final String TRADE_TYPE_APP = "APP";

	public static final String FEE_TYPE_CNY = "CNY";

	
}
