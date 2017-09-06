package com.mcoding.base.ui.service.store.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.bean.store.StoreExample;
import com.mcoding.base.ui.bean.store.StoreExample.Criteria;
import com.mcoding.base.ui.persistence.store.StoreMapper;
import com.mcoding.base.ui.service.store.StoreService;

@Service
public class StoreServiceImpl implements StoreService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static Pattern pattern = Pattern.compile("http:\\/\\/([\\w\\.]+)(:(\\d+))*(\\/(\\w+)*)?");
	
	@Resource
	protected StoreMapper storeMapper;

	@CacheEvict(value={"storeCache"}, allEntries=true)
	@Override
	public void addObj(Store t) {
		this.storeMapper.insertSelective(t);
	}

	@CacheEvict(value={"storeCache"}, allEntries=true)
	@Override
	public void deleteObjById(int id) {
		this.storeMapper.deleteByPrimaryKey(id);
	}

	@CacheEvict(value={"storeCache"}, allEntries=true)
	@Override
	public void modifyObj(Store t) {
		this.storeMapper.updateByPrimaryKeySelective(t);
	}

	@Cacheable(value="storeCache", key="'StoreService_' + #root.methodName + '_' +#id")
	@Override
	public Store queryObjById(int id) {
		return this.storeMapper.selectByPrimaryKey(id);
	}

	@Cacheable(value="storeCache", key="'StoreService_' + #root.methodName + '_'+ #example.toJson()")
	@Override
	public List<Store> queryAllObjByExample(StoreExample example) {
		return this.storeMapper.selectByExample(example);
	}

	@Cacheable(value="storeCache", key="'StoreService_' + #root.methodName + '_'+ #example.toJson()")
	@Override
	public PageView<Store> queryObjByPage(StoreExample example) {
		PageView<Store> pageView = example.getPageView();
		if (pageView == null) {
			pageView = new PageView<>(1, 10);
			example.setPageView(pageView);
		}
		pageView.setQueryResult(this.storeMapper.selectByExampleByPage(example));
		return pageView;
	}
	
	/*@Cacheable(value="storeCache", key="'StoreService_' +#root.methodName + '_' + #schemem + '_' + #serverName + '_'+ #serverPort +'_' + #requestUri")
	@Override
	public Store queryStoreByRequest(String schemem, String serverName, int serverPort, String requestUri) {
		StoreExample storeExample = new StoreExample();
		Criteria cri = storeExample.createCriteria();
		
		String domain = schemem + "://" + serverName + ":" + serverPort;
		cri.andStoreDomainLike("%" +domain + "%");
		
		if (serverPort == 80) {
			String domainWithoutPort = schemem + "://" + serverName;
			storeExample.or().andStoreDomainLike("%"+domainWithoutPort + "%");
		}
		storeExample.setOrderByClause("store_domain desc");
		
		List<Store> storeList = this.queryAllObjByExample(storeExample);
		if (CollectionUtils.isEmpty(storeList)) {
			return null;
		}
		
		
		Store store = null;
		for(int i=0; i<storeList.size(); i++){
			String domainConfig = storeList.get(i).getStoreDomain();
			String[] domainArray = domainConfig.split(";");
			
			boolean isMatch = false;
			for(int j=0; domainArray!=null && j<domainArray.length; j++){
				isMatch = this.isMatch(schemem, serverName, serverPort, requestUri, domainArray[j]);
				if (isMatch) {
					store = storeList.get(i);
					String storeName = store == null ? null :store.getStoreName();
					logger.debug("domain:"+domain+"，match store name :" +storeName);
					return store;
				}
			}
		}
		return store;
	}*/
	
	/*public boolean isMatch(String schemem, String serverName, int serverPort, String requestUri, String storeConfigUrl){
		String appName = this.geteAppNameFromUri(requestUri);
		
		Matcher matcher = pattern.matcher(storeConfigUrl);
		if (!matcher.find()) {
			return false;
		}
		
		String domainInConfig = matcher.group(1);
		String portInConfig = matcher.group(3);
		String appNameInConfig = matcher.group(5);
		
		if (StringUtils.isNotBlank(appNameInConfig) && !appNameInConfig.equals(appName)) {
			return false;
		}
		
		if (StringUtils.isNotBlank(portInConfig) && !portInConfig.equals(String.valueOf(serverPort))) {
			return false;
		}
		
		if (StringUtils.isBlank(portInConfig) && serverPort != 80) {
			return false;
		}
		
		if (!domainInConfig.equals(serverName)) {
			return false;
		}
		
		if ((domainInConfig.equals(serverName) && appNameInConfig.equals(appName) && String.valueOf(serverPort).equals(portInConfig))
				|| (domainInConfig.equals(serverName) && appNameInConfig.equals(appName) && serverPort == 80 && StringUtils.isBlank(portInConfig))){
			isMatch = true;
		}

		return true;
	}*/
	
	@Cacheable(value="storeCache", key="'StoreService_' +#root.methodName + '_' + #schemem + '_' + #serverName +'_' + #requestUri")
	@Override
	public Store queryStoreByRequest(String schemem, String serverName, String requestUri) {
		StoreExample storeExample = new StoreExample();
		Criteria cri = storeExample.createCriteria();
		
		String domainLike = "%"+ schemem + "://" + serverName +"%";
		cri.andStoreDomainLike(domainLike);
		
		storeExample.setOrderByClause("store_domain desc");
		
		List<Store> storeList = this.queryAllObjByExample(storeExample);
		if (CollectionUtils.isEmpty(storeList)) {
			return null;
		}
		
		Store store = null;
		for(int i=0; i<storeList.size(); i++){
			String domainConfig = storeList.get(i).getStoreDomain();
			String[] domainArray = domainConfig.split(";");
			
			boolean isMatch = false;
			for(int j=0; domainArray!=null && j<domainArray.length; j++){
				isMatch = this.isMatch(schemem, serverName, requestUri, domainArray[j]);
				if (isMatch) {
					store = storeList.get(i);
					String storeName = store == null ? null :store.getStoreName();
					logger.debug("request:"+schemem+"://"+serverName+"/"+requestUri+", match store name :" +storeName);
					return store;
				}
			}
		}
		return store;
	}
	
	public boolean isMatch(String schemem, String serverName, String requestUri, String storeConfigUrl){
		
		Matcher matcher = pattern.matcher(storeConfigUrl);
		if (!matcher.find()) {
			return false;
		}
		
		String domainInConfig = matcher.group(1);
		String appNameInConfig = matcher.group(5);
		
		String appName = this.geteAppNameFromUri(requestUri);
		if (StringUtils.isNotBlank(appNameInConfig) && !appNameInConfig.equals(appName)) {
			return false;
		}
		
		if (!domainInConfig.equals(serverName)) {
			return false;
		}
		
		return true;
	}
	
	private String geteAppNameFromUri(String requestUri){
        String appName = null;
		
		int endIndex = requestUri.indexOf("/", 1);
		if(endIndex < 0){
			appName = requestUri;
		}else{
			appName = requestUri.substring(1, endIndex);
		}
		
		return appName;
	}

}
