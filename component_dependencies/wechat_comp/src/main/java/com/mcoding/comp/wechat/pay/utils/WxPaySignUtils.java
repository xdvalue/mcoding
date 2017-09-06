package com.mcoding.comp.wechat.pay.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.utils.encryption.Md5Utils;
import com.mcoding.comp.wechat.common.WxConstant;
import com.mcoding.comp.wechat.jssdk.bean.WxJsPayParams;
import com.mcoding.comp.wechat.pay.bean.WxMpOrder;

import me.chanjar.weixin.mp.bean.pay.request.WxPayUnifiedOrderRequest;

public class WxPaySignUtils {
	
    private static Logger logger = LoggerFactory.getLogger(WxPaySignUtils.class);
    
    /**
     * 统一下单时候，需要给下单参数做签名
     * @param message
     * @param key
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
	@SuppressWarnings("rawtypes")
	public static String sign(Object message, String key) throws IllegalArgumentException, IllegalAccessException {
		ArrayList<String> list = new ArrayList<String>();
		Class cls = message.getClass();
		Field[] fields = cls.getDeclaredFields();
		
		for (Field f : fields) {
			f.setAccessible(true);
			if (f.getName().equals("serialVersionUID")) {
				continue;
			}
			
			if (f.get(message) != null && f.get(message) != "") {
				list.add(f.getName() + "=" + f.get(message) + "&");
			}
		}
		
		int size = list.size();
		String[] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(arrayToSort[i]);
		}
		
		String result = sb.toString();
		result += "key=" + key;
		logger.debug("Sign Before MD5:" + result);
		result = Md5Utils.md5Encode(result).toUpperCase();
		logger.debug("Sign Result:" + result);
		return result;
	}
	
	/***
	 * 前端使用js，调用微信h5 支付接口时候，需要的签名
	 * @param message
	 * @param key
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String getSignForJsParams(WxJsPayParams message, String key) throws IllegalArgumentException, IllegalAccessException{
		if (StringUtils.isBlank(message.getAppId()) || StringUtils.isBlank(message.getNonceStr())
				|| StringUtils.isBlank(message.getPackageStr()) || StringUtils.isBlank(message.getSignType()) 
				|| StringUtils.isBlank(message.getTimeStamp())) {
			throw new CommonException("支付参数不完整");
		}
		
		if (String.valueOf(System.currentTimeMillis()).length() == message.getTimeStamp().length()) {
			//如果传进来参数不对，那就把毫秒 转为 秒
			throw new CommonException("支付jsapi参数中，时间戳的单位是 秒");
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("appId=").append(message.getAppId()).append("&");
		sb.append("nonceStr=").append(message.getNonceStr()).append("&");
		sb.append("package=").append(message.getPackageStr()).append("&");
		sb.append("signType=").append(message.getSignType()).append("&");
		sb.append("timeStamp=").append(message.getTimeStamp()).append("&");
	    sb.append("key=").append(key);
		
		String result = sb.toString();
		logger.debug("Sign Before MD5:" + result);
		result = Md5Utils.md5Encode(result).toUpperCase();
		logger.debug("Sign Result:" + result);
		return result;
	}
	
	public static WxPayUnifiedOrderRequest fromWxOrder(WxMpOrder wxMpOrder){
		WxPayUnifiedOrderRequest outMsg = new WxPayUnifiedOrderRequest();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		
		outMsg.setBody(wxMpOrder.getProductBody());
		outMsg.setDetail(wxMpOrder.getProductDetail());
		outMsg.setAttach(wxMpOrder.getAttach());
		outMsg.setOutTradeNo(wxMpOrder.getOutTradeNo());
		outMsg.setTotalFee(wxMpOrder.getTotalFee());
		outMsg.setGoodsTag(wxMpOrder.getGoodsTag());
		outMsg.setOpenid(wxMpOrder.getOpenId());
		
		if (StringUtils.isNotBlank(wxMpOrder.getFeeType())) {
			outMsg.setFeeType(wxMpOrder.getFeeType());
		}else{
			outMsg.setFeeType(WxConstant.FEE_TYPE_CNY);
		}
		
		if(wxMpOrder.getTimeStart() != null){
			outMsg.setTimeStart(format.format(wxMpOrder.getTimeStart()));
		}
		if (wxMpOrder.getTimeExpire() != null) {
			outMsg.setTimeExpire(format.format(wxMpOrder.getTimeExpire()));
		}
		return outMsg;
	}

}
