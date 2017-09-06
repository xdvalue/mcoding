package com.mcoding.comp.license.utils;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang.StringUtils;

public class EncryptUtils {
	
//	private static Logger logger = LoggerFactory.getLogger(EncryptUtils.class);

	public static Properties getDevicePropertiesAfterEncrypt() {

		Properties properties = new Properties();

		List<String> processors = DeviceUtils.getProcessorId();
		Collections.sort(processors);
		String cupInfo = StringUtils.join(processors, ";");
//		System.out.println("cpuInfo:" + cupInfo);
		cupInfo = Md5Crypt.md5Crypt(cupInfo.getBytes(), Constant.MD5_ENCRYPT_SALT);

		List<String> disks = DeviceUtils.getDiskSerialNumber();
		Collections.sort(disks);
		String diskInfo = StringUtils.join(disks, ";");
//		System.out.println("diskInfo:" + diskInfo);
		diskInfo = Md5Crypt.md5Crypt(diskInfo.getBytes(), Constant.MD5_ENCRYPT_SALT);

		List<String> macAddresses = DeviceUtils.getMacAddresses();
		Collections.sort(macAddresses);
		String macAddress = StringUtils.join(macAddresses, ";");
//		System.out.println("macAddress:" + macAddress);
		macAddress = Md5Crypt.md5Crypt(macAddress.getBytes(), Constant.MD5_ENCRYPT_SALT);
		
		properties.setProperty("cpuInfo", cupInfo);
		properties.setProperty("diskInfo", diskInfo);
		properties.setProperty("macAddress", macAddress);
		
		return properties;
	}

}
