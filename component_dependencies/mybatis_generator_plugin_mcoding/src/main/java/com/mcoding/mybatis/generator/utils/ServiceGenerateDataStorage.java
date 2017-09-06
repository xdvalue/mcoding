package com.mcoding.mybatis.generator.utils;

import java.util.Hashtable;
import java.util.Map;

public class ServiceGenerateDataStorage {
	
	private static final ServiceGenerateDataStorage instance = new ServiceGenerateDataStorage();
	private static final Map<String, String> storage = new Hashtable<>();
	
	private ServiceGenerateDataStorage(){
		super();
	}
	
	public static ServiceGenerateDataStorage getInstance(){
		return instance;
	}
	
	public String getServicePackage(String tableName){
		return storage.get(tableName);
	}
	
	public void setServicePackage(String tableName, String packageStr){
		storage.put(tableName, packageStr); 
	}
	

}
