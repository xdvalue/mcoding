package com.mcoding.comp.sms;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.mcoding.comp.sms.chuanglan.ChuanglanAccount;
import com.mcoding.comp.sms.utils.SmsSendException;
import com.mcoding.comp.sms.utils.Supplier;

/**
 * 短信管理器
 * @author hzy
 *
 */
public class SmsManager {
	
    private final static Map<SupplierAccount, SmsManager> storage = new Hashtable<>();
    private SupplierAccount account;
    
    public static SmsManager getInstance(SupplierAccount account){
    	SmsManager smsManager = storage.get(account);
		if (smsManager != null) {
			return smsManager;
		}
		
		synchronized (storage) {
			if(storage.get(account) == null){
				smsManager = new SmsManager(account);
				storage.put(account, smsManager);
			}
		}
		
		return smsManager;
    }
	
	public static SmsManager getInstance(Supplier supplier) {
		SupplierAccount account = SupplierAccountFactory.build(supplier);
		return getInstance(account);
	}
	
	private SmsManager(SupplierAccount account){
		this.account = account;
	}
	
	private void validMobileList(List<String> phoneList) {
		if (CollectionUtils.isEmpty(phoneList)) {
			throw new SmsSendException("电话号码不能为空");
		}

		for (String phone : phoneList) {
			if (StringUtils.isBlank(phone)) {
				throw new SmsSendException("电话号码不能为空");
			}
		}
	}

	private void validContent(String content) {
		if (StringUtils.isBlank(content)) {
			throw new SmsSendException("短信内容不能为空");
		}
	}

	/**
	 * 发送单条短信，不接受 状态报告
	 * 
	 * @param mobile
	 * @param msg
	 * @return
	 * @throws Exception 
	 */
	public SmsResponse sendSingleMsgWithoutReport(String phone, String content) throws Exception {
		this.validContent(content);

		List<String> phoneList = new ArrayList<>();
		phoneList.add(phone);
		this.validMobileList(phoneList);
		
		SmsMsg smsMsg = new SmsMsg(phoneList, content);

		SmsRequest smsRequest = this.createSmsRequest();
		return this.execute(smsRequest, smsMsg);
	}

	/**
	 * 群发短信， 不接受 状态报告
	 * 
	 * @param mobileList
	 * @param msg
	 * @return
	 * @throws Exception 
	 */
	public SmsResponse sendGroupMsg(List<String> phoneList, String content) throws Exception {
		this.validContent(content);
		this.validMobileList(phoneList);
		
		SmsMsg smsMsg = new SmsMsg(phoneList, content);

		SmsRequest smsRequest = this.createSmsRequest();
		return this.execute(smsRequest, smsMsg);
	}

	/**
	 * 发送单条短信，接受短信状态报告，短信状态异步的方式返回
	 * @param phone
	 * @param content
	 * @param handler
	 * @return
	 * @throws Exception 
	 */
	public SmsResponse sendSingleMsg(String phone, String content, SmsStatusReportHandler<SmsStatusReport> handler) throws Exception {
		this.validContent(content);

		List<String> phoneList = new ArrayList<>();
		phoneList.add(phone);
		this.validMobileList(phoneList);
		
		SmsMsg smsMsg = new SmsMsg(phoneList, content, handler);

		SmsRequest smsRequest = this.createSmsRequest();
		return this.execute(smsRequest, smsMsg);
	}

	/**
	 * 群发短信，接受短信状态报告，短信状态异步的方式返回
	 * @param phone
	 * @param content
	 * @param handler
	 * @return
	 * @throws Exception 
	 */
	public SmsResponse sendGroupMsg(List<String> phoneList, String content, SmsStatusReportHandler<SmsStatusReport> handler) throws Exception {
		this.validContent(content);
		this.validMobileList(phoneList);
		
		SmsMsg smsMsg = new SmsMsg(phoneList, content, handler);

		SmsRequest smsRequest = this.createSmsRequest();
		return this.execute(smsRequest, smsMsg);
	}
	
	private SmsRequest createSmsRequest(){
//		SmsRequestFactory.createRequest(this.account);
		return this.account.createRequest();
	}
	
	private SmsResponse execute(SmsRequest smsRequest, SmsMsg smsMsg) throws Exception{
		if(!smsRequest.beforeExecute()){
			return null;
		}
		
		SmsResponse smsResponse = smsRequest.execut(smsMsg);
		smsRequest.afterExecute(smsResponse);
		return smsResponse;
	}
	
	public static void main(String[] args) throws Exception {
//		SmsManager chuanglan = SmsManager.getInstance(Supplier.XI_DING);
//		chuanglan.sendSingleMsgWithoutReport("18928984438", "abc");
		ChuanglanAccount account = new ChuanglanAccount("sapi.253.com", 80, "/msg/", "vipgzxd_xd_banne", "FDsdf3@!er");
		SmsManager.getInstance(account).sendSingleMsgWithoutReport("18928984438", "Hello world!!");
		
//		ChuanglanAccount account = new ChuanglanAccount("222.73.117.156", 80, "/msg/HttpBatchSendSM", "vipgzxd_maiche", "maiche123Ab");
//		SmsManager.getInstance(account).sendSingleMsgWithoutReport("13580523189", "1231");
//		
	}
}
