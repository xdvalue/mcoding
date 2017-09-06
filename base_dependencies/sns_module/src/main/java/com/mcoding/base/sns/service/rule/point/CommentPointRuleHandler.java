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
import com.mcoding.base.sns.bean.comment.SnsComment;
import com.mcoding.base.sns.bean.post.SnsPost;
import com.mcoding.base.sns.service.post.SnsPostService;
import com.mcoding.base.sns.utils.SnsPointType;

/**
 * 评论后积分计算器
 * @author hzy
 *
 */
@Component
public class CommentPointRuleHandler extends BasePointHandler<SnsComment, Object, Object> {
	
	private static final Integer COMMENT_POINT_LIMIT = 10;
	private static final Integer BE_COMMENT_POINT_LIMIT = 10;

	@Resource
	protected MemberPointRecordService memberPointRecordService;
	
	@Resource
	protected SnsPostService snsPostService;
	
	@Resource
	protected MemberService memberService;
	
	@Override
	public List<MemberPointData> addPoint(SnsComment comment, Object pointSource, Object methodResult) {
		SnsPost snsPost = this.snsPostService.queryObjById(comment.getPostId());
		
		//自己评论自己的帖子，是没有积分的
		if (snsPost.getMember().getId().equals(comment.getMemberId())) {
			return null;
		}
		
		MemberPointData record = getPointForComment(comment);
		MemberPointData record2 = getPointForBeCommented(snsPost);
		
		List<MemberPointData> list = new ArrayList<>();
		if(record != null){
			list.add(record);
		}
		if(record2 != null){
			list.add(record2);
		}
		
		return list;
	}

	/**
	 * 求出文章被评论后，获取的积分
	 * @param comment
	 * @return
	 */
	private MemberPointData getPointForComment (SnsComment comment) {
		if(isOverLimitForComment(comment.getMemberId())){
			return null;
		}
		
		MemberPointData memberPointData = new MemberPointData();
		memberPointData.setPoint(SnsPointType.COMMENT.getPoint());
		memberPointData.setPointType(SnsPointType.COMMENT);
		memberPointData.setSourceId(comment.getId());
		memberPointData.setStoreId(comment.getStoreId());
		
		Member member = this.memberService.queryObjById(comment.getMemberId());
		memberPointData.setMember(member);
		
		return memberPointData;
	}

	/**
	 * 是否超出 获取积分的上限
	 * @param memberId
	 * @return
	 */
	private boolean isOverLimitForComment(int memberId) {
		Date now = new Date();
		Date today = DateUtils.truncate(now, Calendar.DATE);
		Date tomorrow = DateUtils.addDays(today, 1);
		
		MemberPointRecordExample example = new MemberPointRecordExample();
		example.createCriteria()
		       .andMemberIdEqualTo(memberId)
		       .andSourceTypeEqualTo(SnsPointType.COMMENT.getSourceType())
		       .andCreateTimeGreaterThanOrEqualTo(today)
		       .andCreateTimeLessThan(tomorrow);
		
		int sum = this.memberPointRecordService.sumPointByExample(example);
		return sum > COMMENT_POINT_LIMIT;
	}

	/**
	 * 求出评论获取的积分
	 * @param comment
	 * @return
	 */
	private MemberPointData getPointForBeCommented(SnsPost snsPost) {
		if(this.isOverLimitForBeComment(snsPost.getMemeberId())){
			return null;
		}
		
		MemberPointData pointData = new MemberPointData();
		pointData.setPoint(SnsPointType.BE_COMMENTED.getPoint());
		pointData.setPointType(SnsPointType.BE_COMMENTED);
		pointData.setSourceId(snsPost.getId());
		pointData.setStoreId(snsPost.getStoreId());
		
		Member member = this.memberService.queryObjById(snsPost.getMemeberId());
		pointData.setMember(member);
		
		return pointData;
	}

	/**
	 * 是否超出获取被评论的积分的上限
	 * @param memberId
	 * @return
	 */
	private boolean isOverLimitForBeComment(Integer memberId) {
		Date now = new Date();
		Date today = DateUtils.truncate(now, Calendar.DATE);
		Date tomorrow = DateUtils.addDays(today, 1);
		
		MemberPointRecordExample example = new MemberPointRecordExample();
		example.createCriteria()
		       .andMemberIdEqualTo(memberId)
		       .andSourceTypeEqualTo(SnsPointType.BE_COMMENTED.getSourceType())
		       .andCreateTimeGreaterThanOrEqualTo(today)
		       .andCreateTimeLessThan(tomorrow);
		
		int sum = this.memberPointRecordService.sumPointByExample(example);
		return sum > BE_COMMENT_POINT_LIMIT;
	}
}
