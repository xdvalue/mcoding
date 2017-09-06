package com.mcoding.base.auth.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mcoding.base.auth.bean.Role;
import com.mcoding.base.auth.bean.RoleExample;

public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> selectByExampleByPage(RoleExample example);

	List<Role> queryUserOwnAndCreateRoles(int loginUserId);

	List<Role> queryRoleByPage(Map<String, Object> param);

	List<Role> queryUserRolesByUserId(int userId);
}