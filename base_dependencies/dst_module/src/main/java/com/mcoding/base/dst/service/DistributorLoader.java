package com.mcoding.base.dst.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.mcoding.base.dst.bean.level.DstMemberLevel;
import com.mcoding.base.dst.service.level.DstMemberLevelService;
import com.mcoding.base.member.service.member.IMemberExtInfoLoader;
import com.mcoding.base.ui.bean.member.IMemberExtInfo;

@Component
public class DistributorLoader implements IMemberExtInfoLoader {
	
	@Resource
	protected DstMemberLevelService dstMemberLevelService;

	@Override
	public String getMemberExtInfoType() {
		return "distributor";
	}

	@Override
	public IMemberExtInfo getMemberExtInfo(int memberId) {
		DstMemberLevel memberLevel = this.dstMemberLevelService.queryByMemberId(memberId);
		return memberLevel;
	}

}
