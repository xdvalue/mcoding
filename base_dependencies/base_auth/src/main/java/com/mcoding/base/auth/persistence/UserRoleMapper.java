package com.mcoding.base.auth.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mcoding.base.auth.bean.UserRole;
import com.mcoding.base.auth.bean.UserRoleExample;

public interface UserRoleMapper {
    int countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    int deleteByPrimaryKey(Integer rightId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> selectByExample(UserRoleExample example);

    UserRole selectByPrimaryKey(Integer rightId);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
    
    List<UserRole> selectByExampleByPage(UserRoleExample example);

	List<UserRole> queryListByUserId(int userId);
}