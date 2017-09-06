package com.mcoding.comp.wechat.oauth.web.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcoding.base.core.JsonResult;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.service.AccountConfigService;
import com.mcoding.comp.wechat.account.utils.WxAccountConfigUtils;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;
import com.mcoding.comp.wechat.common.WxConstant;
import com.mcoding.comp.wechat.oauth.service.WechatOauthService;
import com.mcoding.comp.wechat.oauth.utils.WechatOauthUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import springfox.documentation.annotations.ApiIgnore;

@Api("微信公众号网页授权接口")
@Controller("wechatOauth")
public class WechatOauthController {
	
private static Logger logger = LoggerFactory.getLogger(WechatOauthController.class); 
	
    /**json字符串的正则表达式**/
//    private static final Pattern tuPattern = Pattern.compile("((\\w+):(.+?))[\\,\\}]");
    /**json字符串的正则表达式**/
    private static final Pattern urlPatther = Pattern.compile("((http|https):\\/\\/.+)?\\?(.+)");
	private static ObjectMapper objectMapper = new ObjectMapper();
	private static String defaultPage;
//	private static String serverName;
	
	@Autowired
	protected AccountConfigService accountConfigService;
	
//	protected WechatOauthService wechatOauthService = WechatOauthUtils.getBean(WechatOauthService.class);
	
	/**
	 * 接受微信传来的 授权信息
	 * @param code openid
	 * @param params 将要跳转的地址
	 * @param modelMap
	 * @param request
	 * @return
	 * @throws WxErrorException
	 */
	@ApiIgnore
	@RequestMapping(WxConstant.OAUTH_2_GET_OPENID + "/{params}")
	public ModelAndView receiveOpenid(@PathVariable("params") String params, String code, ModelMap modelMap, HttpServletRequest request) throws WxErrorException{
		logger.debug("receive wechat post code :" + code +", with params :" + params);

		Map<String, String> paramsMap = null;
		String targetUrl = null;
		try {
			paramsMap = this.getParamsMapFromState(params);
			if(paramsMap != null){
				targetUrl = paramsMap.get(WxConstant.PARAMS_MAP_KEY_TARGET_URL);
//				paramsMap.remove(WxConstant.PARAMS_MAP_KEY_TARGET_URL);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		String requestUri = targetUrl.replaceAll("https?://.+?/", "/");
//		AccountConfig accountConfig = this.accountConfigService.queryByRequest("http", serverName, 80, requestUri);
		AccountConfig accountConfig = this.accountConfigService.queryByOriginId(paramsMap.get(WxConstant.PARAMS_MAP_KEY_TARGET_APPID));
		
		WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(accountConfig);
		logger.debug("wxMpService:" + wxMpService + ",account:" +accountConfig);
//		WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByRequest(request);
		
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
		String openId = wxMpOAuth2AccessToken.getOpenId();

		String serverName = StringUtils.substringBefore(StringUtils.substringBetween(targetUrl, "://", "/"),":");
		if(StringUtils.isBlank(targetUrl)){
			targetUrl = serverName + defaultPage;
		}
		logger.debug("after oauth redirect to :" +targetUrl);
		
		handleWithOpenidAndParamsMap(openId, paramsMap, request);
		
		request.getSession().setAttribute("openid", openId);
		request.setAttribute("openid", openId);
		
		String redirectTargetUrl = (String) request.getAttribute("redirectTargetUrl");
		if (StringUtils.isNotBlank(redirectTargetUrl)) {
			targetUrl = redirectTargetUrl;
		}
		
		String viewName = "redirect:" + targetUrl;
//		String viewName = "forward:" + targetUrl;
		paramsMap.put("openid", openId);
		ModelAndView modelAndView = new ModelAndView(viewName, paramsMap);
		
		return modelAndView;
	}
	
	private void handleWithOpenidAndParamsMap(String openId, Map<String, String> paramsMap, HttpServletRequest request) {
		WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
		Map<String, WechatOauthService> beanMaps = applicationContext.getBeansOfType(WechatOauthService.class);
		if (MapUtils.isEmpty(beanMaps)) {
			return;
		}
		
		Set<String> beanSet = beanMaps.keySet();
		Iterator<String> iterator = beanSet.iterator();
		while (iterator.hasNext()) {
			beanMaps.get(iterator.next()).handleForOpenId(openId, paramsMap, request);
		}
	}
	
	private void handleWithWxUserAndParamsMap(WxMpUser wxMpUser, Map<String, String> paramsMap,
			HttpServletRequest request) {
		WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
		Map<String, WechatOauthService> beanMaps = applicationContext.getBeansOfType(WechatOauthService.class);
		if (MapUtils.isEmpty(beanMaps)) {
			return;
		}
		
		Set<String> beanSet = beanMaps.keySet();
		Iterator<String> iterator = beanSet.iterator();
		while (iterator.hasNext()) {
			beanMaps.get(iterator.next()).handleForWxUser(wxMpUser, paramsMap, request);
		}
	}

	/**
	 * 根据授权获取会员信息
	 * @param code
	 * @param params
	 * @param modelMap
	 * @return
	 * @throws WxErrorException
	 */
	@ApiIgnore
	@RequestMapping(WxConstant.OAUTH_2_GET_WX_USER_INFO  + "/{params}")
	public ModelAndView receiveCodeAndGetWxUserInfo(@PathVariable("params") String params, String code, ModelMap modelMap,HttpServletRequest request) throws WxErrorException{
		logger.debug("receive wechat post code :" + code +", with params :" + params);
		long t1 = System.currentTimeMillis();
		
		Map<String, String> paramsMap = null;
		String targetUrl = null;
		try {
			paramsMap = this.getParamsMapFromState(params);
			if (paramsMap != null) {
				targetUrl = paramsMap.get(WxConstant.PARAMS_MAP_KEY_TARGET_URL);
				paramsMap.remove(WxConstant.PARAMS_MAP_KEY_TARGET_URL);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		String requestUri = targetUrl.replaceAll("https?://.+?/", "/");
//		AccountConfig accountConfig = this.accountConfigService.queryByRequest("http", serverName, 80, requestUri);
		AccountConfig accountConfig = this.accountConfigService.queryByOriginId(paramsMap.get(WxConstant.PARAMS_MAP_KEY_TARGET_APPID));
		
		WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(accountConfig);
//        WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByRequest(request);
		
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
		
		logger.debug("get oAuth2 AccessToken, cost ["+(System.currentTimeMillis() - t1)+"ms]");
		logger.debug("get oAuth2 AccessToken:" + wxMpOAuth2AccessToken);
		
		long t2 = System.currentTimeMillis();
		WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
		
		logger.debug("get userinfo cost["+ (System.currentTimeMillis()-t2) +"ms]");
		logger.debug("get userInfo:" + wxMpUser);
		
		handleWithWxUserAndParamsMap(wxMpUser, paramsMap, request);
		
		String openId = wxMpOAuth2AccessToken.getOpenId();
		request.getSession().setAttribute("openid", openId);
		request.getSession().setAttribute("wxMpUser", wxMpUser);
		
		String serverName = StringUtils.substringBefore(StringUtils.substringBetween(targetUrl, "://", "/"),":");
		if(StringUtils.isBlank(targetUrl)){
			targetUrl = serverName + defaultPage;
		}else if (targetUrl.startsWith("/")) {
			targetUrl = serverName + targetUrl;
		}
		
		String viewName = "redirect:" + targetUrl; 
//		String viewName = "forward:" + targetUrl;
		paramsMap.put("openid", openId);
		ModelAndView modelAndView = new ModelAndView(viewName, paramsMap);
		return modelAndView;
	}
	
	/**
	 * 根据微信服务返回的 参数 “state”，解析出参数map
	 * @param params
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private Map<String, String> getParamsMapFromState(String params) throws JsonParseException, JsonMappingException, IOException{
		Map<String, String> paramsMap= new Hashtable<>();
		
		if (StringUtils.isBlank(params)) {
			return null;
		}
		
		byte[] byteArray = Base64.decodeBase64(params);
		params = new String(byteArray);
		
		if (params.contains("\"")) { 
			//如果是json格式
			paramsMap = objectMapper.readValue(params, Map.class);
			logger.debug("get map from json :" + paramsMap);

		} else {
			//如果是字符串格式
			Pattern tuPattern = Pattern.compile("((\\w+):(.+?))[\\,\\}]");
			Matcher tuMatcher = tuPattern.matcher(params);
			
			while(tuMatcher.find()){
				String key = tuMatcher.group(2);
				String value = tuMatcher.group(3);
				
				if(StringUtils.isNotBlank(key)){
					paramsMap.put(key, value);
				}
			}
		}
		return paramsMap;
	}
	
	
	@ApiOperation(httpMethod="GET", value="获取授权openid的跳转地址")
    @RequestMapping("front/createOauthUrlForOpenid")
    @ResponseBody
	public JsonResult<String> createOauthUrlForOpenid(
			@ApiParam(value="授权的页面") String url, 
			@ApiParam(value="微信公众号的原始id，可以不填") String originId, HttpServletRequest request) throws Exception {
		Map<String, String> paramsMap = null;
		String targetUrl = null;
		
		Matcher matcher = urlPatther.matcher(url);
		if (matcher.find()) {
			List<NameValuePair> list = URLEncodedUtils.parse(matcher.group(3), Charset.forName("utf-8"));
			paramsMap = new HashMap<>();
			for (int i = 0; i < list.size(); i++) {
				NameValuePair params = list.get(i);
				System.out.println("name" + params.getName() +",value" + params.getValue());
				paramsMap.put(params.getName(), params.getValue());
			}
			
			url = matcher.group(1);
		}else{
			targetUrl = url;
		}
		
		
		AccountConfig accountConfig = null; 
		if (StringUtils.isNotBlank(originId)) {
			accountConfig = WxAccountConfigUtils.getByOrginId(originId);
		}else{
			String serverName = StringUtils.substringBetween(url, "://", "/");
			String requestUri = url.replaceAll("https?://.+?/", "/");
			accountConfig = this.accountConfigService.queryByRequest("http", serverName, 80, requestUri);
//			accountConfig = this.accountConfigService.queryByRequest(request);
		}
		
		String oauthUrl = WechatOauthUtils.createOauthUrlForOpenid(targetUrl, paramsMap, accountConfig);
		
		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		result.setData(oauthUrl);
		return result;
	}
	
	@ApiOperation(httpMethod="GET", value="获取会员资料的跳转地址")
    @RequestMapping("front/createOauthUrlForWxUserInfo")
    @ResponseBody
	public JsonResult<String> createOauthUrlForWxUserInfo(
			@ApiParam(value="授权的页面") String url, 
			@ApiParam(value="微信公众号的原始id，可以不填") String originId, HttpServletRequest request) throws Exception {
		Map<String, String> paramsMap = null;
		String targetUrl = null;
		
		Matcher matcher = urlPatther.matcher(url);
		if (matcher.find()) {
			List<NameValuePair> list = URLEncodedUtils.parse(matcher.group(3), Charset.forName("utf-8"));
			paramsMap = new HashMap<>();
			for (int i = 0; i < list.size(); i++) {
				NameValuePair params = list.get(i);
				System.out.println("name" + params.getName() +",value" + params.getValue());
				paramsMap.put(params.getName(), params.getValue());
			}
			
			targetUrl = matcher.group(1);
		}else{
			targetUrl = url;
		}
		
		
		AccountConfig accountConfig = null; 
		if (StringUtils.isNotBlank(originId)) {
			accountConfig = WxAccountConfigUtils.getByOrginId(originId);
		}else{
			
			String serverName = StringUtils.substringBetween(url, "://", "/");
			String requestUri = url.replaceAll("https?://.+?/", "/");
			accountConfig = this.accountConfigService.queryByRequest("http", serverName, 80, requestUri);
//			accountConfig = SpringContextHolder.getOneBean(AccountConfigService.class).queryByRequest(request);
//			StoreUtils.getStoreFromThreadLocal().getId();
//			Storew
		}
		
		String oauthUrl = WechatOauthUtils.createOauthUrlForWxUserInfo(targetUrl, paramsMap, accountConfig);
		
		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		result.setData(oauthUrl);
		return result;
	}
	
}
