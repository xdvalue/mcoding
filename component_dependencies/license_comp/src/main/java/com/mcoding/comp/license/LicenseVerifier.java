package com.mcoding.comp.license;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mcoding.comp.license.utils.Constant;
import com.mcoding.comp.license.utils.EncryptUtils;

public class LicenseVerifier {
	
	private static Logger logger = LoggerFactory.getLogger(LicenseVerifier.class);
	
	public static boolean verify(InputStreamReader reader) throws IOException, ParseException{
		Properties properties = new Properties();
		properties.load(reader);
		
		Properties devProperties = EncryptUtils.getDevicePropertiesAfterEncrypt();
		
		if (properties.getProperty("diskInfo") ==null ||
				!properties.getProperty("diskInfo").equals(devProperties.getProperty("diskInfo"))) {
			logger.warn("diskinfo not verify: " + properties.getProperty("diskInfo") + "-" + devProperties.getProperty("diskInfo"));
			return false;
		}
		
		if (properties.getProperty("macAddress") ==null ||
				!properties.getProperty("macAddress").equals(devProperties.getProperty("macAddress"))) {
			logger.warn("macAddress not verify");
			return false;
		}
		
		if (properties.getProperty("cpuInfo") ==null ||
				!properties.getProperty("cpuInfo").equals(devProperties.getProperty("cpuInfo"))) {
			logger.warn("cpuInfo not verify");
			return false;
		}
		
		String expiredDateStr = properties.getProperty("signature");
		expiredDateStr = new String(Base64.decodeBase64(expiredDateStr)) ;
		
		if (expiredDateStr.equals(Constant.NO_EXPIRED)) {
			return true;
		}
		
		SimpleDateFormat formate = new SimpleDateFormat(Constant.EXPIRED_DATE_FORMATE);
		Date expiredDate = formate.parse(expiredDateStr);
		
		if (expiredDate.getTime() < new Date().getTime()) {
			logger.warn("expired date not verify");
			return false;
		}
		
		return true;
	}
	
//	public static void main(String[] args) throws IOException {
//		File file = new File("C:\\Users\\hzy\\Desktop\\dev.properties");
//		if(!file.exists()){
//			file.createNewFile();
//		}
//		
//		FileReader reader = new FileReader(file);
//		try {
//			boolean istrue = verify(reader);
//			System.out.println("istrue:" + istrue);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
