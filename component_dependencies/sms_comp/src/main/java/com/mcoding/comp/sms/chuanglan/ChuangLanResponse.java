package com.mcoding.comp.sms.chuanglan;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.DateUtils;

import com.mcoding.comp.sms.SmsResponse;
import com.mcoding.comp.sms.utils.SmsConstanst;

public class ChuangLanResponse implements SmsResponse{
	
	private static final Pattern PATTERN = Pattern.compile("(\\d+)\\s*,\\s*(\\d+)(\\s+(\\d+))?", Pattern.DOTALL);
	
	private Date resptime;
	private String respstatus;
	private String msgid;
	private String respMsg;
	
	public ChuangLanResponse(String responseStr) {
		Matcher matcher = PATTERN.matcher(responseStr);
		if (matcher.find()) {
			String resptimeStr = matcher.group(1);
			try {
				this.resptime = DateUtils.parseDate(resptimeStr, new String[]{"yyyyMMddHHmmss"});
			} catch (ParseException e) {
				e.printStackTrace();
			}
			this.respstatus = matcher.group(2);
			this.msgid = matcher.group(3);
			this.setMsgForStatus(this.respstatus);
		}
	}
	
	private void setMsgForStatus(String respstatus){
		switch (respstatus) {
		case "0":   this.respMsg = "提交成功" ; this.respstatus = SmsConstanst.SUCCESS_RESPONSE_CODE; break;
		case "101":	this.respMsg = "无此用户" ; break;
		case "102":	this.respMsg = "密码错" ; break;
		case "103":	this.respMsg = "提交过快（提交速度超过流速限制）" ; break;
		case "104":	this.respMsg = "系统忙（因平台侧原因，暂时无法处理提交的短信）" ; break;
		case "105":	this.respMsg = "敏感短信（短信内容包含敏感词）" ; break;
		case "106":	this.respMsg = "消息长度错（>536或<=0）" ; break;
		case "107":	this.respMsg = "包含错误的手机号码" ; break;
		case "108":	this.respMsg = "手机号码个数错（群发>50000或<=0;单发>200或<=0）" ; break;
		case "109":	this.respMsg = "无发送额度（该用户可用短信数已使用完）" ; break;
		case "110":	this.respMsg = "不在发送时间内" ; break;
		case "111":	this.respMsg = "超出该账户当月发送额度限制" ; break;
		case "112":	this.respMsg = "无此产品，用户没有订购该产品" ; break;
		case "113":	this.respMsg = "extno格式错（非数字或者长度不对）" ; break;
		case "115":	this.respMsg = "自动审核驳回" ; break;
		case "116":	this.respMsg = "签名不合法，未带签名（用户必须带签名的前提下）" ; break;
		case "117":	this.respMsg = "IP地址认证错,请求调用的IP地址不是系统登记的IP地址" ; break;
		case "118":	this.respMsg = "用户没有相应的发送权限" ; break;
		case "119":	this.respMsg = "用户已过期" ; break;
		}
	}

	public Date getResptime() {
		return resptime;
	}

	public void setResptime(Date resptime) {
		this.resptime = resptime;
	}

	public String getRespstatus() {
		return respstatus;
	}

	public void setRespstatus(String respstatus) {
		this.respstatus = respstatus;
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

}
