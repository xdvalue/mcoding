package com.mcoding.base.auth.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mcoding.base.auth.bean.Role;
import com.mcoding.base.auth.bean.RoleExample;
import com.mcoding.base.core.BaseService;
import com.mcoding.base.core.PageView;

/**
 * Created by LiBing on 2014/6/5.
 */
public interface RoleService extends BaseService<Role, RoleExample> {

    //获取登录用户有权限菜单对应的操作权限数据
//    Map<String, Set<String>> queryUserOperatorAction(boolean isAdmin);

    //获取登录用户的角色列表(包括自身具有的角色和自身创建的角色)
    List<Role> queryUserOwnAndCreateRoles(boolean isAdmin, int userId);

    //根据角色Id查询角色
    Role queryRoleById(String roleId);

    //根据用户Id查询用户所具有的角色列表
    List<Role> queryUserOwnRoles(int userId);
    
    public PageView<Role> queryObjByPage(boolean isAdmin, String iDisplayStart, String iDisplayLength);

	Map<String, Set<String>> queryUserOperatorAction(boolean isAdmin, List<String> roleNames);
}
