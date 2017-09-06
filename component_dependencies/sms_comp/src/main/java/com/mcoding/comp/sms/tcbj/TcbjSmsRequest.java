package com.mcoding.comp.sms.tcbj;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mcoding.comp.sms.SmsMsg;
import com.mcoding.comp.sms.SmsRequest;
import com.mcoding.comp.sms.SmsResponse;
import com.mcoding.comp.sms.utils.SmsConstanst;
import com.mcoding.comp.sms.utils.SmsSendException;

public class TcbjSmsRequest implements SmsRequest {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private TcbjAccount account;
	
	private String state = "0";
	private String smsCode;
	private String smsXml;
	private String sendType;
	private String bizType;
	private String sendDate;

	public TcbjSmsRequest(TcbjAccount tcbjAccount) {
		super();
		this.account =tcbjAccount;
		this.init();
	}

	private void init(){
		
		this.state="0";//信息状态，0待发或1已发
		this.smsCode="";//动态模板ID，由平台配置
		this.smsXml="";//动态模板内容参数，需指定格式字符串，有明确的分隔符，可以为XML或key|value模式
		this.sendType="1";//1、普通模式，2、动态模式
		this.bizType="";//业务类型，用于绑定通道0
		this.sendDate= DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");//定时发送日期时间格式：yyyy-mm-dd hh24:mi:ss
	}

	@Override
	public SmsResponse execut(SmsMsg smsMsg) throws Exception {
		List<String> failList = new ArrayList<>();
		
		List<String> phoneList = smsMsg.getPhoneList();
		for (int i = 0; i < phoneList.size(); i++) {
			String accessToken = this.getAccessToken();
			String phone = phoneList.get(i);
			if(!this.sendSms(accessToken, phone, smsMsg.getContent())){
				failList.add(phone);
			}
		}
		
		if (CollectionUtils.isEmpty(failList)) {
			TcbjSmsResponse response = new TcbjSmsResponse(new Date(), SmsConstanst.SUCCESS_RESPONSE_CODE, null, "ok");
			return response;
			
		}else{
			String failPhoneList = StringUtils.join(failList, ",");
			String respMsg = "以下号码发送失败，其余发送成功 ：" + failPhoneList;
			TcbjSmsResponse response = new TcbjSmsResponse(new Date(), "error", null, respMsg);
			return response;
		}
	}
	
	private boolean sendSms(String accessToken, String phone, String content){
		boolean isSuccess = false;
		
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod();
		try {
			URI tcbjServer = new URI(this.account.getHost()+":"+this.account.getPort(), false);
			
			method.setURI(new URI(tcbjServer, this.account.getApiUrl(), false));
			method.setQueryString(new NameValuePair[] { 
					new NameValuePair("access_token", accessToken),
					new NameValuePair("content", content),
					new NameValuePair("phone", phone),
					new NameValuePair("state", this.state),
					new NameValuePair("smsCode", this.smsCode),
					new NameValuePair("Sms_XML", this.smsXml),
					new NameValuePair("sendType", this.sendType),
					new NameValuePair("Username", this.account.getUsername()),
					new NameValuePair("Password", this.account.getPassword()),
					new NameValuePair("bizType", this.bizType),
					new NameValuePair("Send_dt", this.sendDate)
				});
			
			int result = client.executeMethod(method);
			if (result != HttpStatus.SC_OK) {
				throw new SmsSendException("GET SEND MSG FAIL. HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
			}
			
			String responseBody = this.getResponseBody(method);
			logger.info("send msg successed, get response from server:"+responseBody);
			TcbjSmsResponse response = new TcbjSmsResponse(responseBody);
			if (SmsConstanst.SUCCESS_RESPONSE_CODE.equals(response.getRespstatus())) {
				isSuccess = true;
			}
			
		} catch (Exception e) {
			logger.error("send msg failed ,reason :" + e.getMessage());
		} finally {
			method.releaseConnection();
		}
		
		return isSuccess;
	}
	
	private String getAccessToken() throws Exception{
		AccessToken accessToken = AccessTokenManager.getInstance().getToken();
		if(accessToken == null || 
				accessToken.getReturnObject()==null ||
				StringUtils.isBlank(accessToken.getReturnObject().getAccessToken())){
			
			throw new NullPointerException("failed get accesstoken from tcbj esb");
		}
		
		return accessToken.getReturnObject().getAccessToken();
	}
	
	private String getResponseBody(GetMethod method) throws IOException {
		InputStream in = method.getResponseBodyAsStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = in.read(buffer)) != -1) {
			baos.write(buffer, 0, len);
		}
		
		return baos.toString("UTF8");
	}

	@Override
	public boolean beforeExecute() throws Exception {
		return true;
	}

	@Override
	public void afterExecute(SmsResponse smsResponse) throws Exception {
	}
	
	
	

}
