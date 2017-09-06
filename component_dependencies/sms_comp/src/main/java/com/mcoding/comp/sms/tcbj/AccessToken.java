package com.mcoding.comp.sms.tcbj;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;

/**
 * esb返回的access token有效期1分钟,格式如下：<br/>
{
	"errorCode": "00",
	"errorMessage": null,
	"returnObject": {
		"access_token": "caa21c850f024afeae4b3597ec54904b9216016e97a840c68da6f7a7da82cd94",
		"expiredTime": "2016-03-30 13:35:20"
	}
}
 * @author hzy
 *
 */
public class AccessToken {

	public final static String SUCCESS_CODE = "00";

	@JsonProperty("errorCode")
	private String errorCode;

	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonProperty("returnObject")
	private ReturnObject returnObject;
	
	private Date expiredTime;

	static class ReturnObject {

		@JsonProperty("access_token")
		private String accessToken;
		private String expiredTime;

		public String getAccessToken() {
			return accessToken;
		}

		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}

		public String getExpiredTime() {
			return expiredTime;
		}

		public void setExpiredTime(String expiredTime) {
			this.expiredTime = expiredTime;
		}

	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ReturnObject getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(ReturnObject returnObject) {
		this.returnObject = returnObject;
	}

	public Date getExpiredTime() {
		if(this.returnObject != null && StringUtils.isNotBlank(returnObject.getExpiredTime())){
			try {
				expiredTime = DateUtils.parseDate(returnObject.getExpiredTime(), new String[] {"yyyy-MM-dd HH:mm:ss"});
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		String json = "{\"errorCode\":\"00\",\"errorMessage\":null,\"returnObject\":{\"access_token\":\"3f9393bdd77b44cb8a7da43b8ffbda7d0417e455b9da41bb886f171be8c2e8b9\",\"expiredTime\":\"2016-03-29 11:15:57\"}}";
		AccessToken accessToken = JsonUtilsForMcoding.convertValue(json, AccessToken.class);
//		ObjectMapper objectMapper = new ObjectMapper();
//		AccessToken accessToken = objectMapper.readValue(json, AccessToken.class);
		System.out.println(accessToken.getReturnObject().getAccessToken());
	}
}
