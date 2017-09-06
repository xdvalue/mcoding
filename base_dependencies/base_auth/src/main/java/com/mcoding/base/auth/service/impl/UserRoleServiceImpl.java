package com.mcoding.base.auth.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.auth.bean.UserRole;
import com.mcoding.base.auth.bean.UserRoleExample;
import com.mcoding.base.auth.persistence.UserRoleMapper;
import com.mcoding.base.auth.service.UserRoleService;
import com.mcoding.base.core.PageView;

/**
 * Created by LiBing on 2014/6/10.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @CacheEvict(value={"roleCache", "roleRightCache"}, allEntries=true)
    @Override
    public void addObj(UserRole userRole) {
    	 Date date = new Date();
         userRole.setCreateTime(date);
         userRoleMapper.insertSelective(userRole);
    }
    
    @CacheEvict(value={"roleCache", "roleRightCache"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
    	this.userRoleMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"roleCache", "roleRightCache"}, allEntries=true)
    @Override
    public void modifyObj(UserRole userRole) {
    	if (userRole == null || userRole.getRightId() ==null || userRole.getRightId()==0) {
			throw new IllegalArgumentException("id不能为空");
		}
    	this.userRoleMapper.updateByPrimaryKeySelective(userRole);
    }

    @Cacheable(value="roleCache", key="'UserRoleServiceImpl' +#root.methodName + '_' + #userId")
    @Override
    public List<UserRole> queryUserRoleList(int userId) {
        return userRoleMapper.queryListByUserId(userId);
    }

    @Cacheable(value="roleCache", key="'UserRoleServiceImpl' +#root.methodName + '_' + #userId")
    @Override
    public List<Integer> queryUserOwnRoleIds(int userId) {
    	UserRoleExample example = new UserRoleExample();
    	example.createCriteria().andUserIdEqualTo(userId);
    	
    	List<UserRole> list = this.userRoleMapper.selectByExample(example);
    	List<Integer> ids = new ArrayList<>();
    	for(int i=0; i<list.size(); i++){
    		ids.add(list.get(i).getRoleId());
    	}
    	
    	return ids;
    }

    @CacheEvict(value={"roleCache", "roleRightCache"}, allEntries=true)
    @Override
    public void deleteAllUserRole(int userId) {
//        userRoleMapper.deleteAllUserRole(userId);
    	UserRoleExample example = new UserRoleExample();
    	example.createCriteria().andUserIdEqualTo(userId);
    	
    	this.userRoleMapper.deleteByExample(example);
    }

    @Cacheable(value="roleCache", key="'UserRoleServiceImpl' +#root.methodName + '_' + #id")
	@Override
	public UserRole queryObjById(int id) {
		return this.userRoleMapper.selectByPrimaryKey(id);
	}

    @Cacheable(value="roleCache", key="'UserRoleServiceImpl' +#root.methodName +'_'+#example.toJson() ")
	@Override
	public List<UserRole> queryAllObjByExample(UserRoleExample example) {
		return this.userRoleMapper.selectByExample(example);
	}

    @Cacheable(value="roleCache", key="'UserRoleServiceImpl' +#root.methodName +'_'+#example.toJson() ")
	@Override
	public PageView<UserRole> queryObjByPage(UserRoleExample example) {
		PageView<UserRole> pageView = example.getPageView();
		pageView.setQueryResult(this.userRoleMapper.selectByExampleByPage(example));
		return pageView;
	}
}
