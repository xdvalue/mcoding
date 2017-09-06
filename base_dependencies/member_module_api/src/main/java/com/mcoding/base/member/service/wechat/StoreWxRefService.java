package com.mcoding.base.member.service.wechat;

import java.util.Map;

import com.mcoding.base.member.bean.wechat.StoreWxRef;
import com.mcoding.base.member.bean.wechat.StoreWxRefExample;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.service.common.BaseService;
import com.mcoding.comp.wechat.bean.account.AccountConfig;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

public interface StoreWxRefService extends BaseService<StoreWxRef, StoreWxRefExample> {

	 public AccountConfig queryWxAccountByStoreId(Integer storeId);

	// public String queryWechatOauthUrlByRequest(HttpServletRequest request)
	// throws Exception;
	
	public String queryWxAppidByStoreId(Integer storeId);
	
	public String queryWxAppSecretByStoreId(Integer storeId);

	public boolean sendWxMpTemplateMessage(Integer storeId, String templateType, String receiverOpenid, String url,
			String keyword1, String keyword2, String keyword3, String keyword4, String keyword5) throws Exception;

	public boolean sendWxMpTemplateMessage(Integer storeId, String templateType, String receiverOpenid, String first,
			String remark, String url, String color, String keyword1, String keyword2, String keyword3, String keyword4,
			String keyword5) throws Exception;

	public String queryWechatOauthUrlForOpenId(String scheme, String serverName, int serverPort, String requestUri,
			Map<String, String> params, String ref) throws Exception;

	public String queryWechatOauthUrlForWxUserInfo(String scheme, String serverName, int serverPort, String requestUri,
			Map<String, String> params, String ref) throws Exception;

	public String queryWechatOauthUrlForOpenId(String scheme, String serverName, int serverPort, String requestUri,
			Map<String, String> params, String ref, boolean isShortUrl) throws Exception;

	public String queryWechatOauthUrlForWxUserInfo(String scheme, String serverName, int serverPort, String requestUri,
			Map<String, String> params, String ref, boolean isShortUrl) throws Exception;

//	public WxJsapiSignature getJsapiSignature(String scheme, String serverName, int serverPort, String requestUri) throws WxErrorException;
	
	public WxMpService queryWxMpServiceByStoreId(Integer storeId);

}