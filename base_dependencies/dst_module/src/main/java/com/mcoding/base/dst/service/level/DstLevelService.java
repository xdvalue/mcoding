package com.mcoding.base.dst.service.level;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.dst.bean.level.DstLevel;
import com.mcoding.base.dst.bean.level.DstLevelExample;

public interface DstLevelService extends BaseService<DstLevel, DstLevelExample> {
	
	/**
	 * 根据级别id，查找上级级别
	 * @param levelId
	 * @return
	 */
	public DstLevel queryParentLevelById(int levelId);
	
	/**
	 * 根据级别id，查找下级级别
	 * @param levelId
	 * @return
	 */
	public DstLevel queryChildLevelById(int levelId);
}