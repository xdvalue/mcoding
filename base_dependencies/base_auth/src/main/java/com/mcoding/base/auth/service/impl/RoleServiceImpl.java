package com.mcoding.base.auth.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.auth.bean.Role;
import com.mcoding.base.auth.bean.RoleExample;
import com.mcoding.base.auth.bean.RoleRight;
import com.mcoding.base.auth.persistence.RoleMapper;
import com.mcoding.base.auth.persistence.RoleRightMapper;
import com.mcoding.base.auth.service.RoleService;
import com.mcoding.base.auth.utils.SpringSecurityUtils;
import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.PageView;

/**
 * Created by LiBing on 2014/6/5.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    
    @Autowired
    private RoleRightMapper rightMapper;

    @CacheEvict(value={"roleCache", "roleRightCache"}, allEntries=true)
    @Override
    public void addObj(Role role) {
    	Date date = new Date();
        role.setCreateUserId(SpringSecurityUtils.getLoginUserId());
        role.setCreateTime(date);
        role.setUpdateTime(date);
        roleMapper.insertSelective(role);
    }

    @CacheEvict(value={"roleCache", "roleRightCache"}, allEntries=true)
    @Override
    public void deleteObjById(int roleId) {
    	if (roleId == 1) {
			throw new CommonException("系统管理员则不允许删除");
		}
    	
    	roleMapper.deleteByPrimaryKey(roleId);
    }

    @CacheEvict(value={"roleCache", "roleRightCache"}, allEntries=true)
    @Override
    public void modifyObj(Role role) {
    	if (role == null || role.getRoleId() == null || role.getRoleId() ==0) {
			throw new IllegalArgumentException("id不能为空");
		}
    	
    	Date date = new Date();
        role.setUpdateTime(date);
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Cacheable(value="roleCache", key="'RoleService' +#root.methodName +'_'+#isAdmin+'_'+ T(com.mcoding.base.utils.json.JsonUtilsForMcoding).writeValueAsString(#roleNames)")
    @Override
    public Map<String, Set<String>> queryUserOperatorAction(boolean isAdmin, List<String> roleNames) {
//        String loginName = SpringSecurityUtils.getLoginUserName();
        Map<String, Set<String>> rightMap = new HashMap<String, Set<String>>();
        //是否具有管理员权限
        if (isAdmin) {
            rightMap.put("menuAll", new HashSet<String>());
        } else {
//            List<String> roleNames = SpringSecurityUtils.getLoginUserRoleNameList();
            for (String roleName : roleNames) {
            	RoleExample roleExample = new RoleExample();
            	roleExample.createCriteria().andRoleNameEqualTo(roleName);
            	
            	List<Role> list = this.roleMapper.selectByExample(roleExample);
            	if (list.size() == 0) {
					continue;
				}else if(list.size() > 1){
					throw new CommonException("有重名的角色，请检查数据");
				}
            	
                Role role = list.get(0);
                
                List<RoleRight> rights = rightMapper.queryUserRightInfo(role.getRoleId() + "");
                for (RoleRight right : rights) {
                    String menuCode = right.getMenuCode();
                    String operCode = right.getOperCode();
                    Set<String> operSet = null;
                    if (rightMap.containsKey(menuCode)) {
                        operSet = rightMap.get(menuCode);
                    } else {
                        operSet = new HashSet<String>();
                    }
                    operSet.add(operCode);
                    rightMap.put(menuCode, operSet);
                }
            }
        }
        return rightMap;
    }

    @Cacheable(value="roleCache", key="'RoleService' +#root.methodName +'_'+#isAdmin+'_'+#userId")
    @Override
    public List<Role> queryUserOwnAndCreateRoles(boolean isAdmin, int userId) {
    	RoleExample roleExample = new RoleExample();
    	
        //需要根据用户身份取出对应的角色
        if (isAdmin) {
        	return this.roleMapper.selectByExample(roleExample);
        } else {
            return roleMapper.queryUserOwnAndCreateRoles(userId);
        }
    }

    @Cacheable(value="roleCache", key="'RoleService' +#root.methodName +'_'+#roleId")
    @Override
    public Role queryRoleById(String roleId) {
        return roleMapper.selectByPrimaryKey(Integer.valueOf(roleId));
    }

    @Cacheable(value="roleCache", key="'RoleService' +#root.methodName +'_'+#isAdmin+'_' + #iDisplayStart +'_'+#iDisplayLength ")
    @Override
    public PageView<Role> queryObjByPage(boolean isAdmin, String iDisplayStart, String iDisplayLength) {
        PageView<Role> pageView = new PageView<Role>(iDisplayStart, iDisplayLength);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pageView", pageView);
        if (!isAdmin) {
            int userId = SpringSecurityUtils.getLoginUserId();
            param.put("userId", userId);
        }
        List<Role> roleList = roleMapper.queryRoleByPage(param);
        pageView.setQueryResult(roleList);
        return pageView;
    }

    @Cacheable(value="roleCache", key="'RoleService' +#root.methodName +'_'+#userId ")
    @Override
    public List<Role> queryUserOwnRoles(int userId) {
        return roleMapper.queryUserRolesByUserId(userId);
    }

    @Cacheable(value="roleCache", key="'RoleService' +#root.methodName +'_'+#id ")
	@Override
	public Role queryObjById(int id) {
		return this.roleMapper.selectByPrimaryKey(id);
	}

    @Cacheable(value="roleCache", key="'RoleService' +#root.methodName +'_'+#example.toJson() ")
	@Override
	public List<Role> queryAllObjByExample(RoleExample example) {
		return this.roleMapper.selectByExample(example);
	}

    @Cacheable(value="roleCache", key="'RoleService' +#root.methodName +'_'+#example.toJson() ")
	@Override
	public PageView<Role> queryObjByPage(RoleExample example) {
		PageView<Role> pageView = example.getPageView();
		pageView.setQueryResult(this.roleMapper.selectByExampleByPage(example));
		return pageView;
	}
}
