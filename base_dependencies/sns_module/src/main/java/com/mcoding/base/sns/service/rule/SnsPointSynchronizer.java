package com.mcoding.base.sns.service.rule;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.point.bean.MemberPointData;
import com.mcoding.base.point.service.level.MemberLevelService;

import net.sf.json.JSONObject;

/**
 * 积分同步处理器，把微社区的积分同步到EMIS
 * @author hzy
 *
 */
@Component
public class SnsPointSynchronizer {
private static Logger logger = LoggerFactory.getLogger(SnsPointSynchronizer.class);
	
    @Resource
    protected MemberLevelService memberLevelService;
	
	private final static String server = "http://127.0.0.1:80/";
	private final static String apiUrl = "merriplusApi/addMemberPoint";
	private final static String brandCode = "JLD";
	
	public void addPoint(MemberPointData pointData){
		this.memberLevelService.addPoint(pointData);
		this.syncToWMall(pointData);
	}
	
	/**
	 * 同步积分到微商城
	 * @param memberId
	 * @param point
	 * @param pointSourceName
	 */
	public void syncToWMall(final MemberPointData pointData){
		Thread thread = new Thread( new Runnable() {
			public void run() {
				SnsPointSynchronizer.this.getMemberInfoAndSend(pointData.getMember(), pointData.getPoint(), pointData.getPointType().getSourceName());
			}
		});
		
		thread.start();
	}
	
	protected void getMemberInfoAndSend(Member member, int point, String pointSourceName) {
		if (StringUtils.isBlank(pointSourceName)) {
			pointSourceName = "微社区积分";
		}
		
//		Member member =  this.memberService.queryObjById(memberId);
		int memberId = member.getId();
		String openId = null;
		if(member.getWxMember() == null || StringUtils.isBlank(member.getWxMember().getWxOpenid())){
			logger.error("member id["+memberId+"] , have not wx member info,can not get openid");
			throw new NullPointerException("member id["+memberId+"] , have not wx member info,can not get openid");
		}
		openId = member.getWxMember().getWxOpenid();
		int tryTime = 0;
		boolean isSuccess = false;
		while (!isSuccess && tryTime <3) {
			//失败后重发，最多重发3次
			try {
				isSuccess = this.sendRequest(openId, brandCode, point, pointSourceName);
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			tryTime ++;
			logger.info("send requet for sync member point to Wmall, time["+tryTime+"], result["+isSuccess+"]");
		}
		
	}
	
	protected boolean sendRequest(String openid, String brandCode, int point, String pointSourceName) throws NullPointerException, HttpException, IOException{
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod();
			URI tcbjServer = new URI(server,false);
			
			method.setURI(new URI(tcbjServer, apiUrl, false));
			method.setQueryString(new NameValuePair[] { 
					new NameValuePair("brandCode", brandCode),
					new NameValuePair("openId", openid),
					new NameValuePair("point", String.valueOf(point)),
					new NameValuePair("pointType", pointSourceName)
				});
			
			int result = client.executeMethod(method);
			if(result != HttpStatus.SC_OK){
				return false;
			}
			
			String responsebody = this.getResponseBody(method);
			JSONObject jsonObject = JSONObject.fromObject(responsebody);
			String status = jsonObject.getString("status");
			if ("00".equals(status)) {
				return true;
			}else{
				return false;
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
}
