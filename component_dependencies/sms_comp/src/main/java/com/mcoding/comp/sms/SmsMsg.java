package com.mcoding.comp.sms;

import java.io.Serializable;
import java.util.List;

public class SmsMsg implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<String> phoneList;
	
	private String content;
	
	private String msgId;
	
	private SmsStatusReportHandler handler ;
	
	private String extno;
	
	public SmsMsg(List<String> phoneList, String content) {
		this(phoneList, content, null);
	}
	
	public SmsMsg(List<String> phoneList, String content, SmsStatusReportHandler handler) {
		super();
		this.phoneList = phoneList;
		this.content = content;
		this.handler = handler;
	}

	public List<String> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<String> phoneList) {
		this.phoneList = phoneList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public SmsStatusReportHandler getHandler() {
		return handler;
	}

	public void setHandler(SmsStatusReportHandler handler) {
		this.handler = handler;
	}

	public String getExtno() {
		return extno;
	}

	public void setExtno(String extno) {
		this.extno = extno;
	}

}
