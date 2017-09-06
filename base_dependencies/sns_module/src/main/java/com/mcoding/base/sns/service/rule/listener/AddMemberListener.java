package com.mcoding.base.sns.service.rule.listener;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.service.member.event.FinishAddMemberEvent;
import com.mcoding.base.point.bean.MemberPointData;
import com.mcoding.base.point.bean.pointrecord.MemberPointRecordExample;
import com.mcoding.base.point.service.MemberLeveLRule;
import com.mcoding.base.point.service.pointrecord.MemberPointRecordService;
import com.mcoding.base.sns.service.rule.SnsPointSynchronizer;
import com.mcoding.base.sns.utils.SnsPointType;

//@Component
public class AddMemberListener implements ApplicationListener<FinishAddMemberEvent> {

	private Logger logger = LoggerFactory.getLogger(AddMemberListener.class);
	
	@Resource
	protected MemberPointRecordService memberPointRecordService;
	
	@Resource(name="snsMemberLevelRule")
	protected MemberLeveLRule snsMemberLevelRule;
	
	@Resource
	protected SnsPointSynchronizer snsMemberPointHandler;
	
	@Override
	public void onApplicationEvent(FinishAddMemberEvent event) {
		Member member = (Member) event.getSource();
		if (Member.SOURCE_WEIXIN.equals(member.getSource())) {
			this.addPointToMember(member, event.getStoreId());
		}
		
		if (Member.TYPE_MARK.equals(member.getType())) {
			this.addPointToMember(member, event.getStoreId());
		}
		
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
