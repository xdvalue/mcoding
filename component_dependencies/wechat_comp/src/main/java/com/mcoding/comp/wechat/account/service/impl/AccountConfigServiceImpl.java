package com.mcoding.comp.wechat.account.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.bean.AccountConfigExample;
import com.mcoding.comp.wechat.account.persistence.AccountConfigMapper;
import com.mcoding.comp.wechat.account.service.AccountConfigService;

@Service
public class AccountConfigServiceImpl implements AccountConfigService {
	
	private static Logger logger = LoggerFactory.getLogger(AccountConfigServiceImpl.class);
	
	@Resource
	public AccountConfigMapper accountConfigMapper;

	@CacheEvict(value="accountConfigCache", allEntries=true)
	@Override
	public void addObj(AccountConfig t) {
		this.accountConfigMapper.insertSelective(t);
	}

	@CacheEvict(value="accountConfigCache", allEntries=true)
	@Override
	public void deleteObjById(int id) {
		this.accountConfigMapper.deleteByPrimaryKey(id);
	}

	@CacheEvict(value="accountConfigCache", allEntries=true)
	@Override
	public void modifyObj(AccountConfig t) {
		if (t.getId() == null) {
			throw new NullPointerException("id为空，无法更新");
		}
		this.accountConfigMapper.updateByPrimaryKeySelective(t);
	}

	@Cacheable(value="accountConfigCache", key="'AccountConfigService_' +#root.methodName + '_' + #id")
	@Override
	public AccountConfig queryObjById(int id) {
		return this.accountConfigMapper.selectByPrimaryKey(id);
	}

	@Cacheable(value="accountConfigCache", key="'AccountConfigService_' +#root.methodName + '_' + #example.toJson()")
	@Override
	public List<AccountConfig> queryAllObjByExample(AccountConfigExample example) {
		return this.accountConfigMapper.selectByExample(example);
	}

	@Cacheable(value="accountConfigCache", key="'AccountConfigService_' +#root.methodName + '_' + #example.toJson()")
	@Override
	public PageView<AccountConfig> queryObjByPage(AccountConfigExample example) {
		PageView<AccountConfig> pageView = example.getPageView();
		if (pageView == null) {
			pageView = new PageView<>(1, 10);
		}
		
		pageView.setQueryResult(this.accountConfigMapper.selectByExampleByPage(example));
		return pageView;
	}

	@Cacheable(value="accountConfigCache", key="'AccountConfigService_' +#root.methodName + '_' + #schemem + '_' + #serverName + '_'+ #port +'_' + #requestUri")
	@Override
	public AccountConfig queryByRequest(String schemem, String serverName, Integer port,  String requestUri) {
		
		String domain = schemem + "://" + serverName;
		
		AccountConfigExample example = new AccountConfigExample();
		example.createCriteria().andDomainLike(domain + "%");
		
		List<AccountConfig> list = this.accountConfigMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		
		String appName = null;
		int endIndex = requestUri.indexOf("/", 1);
		if(endIndex < 0){
			appName = requestUri;
		}else{
			appName = requestUri.substring(1, endIndex);
		}
		
		AccountConfig defaultAc = null;
		AccountConfig accountConfig = null;
		
		Pattern pattern = Pattern.compile("http:\\/\\/([\\w\\.]+)(:(\\d+))*(\\/(\\w+)*)?");
		for(int i=0; i<list.size(); i++){
			Matcher matcher = pattern.matcher(list.get(i).getDomain());
			if (!matcher.find()) {
				continue;
			}
			
			String domainInConfig = matcher.group(1);
			String appNameInConfig = matcher.group(5);
			
			if (StringUtils.isBlank(domainInConfig)) {
				logger.warn("account["+list.get(i).getName()+"] config error, no domain config");
				continue;
			}
			
			if (!domainInConfig.equals(serverName)) {
				continue;
			}
			
			if(StringUtils.isNotBlank(appNameInConfig) && !appNameInConfig.equals(appName)){
				continue;
			}
			
			if (StringUtils.isBlank(appNameInConfig)) {
				defaultAc = list.get(i);
			
			}else if (appNameInConfig.equals(appName)){
				accountConfig = list.get(i);
				return accountConfig;
			}
		}
		
		return defaultAc;
	}

	@Cacheable(value="accountConfigCache", key="'AccountConfigService_' +#root.methodName + '_' + #request.getScheme() + '_' + #request.getServerName() + '_'+ #request.getServerPort() +'_' + #request.getRequestURI()")
	@Override
	public AccountConfig queryByRequest(HttpServletRequest request) {
		return this.queryByRequest(request.getScheme(), request.getServerName(), request.getServerPort(), request.getRequestURI());
	}

	@Cacheable(value="accountConfigCache", key="'AccountConfigService_' +#root.methodName + '_' + #originId")
	@Override
	public AccountConfig queryByOriginId(String originId) {
		AccountConfigExample example = new AccountConfigExample();
		example.createCriteria().andOriginIdEqualTo(originId);
		
		List<AccountConfig> list = this.queryAllObjByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		
		if (list.size() > 1) {
			throw new IllegalArgumentException("微信公众号配置异常，code不唯一，配置有相同的code");
		}
		
		return list.get(0);
	}

}
