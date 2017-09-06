package com.mcoding.comp.wechat.pay.service.impl;

import java.io.IOException;

import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mcoding.comp.wechat.common.WxConstant;
import com.mcoding.comp.wechat.pay.bean.WxMpXmlUnifiedOrderInMessage;
import com.mcoding.comp.wechat.pay.bean.WxMpXmlUnifiedOrderOutMessage;

import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.Utf8ResponseHandler;

public class WxMpPayPostRequest implements RequestExecutor<WxMpXmlUnifiedOrderInMessage, WxMpXmlUnifiedOrderOutMessage> {
	
	public static Logger logger = LoggerFactory.getLogger(WxMpPayPostRequest.class);

	@Override
	public WxMpXmlUnifiedOrderInMessage execute(CloseableHttpClient httpclient, HttpHost httpProxy, String uri,
			WxMpXmlUnifiedOrderOutMessage postEntity) throws WxErrorException, ClientProtocolException, IOException {
		
		HttpPost httpPost = new HttpPost(uri);
		if (httpProxy != null) {
			RequestConfig config = RequestConfig.custom().setProxy(httpProxy).build();
			httpPost.setConfig(config);
		}

		if (postEntity != null) {
			StringEntity entity = new StringEntity(postEntity.toXml(), Consts.UTF_8);
			httpPost.setEntity(entity);
		}
		
		logger.info("send pay request body:" + postEntity);
		
		CloseableHttpResponse response = httpclient.execute(httpPost);
		String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
		
		logger.info("get pay response body:" + responseContent);
		WxMpXmlUnifiedOrderInMessage inMsg = WxMpXmlUnifiedOrderInMessage.fromXml(responseContent);
		
		if (inMsg == null) {
			WxError error = new WxError();
			error.setErrorMsg("微信服务器返回null");
			throw new WxErrorException(error);
		}
		
		if (!WxConstant.RETURN_CODE_SUCCESS.equals(inMsg.getReturnCode())) {
			WxError error = new WxError();
			error.setErrorMsg("code:" +  inMsg.getReturnCode() + ",msg:" + inMsg.getReturnMsg());
			throw new WxErrorException(error);
		}
		
		if(!WxConstant.RETURN_CODE_SUCCESS.equals(inMsg.getResultCode())){
			WxError error = new WxError();
			error.setErrorMsg("code:" + inMsg.getResultCode() +",msg:"+inMsg.getErrCodeDes());
			throw new WxErrorException(error);
		}
		
		return inMsg;
	}
	
	/*public static void main(String[] args) {
		String abc ="<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wx2840dda77e835ba0]]></appid><mch_id><![CDATA[1316437101]]></mch_id><device_info><![CDATA[WEB]]></device_info><nonce_str><![CDATA[3jfx3ggcTCwsTr12]]></nonce_str><sign><![CDATA[476E437566B7C34833C04A51ACFC7B4F]]></sign><result_code><![CDATA[FAIL]]></result_code><err_code><![CDATA[INVALID_REQUEST]]></err_code><err_code_des><![CDATA[201 商户订单号重复]]></err_code_des></xml>";
		WxMpXmlUnifiedOrderInMessage inMsg = WxMpXmlUnifiedOrderInMessage.fromXml(abc);
		System.out.println(!WxConstant.RETURN_CODE_SUCCESS.equals(inMsg.getResultCode()));
	}*/

}
