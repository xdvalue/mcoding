package com.mcoding.base.cms.persistence.collect;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mcoding.base.cms.bean.collect.Collect;
import com.mcoding.base.cms.bean.collect.CollectExample;

/**
 * 
 * CollectMapper
 * 
 * @author acer
 * 
 */
public interface CollectMapper {

	int countByExample(CollectExample example);

	int deleteByExample(CollectExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Collect record);

	int insertSelective(Collect record);

	List<Collect> selectByExample(CollectExample example);

	Collect selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Collect record,
			@Param("example") CollectExample example);

	int updateByExample(@Param("record") Collect record,
			@Param("example") CollectExample example);

	int updateByPrimaryKeySelective(Collect record);

	int updateByPrimaryKey(Collect record);

	List<Collect> selectByExampleByPage(CollectExample example);
}