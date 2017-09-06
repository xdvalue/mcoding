package com.mcoding.base.point.persistence.level;

import org.apache.ibatis.annotations.Param;

import com.mcoding.base.core.IMapper;
import com.mcoding.base.point.bean.level.MemberLevel;
import com.mcoding.base.point.bean.level.MemberLevelExample;

public interface MemberLevelMapper extends IMapper<MemberLevel, MemberLevelExample> {

	/**
	 * 添加积分
	 * @param memberId
	 * @param point
	 * @param point2 
	 */
	public void addPoint(@Param("memberId") int memberId, @Param("moduleType")int moduleType,
			@Param("point")int point, @Param("storeId")int storeId);
}