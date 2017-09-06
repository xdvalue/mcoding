package com.mcoding.base.member.bean.point.vo;

import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.bean.point.MemberPointRecord;
import com.mcoding.base.member.utils.point.PointType;

public class MemberPointData {

	private PointType pointType;
	private int point;
	private Member member;
	private Integer storeId;
	private Integer sourceId;

	public PointType getPointType() {
		return pointType;
	}

	public void setPointType(PointType pointType) {
		this.pointType = pointType;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	
	public MemberPointRecord toMemberPointRecord(){
		MemberPointRecord memberPointRecord = new MemberPointRecord();
		memberPointRecord.setMemberId(this.member.getId());
		memberPointRecord.setMemberName(this.member.getName());
		memberPointRecord.setPoint(this.getPoint());
		memberPointRecord.setSourceId(this.getSourceId());
		memberPointRecord.setSourceName(this.pointType.getSourceName());
		memberPointRecord.setSourceType(pointType.getSourceType());
		memberPointRecord.setStoreId(this.getStoreId());
		memberPointRecord.setModuleType(pointType.getModuleType());
		return memberPointRecord;
	}
}
