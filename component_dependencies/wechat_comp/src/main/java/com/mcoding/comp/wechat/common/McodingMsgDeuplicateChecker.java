package com.mcoding.comp.wechat.common;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.chanjar.weixin.common.api.WxMessageDuplicateChecker;

/**
 * <p>信息排重检查，根据每条消息的msgId，进行信息排重。</p>
 * <p>原理是：每当接受一条消息，就根据msgId存储该消息的处理状态。状态有三种：处理中，已完成，有异常。
 * 当再次收到相同消息，检查上一次消息的状态，如果是在处理中，或者已完成处理，该消息则当做重复消息。每条消息存储的时间为10分钟，10分钟后自动清除。</p>
 * @author hzy
 *
 */
public class McodingMsgDeuplicateChecker implements WxMessageDuplicateChecker {
	
	private static Logger logger = LoggerFactory.getLogger(McodingMsgDeuplicateChecker.class);
	
	public static final String MSG_STATUS_PROCESS = "processing";
	public static final String MSG_STATUS_ERROR = "error";
	public static final String MSG_STATUS_FINISH = "finish";

	/**消息存储时间10分钟**/
	private static final long EXPIRED_TIME = 1 * 60 * 1000l;
	private boolean isVailable = true;
	
	private Map<String, String> msgAndStatus = new Hashtable<>();
	
	public McodingMsgDeuplicateChecker() {
		super();
		startClean();
	}

	@Override
	public boolean isDuplicate(String messageId) {
	    String status = this.getMsgStatus(messageId);
	    
	    if(StringUtils.isBlank(status) || MSG_STATUS_ERROR.equals(status)){
	    	return false;
	    }
	    
	    logger.warn("receive a repeat msg["+ messageId +"] ,status["+ status+"]");
	    return true;
	}
	
	public void setMsgStatus(String msgId, String status){
		if (StringUtils.isBlank(status) || 
				(!MSG_STATUS_ERROR.equals(status) && !MSG_STATUS_FINISH.equals(status) && !MSG_STATUS_PROCESS.equals(status))) {
			throw new IllegalArgumentException("msg status is illegal!!!");
		}
		
		long currentTime = System.currentTimeMillis();
		String timeAndStatus = status + "_" + currentTime;
		
		msgAndStatus.put(msgId, timeAndStatus);
	}
	
	protected String getMsgStatus(String msgId){
		String timeAndStatus = msgAndStatus.get(msgId);
		if (StringUtils.isBlank(timeAndStatus) || !timeAndStatus.matches("\\w+_\\d+")) {
			return null;
		}
		
		return timeAndStatus.split("_")[0];
	}
	
	protected Long getMsgTime(String msgId){
		String timeAndStatus = msgAndStatus.get(msgId);
		if (StringUtils.isBlank(timeAndStatus) || !timeAndStatus.matches("\\w+_\\d+")) {
			return null;
		}
		
		return Long.valueOf(timeAndStatus.split("_")[1]);
	}
	
	protected void cleanExpiredMsgId(){
		logger.debug("start clean wx msgAndStatusPool,size["+this.msgAndStatus.size()+"]");
		Set<String> keySet = new HashSet<>();
		CollectionUtils.addAll(keySet, this.msgAndStatus.keySet().iterator());
		Iterator<String> keyIterator = keySet.iterator();
		
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			Long time = this.getMsgTime(key);
			
			if (time == null || (System.currentTimeMillis() - time) > EXPIRED_TIME) {
				this.msgAndStatus.remove(key);
			}
		}
		logger.debug("finish clean wx msgAndStatusPool,size["+this.msgAndStatus.size()+"]");
	}
	
	public void stopClean(){
		this.isVailable = false;
	}
	
	private void startClean(){
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					while (isVailable) {
						McodingMsgDeuplicateChecker.this.cleanExpiredMsgId();
						Thread.sleep(EXPIRED_TIME);
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "Thread-McodingMsgDeuplicateChecker");
		thread.start();
	}
	
}
