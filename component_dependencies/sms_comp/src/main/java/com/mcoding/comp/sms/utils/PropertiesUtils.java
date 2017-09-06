package com.mcoding.comp.sms.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.mcoding.base.core.CommonException;

public class PropertiesUtils {
	
	public static String getProperty(String key){
		return getStaticPropertyBean().getProperty(key);
	}
	
	public static Properties getStaticPropertyBean(){
//		WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
//		Properties smsConfigProperties = (Properties) applicationContext.getBean("smsConfigProperties");
//		return smsConfigProperties;
		
		Properties smsConfigProperties = new Properties();
		try {
			InputStream input= PropertiesUtils.class.getClassLoader().getResourceAsStream("sms.tcbj.properties");
			InputStreamReader reader = new InputStreamReader(input);
			
			if (!reader.ready()) {
				reader = new InputStreamReader(PropertiesUtils.class.getClassLoader().getResourceAsStream("/sms.tcbj.properties"));
				if (reader == null) {
					throw new CommonException("无法读取配置文件sms.tcbj.properties");
				}
			}
			smsConfigProperties.load(reader);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return smsConfigProperties;
	}

}
