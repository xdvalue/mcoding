package com.mcoding.base.sns.service.rule.point;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Component;

import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.service.member.MemberService;
import com.mcoding.base.point.bean.MemberPointData;
import com.mcoding.base.point.bean.pointrecord.MemberPointRecordExample;
import com.mcoding.base.point.service.pointrecord.MemberPointRecordService;
import com.mcoding.base.sns.bean.post.SnsPost;
import com.mcoding.base.sns.utils.SnsPointType;

/**
 * 发帖积分处理器
 * @author hzy
 *
 */
@Component
public class PostPointRuleHandler extends BasePointHandler<SnsPost, Object, Object> {
	
	private static final Integer POST_POINT_LIMIT = 10;
	
	@Resource
	protected MemberService memberService;
	
	@Resource
	protected MemberPointRecordService memberPointRecordService;

	@Override
	public List<MemberPointData> addPoint(SnsPost post, Object pointSource, Object methodResult) {
		
		if(isOverLimit(post.getMemeberId())){
			return null;
		}
		
		MemberPointData pointData = new MemberPointData();
		pointData.setPoint(SnsPointType.POST.getPoint());
		pointData.setPointType(SnsPointType.POST);
		pointData.setSourceId(post.getId());
		pointData.setStoreId(post.getStoreId());
		
		Member member = this.memberService.queryObjById(post.getMemeberId());
		pointData.setMember(member);
		
		List<MemberPointData> list = new ArrayList<>();
		list.add(pointData);
		return list;
	}

	/**
	 * 是否超过限制
	 * @param memeberId
	 * @return
	 */
	private boolean isOverLimit(Integer memeberId) {
		Date now = new Date();
		Date today = DateUtils.truncate(now, Calendar.DATE);
		Date tomorrow = DateUtils.addDays(today, 1);
		
		MemberPointRecordExample example = new MemberPointRecordExample();
		example.createCriteria()
		       .andMemberIdEqualTo(memeberId)
		       .andSourceTypeEqualTo(SnsPointType.POST.getSourceType())
		       .andCreateTimeGreaterThanOrEqualTo(today)
		       .andCreateTimeLessThan(tomorrow);
		
		int pointSum = this.memberPointRecordService.sumPointByExample(example);
		return pointSum >= POST_POINT_LIMIT;
	}
}
