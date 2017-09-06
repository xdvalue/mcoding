package com.mcoding.base.member.service.wechat.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.wechat.StoreWxRef;
import com.mcoding.base.member.bean.wechat.StoreWxRefExample;
import com.mcoding.base.member.persistence.wechat.StoreWxRefMapper;
import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.service.store.StoreService;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.service.AccountConfigService;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;
import com.mcoding.comp.wechat.msg.utils.WxMpTemplateMsgUtils;
import com.mcoding.comp.wechat.oauth.utils.WechatOauthUtils;

import me.chanjar.weixin.mp.api.WxMpService;

@Service("storeWxRefService")
public class StoreWxRefServiceImpl implements StoreWxRefService {

	@Resource
	protected StoreService storeService;

	@Resource
	protected StoreWxRefMapper storeWxRefMapper;

	@Resource
	protected AccountConfigService accountConfigService;

	@CacheEvict(value = { "storeWxRef" }, allEntries = true)
	@Override
	public void addObj(StoreWxRef t) {
		this.storeWxRefMapper.insertSelective(t);
	}

	@CacheEvict(value = { "storeWxRef" }, allEntries = true)
	@Override
	public void deleteObjById(int id) {
		this.storeWxRefMapper.deleteByPrimaryKey(id);
	}

	@CacheEvict(value = { "storeWxRef" }, allEntries = true)
	@Override
	public void modifyObj(StoreWxRef t) {
		if (t.getId() == null || t.getId() == 0) {
			throw new NullPointerException("id 为空，无法更新");
		}
		this.storeWxRefMapper.updateByPrimaryKeySelective(t);
	}

	@Cacheable(value = "storeWxRef", key = "'StoreWxRefService_' + #root.methodName + '_' +#id")
	@Override
	public StoreWxRef queryObjById(int id) {
		return this.storeWxRefMapper.selectByPrimaryKey(id);
	}

	@Cacheable(value = "storeWxRef", key = "'StoreWxRefService_' + #root.methodName + '_'+ #example.toJson()")
	@Override
	public List<StoreWxRef> queryAllObjByExample(StoreWxRefExample example) {
		return this.storeWxRefMapper.selectByExample(example);
	}

	@Cacheable(value = "storeWxRef", key = "'StoreWxRefService_' + #root.methodName + '_'+ #example.toJson()")
	@Override
	public PageView<StoreWxRef> queryObjByPage(StoreWxRefExample example) {
		PageView<StoreWxRef> pageView = example.getPageView();
		if (pageView == null) {
			pageView = new PageView<>(1, 10);
			example.setPageView(pageView);
		}
		pageView.setQueryResult(this.storeWxRefMapper.selectByExampleByPage(example));
		return pageView;
	}

	@Cacheable(value="storeWxRef", key="'StoreWxRefService_' + #root.methodName + '_'+ #storeId")
	@Override
	public AccountConfig queryWxAccountByStoreId(Integer storeId) {
		StoreWxRefExample example = new StoreWxRefExample();
		example.createCriteria().andStoreIdEqualTo(storeId);

		List<StoreWxRef> list = this.queryAllObjByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}

		StoreWxRef ref = list.get(0);
		return this.accountConfigService.queryByOriginId(ref.getWxAccountOriginId());
	}

//	@Cacheable(value = "storeWxRef", key = "'StoreWxRefService_' + #root.methodName + '_' + #schemem + '_' + #serverName + '_'+ #serverPort +'_' + #requestUri")
//	@Cacheable(value = "storeWxRef", key = "'StoreWxRefService_' + #root.methodName + '_' + #schemem + '_' + #serverName + '_'+ #serverPort +'_' + #requestUri + + '_' + T(com.mcoding.base.utils.json.JsonUtilsForMcoding).writeValueAsString(#params)")
	@Override
	public String queryWechatOauthUrlForOpenId(String scheme, String serverName, int serverPort, String requestUri,
			Map<String, String> params, String ref) throws Exception {

		return queryWechatOauthUrlForOpenId(scheme, serverName, serverPort, requestUri, params, ref, false);
	}

//	@Cacheable(value = "storeWxRef", key = "'StoreWxRefService_' + #root.methodName + '_' + #schemem + '_' + #serverName + '_'+ #serverPort +'_' + #requestUri")
//	@Cacheable(value = "storeWxRef", key = "'StoreWxRefService_' + #root.methodName + '_' + #schemem + '_' + #serverName + '_'+ #serverPort +'_' + #requestUri + + '_' + T(com.mcoding.base.utils.json.JsonUtilsForMcoding).writeValueAsString(#params)")
	@Override
	public String queryWechatOauthUrlForWxUserInfo(String scheme, String serverName, int serverPort, String requestUri,
			Map<String, String> params, String ref) throws Exception {

		return this.queryWechatOauthUrlForWxUserInfo(scheme, serverName, serverPort, requestUri, params, ref, false);
	}

	@Override
	public boolean sendWxMpTemplateMessage(Integer storeId, String templateType, String receiverOpenid, String url,
			String keyword1, String keyword2, String keyword3, String keyword4, String keyword5) throws Exception {
		AccountConfig accountConfig = this.queryWxAccountByStoreId(storeId);
		if (accountConfig == null) {
			throw new NullPointerException("storeId["+storeId+"] 没有关联的微信公众号");
		}
		
		return WxMpTemplateMsgUtils.sendWxMpTemplateMessage(accountConfig, templateType, receiverOpenid, url, keyword1,
				keyword2, keyword3, keyword4, keyword5);

	}

	@Override
	public boolean sendWxMpTemplateMessage(Integer storeId, String templateType, String receiverOpenid, String first,
			String remark, String url, String color, String keyword1, String keyword2, String keyword3, String keyword4,
			String keyword5) throws Exception {
		AccountConfig accountConfig = this.queryWxAccountByStoreId(storeId);
		return WxMpTemplateMsgUtils.sendWxMpTemplateMessage(accountConfig, templateType, receiverOpenid, first, remark, url, color, keyword1, keyword2, keyword3, keyword4, keyword5);
	}

	@Cacheable(value = "storeWxRef", key = "'StoreWxRefService_' + #root.methodName + '_' + #storeId")
	@Override
	public String queryWxAppidByStoreId(Integer storeId) {
		AccountConfig accountConfig = this.queryWxAccountByStoreId(storeId);
		return accountConfig.getAppId();
	}

	@Cacheable(value = "storeWxRef", key = "'StoreWxRefService_' + #root.methodName + '_' + #storeId")
	@Override
	public String queryWxAppSecretByStoreId(Integer storeId) {
		AccountConfig accountConfig = this.queryWxAccountByStoreId(storeId);
		return accountConfig.getAppSecret();
	}

//	@Cacheable(value = "storeWxRef", key = "'StoreWxRefService_' + #root.methodName + '_' + #schemem + '_' + #serverName + '_'+ #serverPort +'_' + #requestUri + '_' + #isShortUrl + '_' + T(com.mcoding.base.utils.json.JsonUtilsForMcoding).writeValueAsString(#params)")
	@Override
	public String queryWechatOauthUrlForOpenId(String scheme, String serverName, int serverPort, String requestUri,
			Map<String, String> params, String ref, boolean isShortUrl) throws Exception {
//		Store store = this.storeService.queryStoreByRequest(scheme, serverName, serverPort, requestUri);
		
//		Store store = this.storeService.queryStoreByRequest(scheme, serverName, requestUri);
		
		StoreWxRefService storeWxRefService = SpringContextHolder.getBean("storeWxRefService");
		AccountConfig accountConfig = storeWxRefService.queryWxAccountByStoreId(StoreUtils.getStoreIdFromThreadLocal());

		String targetUrl = scheme + "://" + serverName + ":" + serverPort + requestUri;

		if (StringUtils.isNotBlank(ref)) {
			targetUrl = targetUrl + "#" + ref;
		}

		String oauthUrl = WechatOauthUtils.createOauthUrlForOpenid(targetUrl, params, accountConfig);
		
		if (isShortUrl) {
			WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(accountConfig);
			oauthUrl = wxMpService.shortUrl(oauthUrl);
		}
		
		return oauthUrl;
	}

//	@Cacheable(value = "storeWxRef", key = "'StoreWxRefService_' + #root.methodName + '_' + #schemem + '_' + #serverName + '_'+ #serverPort +'_' + #requestUri + '_' + #isShortUrl + '_' + T(com.mcoding.base.utils.json.JsonUtilsForMcoding).writeValueAsString(#params)")
	@Override
	public String queryWechatOauthUrlForWxUserInfo(String scheme, String serverName, int serverPort, String requestUri,
			Map<String, String> params, String ref, boolean isShortUrl) throws Exception {

//		Store store = this.storeService.queryStoreByRequest(scheme, serverName, serverPort, requestUri);
//		Store store = this.storeService.queryStoreByRequest(scheme, serverName, requestUri);
		
		StoreWxRefService storeWxRefService = SpringContextHolder.getBean("storeWxRefService");
		AccountConfig accountConfig = storeWxRefService.queryWxAccountByStoreId(StoreUtils.getStoreIdFromThreadLocal());

		String targetUrl = scheme + "://" + serverName + ":" + serverPort + requestUri;

		if (StringUtils.isNotBlank(ref)) {
			targetUrl = targetUrl + "#" + ref;
		}

		String oauthUrl = WechatOauthUtils.createOauthUrlForWxUserInfo(targetUrl, params, accountConfig);
		
		if (isShortUrl) {
			WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(accountConfig);
			oauthUrl = wxMpService.shortUrl(oauthUrl);
		}
		
		return oauthUrl;
	}

	/*@Override
	public WxJsapiSignature getJsapiSignature(String scheme, String serverName, int serverPort, String requestUri) throws WxErrorException {
		Store store = this.storeService.queryStoreByRequest(scheme, serverName, serverPort, requestUri);
		AccountConfig accountConfig = this.queryWxAccountByStoreId(store.getId());
		WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(accountConfig);
		
		String appName = this.getAppName(accountConfig.getDomain());
		if (StringUtils.isNotBlank(appName)) {
			requestUri = requestUri.replaceAll(appName + "\\\\*", "");
		}
		String url = accountConfig.getDomain() + requestUri;
		return wxMpService.createJsapiSignature(url);
	}*/

	@Override
	public WxMpService queryWxMpServiceByStoreId(Integer storeId) {
		StoreWxRefService storeWxRefService = SpringContextHolder.getBean("storeWxRefService");
		AccountConfig accountConfig = storeWxRefService.queryWxAccountByStoreId(storeId);
		WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(accountConfig);
		return wxMpService;
	}
	
	/*public static void main(String[] args) {
		ExpressionParser parser = new SpelExpressionParser(); 
		String expression2 = "T(com.mcoding.base.utils.json.JsonUtilsForMcoding).writeValueAsString()";  
		System.out.println(parser.parseExpression(expression2).getValue(String.class));
	}*/
}