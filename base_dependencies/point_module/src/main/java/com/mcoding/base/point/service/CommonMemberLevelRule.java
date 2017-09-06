package com.mcoding.base.point.service;

import org.springframework.stereotype.Component;

import com.mcoding.base.member.utils.MemberConstant;
import com.mcoding.base.point.bean.level.MemberLevel;

@Component("commonMemberLevelRule")
public class CommonMemberLevelRule implements MemberLeveLRule {

	@Override
	public void upgrade(int memberId, int moduleType, int storeId) {
		return;
	}

	@Override
	public MemberLevel createDefaultLevel(int memberId, Integer moduleType, int storeId) {
		MemberLevel level = new MemberLevel();
		level.setMemberId(memberId);
		level.setGrade(1);
		level.setGradeName("初级会员");
		level.setTotalPoint(10);
		level.setModuleType(MemberConstant.CODE_MODULE_TYPE_COMMON);
		level.setStoreId(storeId);
		return level;
	}

}
