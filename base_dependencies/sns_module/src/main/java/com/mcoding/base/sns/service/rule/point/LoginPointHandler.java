package com.mcoding.base.sns.service.rule.point;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.service.wechat.WechatLoginInterceptor;
import com.mcoding.base.point.bean.MemberPointData;
import com.mcoding.base.point.bean.pointrecord.MemberPointRecordExample;
import com.mcoding.base.point.service.MemberLeveLRule;
import com.mcoding.base.point.service.pointrecord.MemberPointRecordService;
import com.mcoding.base.sns.service.rule.SnsPointSynchronizer;
import com.mcoding.base.sns.utils.SnsPointType;
import com.mcoding.base.ui.utils.StoreUtils;

/***
 * 微信登录拦截器，添加登录积分
 * @author hzy
 *
 */
@Component
public class LoginPointHandler implements WechatLoginInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(LoginPointHandler.class);
	
	@Resource
	protected MemberPointRecordService memberPointRecordService;
	
	@Resource(name="snsMemberLevelRule")
	protected MemberLeveLRule snsMemberLevelRule;
	
	@Resource
	protected SnsPointSynchronizer snsMemberPointHandler;
	
	@Override
	public boolean preHandle(String openId) {
		return true;
	}

	@Override
	public void afterCompletion(final Member member) {
		final Integer storeId = StoreUtils.getStoreFromThreadLocal().getId();
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				LoginPointHandler.this.addPointToMember( member, storeId);
			}
		});
		
		thread.start();
	}
	
	private void addPointToMember(Member member, Integer storeId){
		logger.info("给会员加登录积分");
		if (this.isGetPoint(member.getId())) {
			return;
		}
		
		MemberPointData pointData = new MemberPointData();
		pointData.setStoreId(storeId);
		pointData.setMember(member);
		
		if(this.isFirstLogin(member.getId())){
			pointData.setPointType(SnsPointType.FIRST_LOGIN);
			pointData.setPoint(SnsPointType.FIRST_LOGIN.getPoint());
			
		}else{
			pointData.setPointType(SnsPointType.LOGIN);
			pointData.setPoint(SnsPointType.LOGIN.getPoint());
		}
		
		this.snsMemberPointHandler.addPoint(pointData);
	}
	
	
	private boolean isGetPoint(int memberId) {
		Date now = new Date();
		Date today = DateUtils.truncate(now, Calendar.DATE);
		Date tommorrow = DateUtils.addDays(today, 1);
		
		MemberPointRecordExample example = new MemberPointRecordExample();
		example.createCriteria()
		       .andMemberIdEqualTo(memberId)
		       .andCreateTimeGreaterThanOrEqualTo(today)
		       .andCreateTimeLessThan(tommorrow)
		       .andSourceTypeEqualTo(SnsPointType.FIRST_LOGIN.getSourceType());
		example.or()
		       .andMemberIdEqualTo(memberId)
	           .andCreateTimeGreaterThanOrEqualTo(today)
	           .andCreateTimeLessThan(tommorrow)
	           .andSourceTypeEqualTo(SnsPointType.LOGIN.getSourceType());
		
		return this.memberPointRecordService.countByExample(example) > 0;
	}
	
	private boolean isFirstLogin(int memberId){
		MemberPointRecordExample example = new MemberPointRecordExample();
		example.createCriteria()
		       .andMemberIdEqualTo(memberId)
		       .andSourceTypeEqualTo(SnsPointType.FIRST_LOGIN.getSourceType());
		
		example.or()
	           .andMemberIdEqualTo(memberId)
               .andSourceTypeEqualTo(SnsPointType.LOGIN.getSourceType());
		return this.memberPointRecordService.countByExample(example) <= 0;
	}

}
