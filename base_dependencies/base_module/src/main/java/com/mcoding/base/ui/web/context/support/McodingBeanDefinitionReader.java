package com.mcoding.base.ui.web.context.support;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.Assert;

public class McodingBeanDefinitionReader extends XmlBeanDefinitionReader {

	public McodingBeanDefinitionReader(BeanDefinitionRegistry registry) {
		super(registry);
	}

	@Override
	public int loadBeanDefinitions(String location, Set<Resource> actualResources) throws BeanDefinitionStoreException {

		ResourceLoader resourceLoader = getResourceLoader();
		if (resourceLoader == null) {
			throw new BeanDefinitionStoreException(
					"Cannot import bean definitions from location [" + location + "]: no ResourceLoader available");
		}

		if (resourceLoader instanceof ResourcePatternResolver) {
			// Resource pattern matching available.
			try {
				Resource[] resources = ((ResourcePatternResolver) resourceLoader).getResources(location);
				resources = this.filte(resources);
				resources = this.sort(resources);
				
				int loadCount = loadBeanDefinitions(resources);
				if (actualResources != null) {
					for (Resource resource : resources) {
						actualResources.add(resource);
					}
				}
				if (logger.isDebugEnabled()) {
					logger.debug("Loaded " + loadCount + " bean definitions from location pattern [" + location + "]");
				}
				return loadCount;
			} catch (IOException ex) {
				throw new BeanDefinitionStoreException(
						"Could not resolve bean definition resource pattern [" + location + "]", ex);
			}
		} else {
			// Can only load single resources by absolute URL.
			Resource resource = resourceLoader.getResource(location);
			int loadCount = loadBeanDefinitions(resource);
			if (actualResources != null) {
				actualResources.add(resource);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Loaded " + loadCount + " bean definitions from location [" + location + "]");
			}
			return loadCount;
		}
	}

	private Resource[] sort(Resource[] source) {
		Assert.notNull(source, "spring的配置文件不能为空");
		
		Resource first = null;
		int firstIndex = -1;
		for(int i=0; i<source.length; i++){
			if (source[i].getFilename().contains("spring-context.xml")) {
				first = source[i];
				firstIndex = i;
				break;
			}
		}
		
		if (first == null) {
			return source;
		}
		
		if (firstIndex == 0) {
			return source;
		}
		
		Resource tmp = source[0];
		source[0] = first;
		source[firstIndex] = tmp;
		return source;
	}

	/**
	 * 过滤多余重复的配置文件，同时根据配置过滤不用的文件
	 * @param source
	 * @return
	 */
	private Resource[] filte(Resource[] source)  {
		
		List<Resource> dubboServerConfList = new ArrayList<>();
		List<Resource> dubboClientConfList = new ArrayList<>();
		Resource redisSource = null;
		
		for(int i=0; i<source.length; i++){
			String fileName = source[i].getFilename();
			if (fileName.contains("spring-dubbo") && fileName.contains("provider")) {
				dubboServerConfList.add(source[i]);
			}
			if (fileName.contains("spring-dubbo") && fileName.contains("consumer")) {
				dubboClientConfList.add(source[i]);
			}
			if (fileName.contains("spring-redis")) {
				redisSource = source[i];
			}
		}
		
		try {
			source = this.removeRepeatResources(source);
			
			Properties properties = this.loadConfProperties();
			
			Boolean isDubboServer = Boolean.valueOf(properties.getProperty("dubbo.is.server", "false"));
			Boolean isDubboClient = Boolean.valueOf(properties.getProperty("dubbo.is.client", "false"));
			Boolean isRedisCacheEnable = Boolean.valueOf(properties.getProperty("cache.redis.is.enable", "true"));
			
			if (!isDubboServer) {
				for(int i=0; i<dubboServerConfList.size(); i++){
					source = (Resource[]) ArrayUtils.removeElement(source, dubboServerConfList.get(i));
				}
			}
			
			if (!isDubboClient) {
				for (int i = 0; i < dubboClientConfList.size(); i++) {
					source = (Resource[]) ArrayUtils.removeElement(source, dubboClientConfList.get(i));
				}
			}
			
			if (!isRedisCacheEnable && redisSource!=null) {
				source = (Resource[]) ArrayUtils.removeElement(source, redisSource);
			}
			
		} catch (IOException | URISyntaxException e) {
			logger.warn("过滤spring配置文件失败", e);
			return source;
		}
		
		return source;
	}
	
	private Properties loadConfProperties() throws FileNotFoundException, IOException, URISyntaxException{
		File confFile = null;
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		Enumeration<URL> resourceUrls = (cl != null ? cl.getResources("//conf.properties") : ClassLoader.getSystemResources("//conf.properties"));
		while (resourceUrls.hasMoreElements()) {
			URL url = resourceUrls.nextElement();
			confFile = new File(url.toURI());
		}
		
		if (confFile == null) {
			throw new NullPointerException("系统没有配置conf.properties文件");
		}
		
		Properties properties = new Properties();
		properties.load(new FileReader(confFile));
		
		return properties;
	}
	
	private Resource[] removeRepeatResources(Resource[] resources) throws IOException{
		List<String> namesOfRepeat = new ArrayList<>();
		
		List<String> namesOfNoneRepeat = new ArrayList<>();
		for(Resource resource: resources){
			String fileName = resource.getFilename();
			
			if (!namesOfNoneRepeat.contains(fileName)) {
				namesOfNoneRepeat.add(resource.getFilename());
			}else {
				namesOfRepeat.add(fileName);
			}
		}
		
		if (CollectionUtils.isEmpty(namesOfRepeat)) {
			return resources;
		}
		
		List<Resource> repeatResources = new ArrayList<>();
		for(String fileName: namesOfRepeat){
			
			for(Resource resource : resources){
				if (fileName.equals(resource.getFilename()) 
						&& resource.getURI().toString().contains(".jar!")) {
					repeatResources.add(resource);
				}
			}
		}
		
		for(Resource resource : repeatResources){
			resources = (Resource[]) ArrayUtils.removeElement(resources, resource);
		}
		return resources;
	}
	

}
