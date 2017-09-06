package com.mcoding.base.point.service.pointrecord;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.point.bean.pointrecord.MemberPointRecord;
import com.mcoding.base.point.bean.pointrecord.MemberPointRecordExample;

public interface MemberPointRecordService extends BaseService<MemberPointRecord, MemberPointRecordExample> {
	
	public int countByExample(MemberPointRecordExample example);
	
	public int sumPointByExample(MemberPointRecordExample example);
}