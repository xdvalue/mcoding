package com.mcoding.base.auth.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mcoding.base.auth.bean.RoleRight;
import com.mcoding.base.auth.bean.RoleRightExample;

public interface RoleRightMapper {
    int countByExample(RoleRightExample example);

    int deleteByExample(RoleRightExample example);

    int insert(RoleRight record);

    int insertSelective(RoleRight record);

    List<RoleRight> selectByExample(RoleRightExample example);

    int updateByExampleSelective(@Param("record") RoleRight record, @Param("example") RoleRightExample example);

    int updateByExample(@Param("record") RoleRight record, @Param("example") RoleRightExample example);

	List<Integer> queryOperIdsByRoleIds(List<Integer> userRoleIds);

	List<RoleRight> queryUserRightInfo(String string);
}