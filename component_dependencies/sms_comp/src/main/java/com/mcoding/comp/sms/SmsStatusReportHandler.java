package com.mcoding.comp.sms;


public interface SmsStatusReportHandler<T extends SmsStatusReport> {
	
	public void handle(T smsStatusReport);

}
