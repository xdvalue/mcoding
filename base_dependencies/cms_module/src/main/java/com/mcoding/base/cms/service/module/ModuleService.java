package com.mcoding.base.cms.service.module;

import java.util.List;
import java.util.Map;

import com.mcoding.base.cms.bean.module.Module;
import com.mcoding.base.cms.bean.module.ModuleExample;
import com.mcoding.base.core.BaseService;
import com.mcoding.base.core.PageView;

/**
 * ModuleService
 * 
 * @author acer
 * 
 */
public interface ModuleService extends BaseService<Module, ModuleExample> {

	List<Module> selectModules(Map<String, Object> map);
	
    public PageView<Module> selectByPage(Integer storeId);
}
