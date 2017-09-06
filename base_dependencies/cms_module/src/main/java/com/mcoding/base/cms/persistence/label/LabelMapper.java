package com.mcoding.base.cms.persistence.label;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mcoding.base.cms.bean.label.Label;
import com.mcoding.base.cms.bean.label.LabelExample;

/**
 * LabelMapper
 * 
 * @author acer
 * 
 */
public interface LabelMapper {

	int countByExample(LabelExample example);

	int deleteByExample(LabelExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Label record);

	int insertSelective(Label record);

	List<Label> selectByExample(LabelExample example);

	Label selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Label record,
			@Param("example") LabelExample example);

	int updateByExample(@Param("record") Label record,
			@Param("example") LabelExample example);

	int updateByPrimaryKeySelective(Label record);

	int updateByPrimaryKey(Label record);

	List<Label> selectByExampleByPage(LabelExample example);

	/**
	 * 根据标签id，增加1点热度
	 * @param labelId
	 * @return
	 */
	int addLabelHitById(int labelId);
	
	/**
	 * 根据公司id与标签值，增加1点热度
	 * @param mark
	 * @param storeId
	 * @return
	 */
	int addLabelByMarkAndStoreId(@Param("mark") String mark, @Param("storeId") int storeId);
}