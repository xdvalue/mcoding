package com.mcoding.base.member.service.member.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.mcoding.base.member.bean.member.MemberExtInfo;
import com.mcoding.base.member.bean.member.MemberExtInfoExample;
import com.mcoding.base.member.service.member.IMemberExtInfoLoader;
import com.mcoding.base.member.service.member.MemberExtInfoService;
import com.mcoding.base.ui.bean.member.IMemberExtInfo;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

//@Component
public class MemberExtInfoLoaderImpl implements IMemberExtInfoLoader {
	
	private static MemberExtInfoService memberExtInfoService = SpringContextHolder.getOneBean(MemberExtInfoService.class);

	@Override
	public String getMemberExtInfoType() {
		return "base";
	}

	@Override
	public IMemberExtInfo getMemberExtInfo(int memberId) {
		MemberExtInfoExample example = new MemberExtInfoExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		
		
		List<MemberExtInfo> list = memberExtInfoService.queryAllObjByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

}
