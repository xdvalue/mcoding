package com.mcoding.base.sns.persistence.module;

import org.apache.ibatis.annotations.Param;

import com.mcoding.base.core.IMapper;
import com.mcoding.base.sns.bean.module.SnsModule;
import com.mcoding.base.sns.bean.module.SnsModuleExample;

public interface SnsModuleMapper extends IMapper<SnsModule, SnsModuleExample> {
	
	public void addPostNumById(@Param("id")int id, @Param("count")int count);
}