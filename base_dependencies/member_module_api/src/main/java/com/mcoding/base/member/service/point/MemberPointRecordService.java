package com.mcoding.base.member.service.point;

import com.mcoding.base.member.bean.point.MemberPointRecord;
import com.mcoding.base.member.bean.point.MemberPointRecordExample;
import com.mcoding.base.ui.service.common.BaseService;

public interface MemberPointRecordService extends BaseService<MemberPointRecord, MemberPointRecordExample> {
	
	public int countByExample(MemberPointRecordExample example);
	
	public int sumPointByExample(MemberPointRecordExample example);
}