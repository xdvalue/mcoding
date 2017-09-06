package com.mcoding.base.point.persistence.pointrecord;

import com.mcoding.base.core.IMapper;
import com.mcoding.base.point.bean.pointrecord.MemberPointRecord;
import com.mcoding.base.point.bean.pointrecord.MemberPointRecordExample;

public interface MemberPointRecordMapper extends IMapper<MemberPointRecord, MemberPointRecordExample> {

	int sumPointByExample(MemberPointRecordExample example);
}