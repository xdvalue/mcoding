package com.mcoding.base.member.persistence.member;

import com.mcoding.base.core.IMapper;
import com.mcoding.base.member.bean.member.MemberExtInfo;
import com.mcoding.base.member.bean.member.MemberExtInfoExample;

public interface MemberExtInfoMapper extends IMapper<MemberExtInfo, MemberExtInfoExample>{
	
	public MemberExtInfo selectByMemberId(int memberId);
}