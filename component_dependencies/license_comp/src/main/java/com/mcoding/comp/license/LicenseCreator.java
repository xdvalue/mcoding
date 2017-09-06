package com.mcoding.comp.license;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;

import com.mcoding.comp.license.utils.Constant;
import com.mcoding.comp.license.utils.EncryptUtils;

public class LicenseCreator {

	public static void create(Date expireDate, OutputStreamWriter writer) throws IOException{
		
		Properties properties = EncryptUtils.getDevicePropertiesAfterEncrypt();
		String expireDateStr = null;
		
		if (expireDate !=null) {
			SimpleDateFormat format = new SimpleDateFormat(Constant.EXPIRED_DATE_FORMATE);
			expireDateStr = format.format(expireDate);
		}else{
			expireDateStr = Constant.NO_EXPIRED;
		}
		
		expireDateStr = Base64.encodeBase64String(expireDateStr.getBytes());
		properties.setProperty("signature", expireDateStr);
		
		properties.store(writer, "license");
	}
	
	/*public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\hzy\\Desktop\\dev.properties");
		if(!file.exists()){
			file.createNewFile();
		}
		
		FileWriter fileWriter = new FileWriter(file);
		create(DateUtils.addDays(new Date(), 2), fileWriter);
	}*/
}
