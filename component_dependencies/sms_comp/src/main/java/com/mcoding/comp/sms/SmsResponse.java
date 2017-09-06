package com.mcoding.comp.sms;

import java.util.Date;

/**
 * 发送相应，respstatus 为00则正常
 * @author hzy
 *
 */
public interface SmsResponse {

	/**响应事件**/
	public Date getResptime();

	/**
	 * 发送短信的返回码
	 * @return
	 */
	public String getRespstatus();

	/**发送的短信id**/
	public String getMsgid();

	/**发送后，返回的信息**/
	public String getRespMsg();

}
