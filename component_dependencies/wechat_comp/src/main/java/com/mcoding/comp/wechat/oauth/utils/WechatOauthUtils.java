package com.mcoding.comp.wechat.oauth.utils;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;
import com.mcoding.comp.wechat.common.WxConstant;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

/**
 * 网页授权 工具
 * @author hzy
 *
 */
public class WechatOauthUtils {

	private static Logger logger = LoggerFactory.getLogger(WechatOauthUtils.class);

	/**
	 * 创建 获取openid的授权地址，并注入授权的参数
	 * 
	 * @param targetUrl
	 *            授权后，跳转的目标地址
	 * @param paramsMap
	 *            跳转目标地址的时候，带上的参数
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws WxErrorException
	 */
	public static String createOauthUrlForOpenid(String targetUrl, Map<String, String> paramsMap,
			AccountConfig accountConfig) throws Exception {
		if (accountConfig == null) {
			throw new NullPointerException("没有对应的微信公众号的账号配置，无法进行授权");
		}

		if (paramsMap == null) {
			paramsMap = new Hashtable<>();
		}

		paramsMap.put(WxConstant.PARAMS_MAP_KEY_TARGET_URL, targetUrl);
		paramsMap.put(WxConstant.PARAMS_MAP_KEY_TARGET_APPID, accountConfig.getOriginId());

//		String json = JsonUtilsForMcoding.writeValueAsString(paramsMap);
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(paramsMap);
//		String params = URLEncoder.encode(json, "UTF-8");
		String params = Base64.encodeBase64String(json.getBytes());

		String domain = accountConfig.getDomain().replaceAll("/$", "");
		domain = domain.replaceAll(":\\w+", "");
		String getCodeUrl = domain + WxConstant.OAUTH_2_GET_OPENID + "/" + params;

		WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(accountConfig);
		String oauth2Url = wxMpService.oauth2buildAuthorizationUrl(getCodeUrl, WxConsts.OAUTH2_SCOPE_BASE, null);

		return oauth2Url;
	}

	/**
	 * 创建 获取微信用户信息的授权地址，并注入授权的参数
	 * 
	 * @param targetUrl
	 *            授权成功后，跳转的目标地址
	 * @param paramsMap
	 *            跳转目标地址的时候，带上的参数
	 * @return
	 * @throws Exception
	 */
	public static String createOauthUrlForWxUserInfo(String targetUrl, Map<String, String> paramsMap,
			AccountConfig accountConfig) throws Exception {
		if (accountConfig == null) {
			throw new NullPointerException("没有对应的微信公众号的账号配置，无法进行授权");
		}

		if (paramsMap == null) {
			paramsMap = new Hashtable<>();
		}

		paramsMap.put(WxConstant.PARAMS_MAP_KEY_TARGET_URL, targetUrl);
		paramsMap.put(WxConstant.PARAMS_MAP_KEY_TARGET_APPID, accountConfig.getOriginId());

//		String json = JsonUtilsForMcoding.writeValueAsString(paramsMap);
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(paramsMap);
//		String paramsStr = URLEncoder.encode(json, "UTF-8");
		String params = Base64.encodeBase64String(json.getBytes());

		String domain = accountConfig.getDomain().replaceAll("/$", "");
		logger.debug("[create oauth url] domain:" + domain);
		domain = domain.replaceAll(":\\w+", "");
		String getCodeUrl = domain + WxConstant.OAUTH_2_GET_WX_USER_INFO + "/" + params;
		logger.debug("[create oauth url] getCodeUrl:" + getCodeUrl);

		WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(accountConfig);
		String oauth2Url = wxMpService.oauth2buildAuthorizationUrl(getCodeUrl, WxConsts.OAUTH2_SCOPE_USER_INFO,
				null);

		return oauth2Url;
	}

}
