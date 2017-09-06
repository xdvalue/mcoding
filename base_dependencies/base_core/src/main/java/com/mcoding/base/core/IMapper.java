package com.mcoding.base.core;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2014/6/4.
 * 提供基本的增删改查方法
 */
public interface IMapper<T extends Serializable, Example extends IExample<T>> {
	public int countByExample(Example example);

	public int deleteByExample(Example example);

	public int deleteByPrimaryKey(Integer id);

	public int insert(T record);

	public int insertSelective(T record);

	public List<T> selectByExample(Example example);

	public T selectByPrimaryKey(Integer id);

	public int updateByExampleSelective(@Param("record") T record, @Param("example") Example example);

	public int updateByExample(@Param("record") T record, @Param("example") Example example);

	public int updateByPrimaryKeySelective(T record);

	public int updateByPrimaryKey(T record);
    
	public List<T> selectByExampleByPage(Example example);
}
