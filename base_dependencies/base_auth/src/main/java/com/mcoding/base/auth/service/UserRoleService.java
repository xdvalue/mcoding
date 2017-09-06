package com.mcoding.base.auth.service;

import java.util.List;

import com.mcoding.base.auth.bean.UserRole;
import com.mcoding.base.auth.bean.UserRoleExample;
import com.mcoding.base.core.BaseService;

/**
 * Created by LiBing on 2014/6/10.
 */
public interface UserRoleService extends BaseService<UserRole, UserRoleExample> {

    //根据用户Id获取用户拥有的角色信息
    List<UserRole> queryUserRoleList(int userId);
    
    //根据用户Id查询用户所拥有的角色Id
    List<Integer> queryUserOwnRoleIds(int userId);

    //根据用户Id删除用户所拥有的角色信息
    void deleteAllUserRole(int userId);
}
