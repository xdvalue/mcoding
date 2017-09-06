package com.mcoding.base.sns.service.module;

import java.util.List;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.sns.bean.module.SnsModule;
import com.mcoding.base.sns.bean.module.SnsModuleExample;

/**
 * 
 * 社区板块service
 * 
 * @author acer
 *
 */
public interface SnsModuleService extends BaseService<SnsModule, SnsModuleExample> {

	/**
	 * 根据模块ID查询所有子模块
	 * 
	 * @param storeId
	 * @param moduleId
	 * @return
	 */
	public List<SnsModule> queryChildModulesById(int storeId, int moduleId);
	
	public void addPostCountNum(int moduleId, int addNum);

}
