package com.mcoding.comp.sms;

public interface SmsRequest {

	public SmsResponse execut(SmsMsg smsMsg) throws Exception;
	
	public boolean beforeExecute() throws Exception;
	
	public void afterExecute(SmsResponse smsResponse) throws Exception;

}
