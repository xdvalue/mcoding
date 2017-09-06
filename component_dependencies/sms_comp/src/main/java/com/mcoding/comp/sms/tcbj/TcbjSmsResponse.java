package com.mcoding.comp.sms.tcbj;

import java.util.Date;

import com.mcoding.comp.sms.SmsResponse;

import net.sf.json.JSONObject;

public class TcbjSmsResponse implements SmsResponse {

	private Date resptime;
	private String respstatus;
	private String msgid;
	private String respMsg;
	
	public TcbjSmsResponse(String json) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		this.respstatus = (String) jsonObject.get("errorCode");
		this.respMsg = (String) jsonObject.get("errorMessage");
		this.resptime = new Date();
	}

	public TcbjSmsResponse(Date resptime, String respstatus, String msgid, String respMsg) {
		super();
		this.resptime = resptime;
		this.respstatus = respstatus;
		this.msgid = msgid;
		this.respMsg = respMsg;
	}

	public Date getResptime() {
		return resptime;
	}

	public void setResptime(Date resptime) {
		this.resptime = resptime;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public String getRespstatus() {
		return respstatus;
	}

	public void setRespstatus(String respstatus) {
		this.respstatus = respstatus;
	}

}
