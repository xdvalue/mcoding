package com.mcoding.comp.wechat.account.utils;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.lang.StringUtils;

public class WechatTokenOrTicketManager {
	
	private static final String KEY_ACCESS_TOKEN = "AC";
	private static final String KEY_ACCESS_TOKEN_EXPIRES_TIME = "ACET";
	private static final String KEY_JSAPI_TICKET = "JT";
	private static final String KEY_JSAPI_TICKET_EXPIRES_TIME = "JTET";
	private static final String KEY_CARD_TICKET = "CT";
	private static final String KEY_CARD_TICKET_EXPIRES_TIME = "CTET";
	
	private static WechatTokenOrTicketManager manager = new WechatTokenOrTicketManager();
	
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	
	private Map<String, Map<String, String>> tokenAccountMap = new Hashtable<>();
	
	private WechatTokenOrTicketManager(){
		super();
	}
	
	public static WechatTokenOrTicketManager getInstance(){
		return manager;
	}
	
	private Map<String, String> getDataForAccount(int accountId){
		if (accountId <= 0) {
			throw new IllegalArgumentException("accountId can not be null, or less than 0");
		}
		Map<String, String> map = tokenAccountMap.get(String.valueOf(accountId));
		if (map == null) {
			lock.writeLock().lock();
			
			map = tokenAccountMap.get(String.valueOf(accountId));
			if (map == null) {
				map = new Hashtable<>();
			}
			tokenAccountMap.put(String.valueOf(accountId), map);
			
			lock.writeLock().unlock();
		}
		
		return map;
	}
	
	private void writeValue(int accountId, Map<String, String> keyAndValues){
		Map<String, String> map = this.getDataForAccount(accountId);
		
		lock.writeLock().lock();
		Iterator<String> iterator = keyAndValues.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			map.put(key, keyAndValues.get(key));
		}
		lock.writeLock().unlock();
	}
	
	private String readValue(int accountId, String key){
		Map<String, String> map = this.getDataForAccount(accountId);
		
		lock.readLock().lock();
		String value = map.get(key);
		lock.readLock().unlock();
		
		return value;
	}
	
	public String getAccessToken(int accountId){
		return readValue(accountId, KEY_ACCESS_TOKEN);
	}
	
	public long getAccessTokenExpiresTime(int accountId){
		String accessTokenExpiresTime = StringUtils.defaultIfBlank(readValue(accountId, KEY_ACCESS_TOKEN_EXPIRES_TIME), "0");
		return Long.valueOf(accessTokenExpiresTime);
	}
	
	public void expireAccessToken(int accountId){
		Map<String, String> tmp = new HashMap<>();
		tmp.put(KEY_ACCESS_TOKEN_EXPIRES_TIME, "0");
		writeValue(accountId, tmp);
	}
	
	public void updateAccessToken(int accountId, String accessToken, long expiresIn){
		Map<String, String> tmp = new HashMap<>();
		tmp.put(KEY_ACCESS_TOKEN_EXPIRES_TIME, String.valueOf(expiresIn));
		tmp.put(KEY_ACCESS_TOKEN, accessToken);
		writeValue(accountId, tmp);
	}
	
	public String getJsapiTicket(int accountId){
		return readValue(accountId, KEY_JSAPI_TICKET);
	}
	
	public long getJsapiTicketExpiresTime(int accountId){
		String jsapiTicketExpiresTime = StringUtils.defaultIfBlank(readValue(accountId, KEY_JSAPI_TICKET_EXPIRES_TIME), "0") ;
		return Long.valueOf(jsapiTicketExpiresTime);
	}
	
	public void expireJsapiTicket(int accountId){
		Map<String, String> tmp = new HashMap<>();
		tmp.put(KEY_JSAPI_TICKET_EXPIRES_TIME, "0");
		writeValue(accountId, tmp);
	}
	
	public void updateJsapiTicket(int accountId, String jsapiTicket, long expiresIn){
		Map<String, String> tmp = new HashMap<>();
		tmp.put(KEY_JSAPI_TICKET_EXPIRES_TIME, String.valueOf(expiresIn));
		tmp.put(KEY_JSAPI_TICKET, jsapiTicket);
		writeValue(accountId, tmp);
	}
	
	public String getCardApiTicket(int accountId){
		return readValue(accountId, KEY_CARD_TICKET);
	}
	
	public long getCardApiTicketExpiresTime(int accountId){
		String cardApiExpiresTime = StringUtils.defaultIfBlank(readValue(accountId, KEY_CARD_TICKET_EXPIRES_TIME), "0") ;
		return Long.valueOf(cardApiExpiresTime);
	}
	
	public void expireCardApiTicket(int accountId){
		Map<String, String> tmp = new HashMap<>();
		tmp.put(KEY_CARD_TICKET_EXPIRES_TIME, "0");
		writeValue(accountId, tmp);
	}
	
	public void updateCardApiTicket(int accountId, String cardApiTicket, long expiresIn){
		Map<String, String> tmp = new HashMap<>();
		tmp.put(KEY_CARD_TICKET_EXPIRES_TIME, String.valueOf(expiresIn));
		tmp.put(KEY_CARD_TICKET, cardApiTicket);
		writeValue(accountId, tmp);
	}
	
	

}
