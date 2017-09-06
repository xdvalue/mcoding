package com.mcoding.comp.license;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.mcoding.comp.license.utils.Constant;

public class LicenseManager {

	private static LicenseManager manager = new LicenseManager();

	private LicenseManager() {
		super();
	}

	public static LicenseManager getInstance() {
		return manager;
	}

	public void create(int months, File licensePath) throws IOException {
		File saveFile = null;
		
		if (licensePath == null) {
			String filePath  = new File("").getAbsolutePath();
			licensePath = new File(filePath);
		}

		if (!licensePath.exists()) {
			
			licensePath.mkdirs();
			saveFile = new File(licensePath, Constant.DEFAULT_FILE_NAME);
			saveFile.createNewFile();
			
		} else {
			if (licensePath.isDirectory()) {
				saveFile = new File(licensePath, Constant.DEFAULT_FILE_NAME);
			} else {
				saveFile = licensePath;
			}
		}

		Date expireDate = null;
		if (months >0) {
			expireDate = DateUtils.addMonths(new Date(), months);
		}

		FileWriter writer = new FileWriter(saveFile);
		LicenseCreator.create(expireDate, writer);
	}

	public boolean verify(File licensePath) throws IOException, ParseException {
		File saveFile = null;

		if (!licensePath.exists()) {
			throw new NullPointerException("没有license授权文件");
			
		} else {
			if (licensePath.isDirectory()) {
				saveFile = new File(licensePath, Constant.DEFAULT_FILE_NAME);
			} else {
				saveFile = licensePath;
			}
		}
		
		FileReader reader = new FileReader(saveFile);
		return LicenseVerifier.verify(reader);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);   
        
        try {
        	System.out.println("请输入一个整数，作为license的有效期(月)，不限有效期则输入0。\n\r\n\r有效期:");  
            String monthsStr = sc.next();
            if (StringUtils.isBlank(monthsStr) || !StringUtils.isNumeric(monthsStr)) {
				throw new IllegalArgumentException("输入的不是整数");
			}
        	
            int months = Integer.valueOf(monthsStr);
            
            System.out.println("license 正在生成中.............");
			LicenseManager.getInstance().create(months, null);
			System.out.println("license 生成成功，文件名 license.properties。");
			sc.hasNext();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("license 生成失败，输入任意字符退出。" + e.getMessage());
			sc.hasNext();
		} finally {
			sc.close();
		}
	}

}
