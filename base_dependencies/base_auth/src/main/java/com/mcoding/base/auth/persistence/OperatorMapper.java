package com.mcoding.base.auth.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mcoding.base.auth.bean.Operator;
import com.mcoding.base.auth.bean.OperatorExample;

public interface OperatorMapper {
    int countByExample(OperatorExample example);

    int deleteByExample(OperatorExample example);

    int deleteByPrimaryKey(Integer operId);

    int insert(Operator record);

    int insertSelective(Operator record);

    List<Operator> selectByExample(OperatorExample example);

    Operator selectByPrimaryKey(Integer operId);

    int updateByExampleSelective(@Param("record") Operator record, @Param("example") OperatorExample example);

    int updateByExample(@Param("record") Operator record, @Param("example") OperatorExample example);

    int updateByPrimaryKeySelective(Operator record);

    int updateByPrimaryKey(Operator record);
    
    List<Operator> selectByExampleByPage(OperatorExample example);

	List<Operator> queryByMenuId(String menuId);
}