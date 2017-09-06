package com.mcoding.comp.sms.chuanglan;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mcoding.base.core.CommonException;
import com.mcoding.comp.sms.SmsMsg;
import com.mcoding.comp.sms.SmsRequest;
import com.mcoding.comp.sms.SmsResponse;
import com.mcoding.comp.sms.utils.SmsConstanst;

public class ChuangLanRequest implements SmsRequest {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ChuanglanAccount account;

	public ChuangLanRequest(ChuanglanAccount chuanglanAccount) {
		this.account = chuanglanAccount;
	}

	@Override
	public SmsResponse execut(SmsMsg smsMsg) throws Exception {
		
		String mobileList = StringUtils.join(smsMsg.getPhoneList(), ",");

		boolean isNeedstatus = smsMsg.getHandler() == null;

		String url = "http://" + this.account.getServerHost() + this.account.getServerUrl();
		
		HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
		GetMethod method = new GetMethod();
		try {
			URI base = new URI(url, false);
			method.setURI(new URI(base, "HttpBatchSendSM", false));
			method.setQueryString(new NameValuePair[] { 
					new NameValuePair("account", this.account.getAccount()),
					new NameValuePair("pswd", this.account.getPswd()), 
					new NameValuePair("mobile", mobileList),
					new NameValuePair("needstatus", String.valueOf(isNeedstatus)), 
					new NameValuePair("msg", smsMsg.getContent()),
					new NameValuePair("extno", smsMsg.getExtno()), 
				});
			int result = client.executeMethod(method);
			if (result == HttpStatus.SC_OK) {
				InputStream in = method.getResponseBodyAsStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				
				
				ChuangLanResponse response = new ChuangLanResponse(URLDecoder.decode(baos.toString(), "UTF-8"));
				if (!SmsConstanst.SUCCESS_RESPONSE_CODE.equals(response.getRespstatus())) {
					throw new CommonException(response.getRespMsg(), response.getRespstatus());
				}
				
				return response;
//				return URLDecoder.decode(baos.toString(), "UTF-8");
			} else {
				throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
			}
		} finally {
			method.releaseConnection();
		}
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
		// TODO Auto-generated method stub
	}

}
