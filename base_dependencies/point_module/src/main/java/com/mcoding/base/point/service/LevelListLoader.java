package com.mcoding.base.point.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Component;

import com.mcoding.base.member.service.member.IMemberExtInfoLoader;
import com.mcoding.base.point.bean.LevelListForMemberExtInfo;
import com.mcoding.base.point.bean.level.MemberLevel;
import com.mcoding.base.point.service.level.MemberLevelService;
import com.mcoding.base.ui.bean.member.IMemberExtInfo;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

@Component
public class LevelListLoader implements IMemberExtInfoLoader {

	@Override
	public String getMemberExtInfoType() {
		return "levelList";
	}

	@Override
	public IMemberExtInfo getMemberExtInfo(int memberId) {
		MemberLevelService memberLevelService = SpringContextHolder.getBean("memberLevelService");
		Map<Integer, MemberLevel> levelMap = memberLevelService.queryAllByMemberId(memberId);
		if (MapUtils.isEmpty(levelMap)) {
			return null;
		}
		
		LevelListForMemberExtInfo memberExtInfo = new LevelListForMemberExtInfo();
		
		Iterator<Integer> iterator = levelMap.keySet().iterator();
		while (iterator.hasNext()) {
			Integer key = iterator.next();
			MemberLevel level = levelMap.get(key);
			memberExtInfo.put("mt" + key, level);
		}
		return memberExtInfo;
	}

}
