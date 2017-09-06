package com.mcoding.base.auth.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.auth.bean.Operator;
import com.mcoding.base.auth.bean.OperatorExample;
import com.mcoding.base.auth.bean.Role;
import com.mcoding.base.auth.bean.RoleExample;
import com.mcoding.base.auth.bean.RoleRight;
import com.mcoding.base.auth.bean.RoleRightExample;
import com.mcoding.base.auth.persistence.OperatorMapper;
import com.mcoding.base.auth.persistence.RoleMapper;
import com.mcoding.base.auth.persistence.RoleRightMapper;
import com.mcoding.base.auth.service.RoleRightService;
import com.mcoding.base.core.PageView;

/**
 * Created by LiBing on 2014-07-22  17:45
 */
@Service
public class RoleRightServiceImpl implements RoleRightService {
    
    @Resource
    private RoleRightMapper roleRightMapper;
    
    @Resource
    private RoleMapper roleMapper;
    
    @Resource
    private OperatorMapper operatorMapper;
    
    @CacheEvict(value={"operatorCache", "roleRightCache"}, allEntries=true)
    @Override
    public void addObj(RoleRight roleRight) {
    	Date date = new Date();
        roleRight.setCreateTime(date);
        roleRightMapper.insertSelective(roleRight);
        
        //对角色进行权限配置后,重载security的metaSource
//        metadataSource.loadResourceDefine();
    }

    @CacheEvict(value={"operatorCache", "roleRightCache"}, allEntries=true)
    @Override
    public void deleteObj(RoleRight roleRight) {
    	RoleRightExample roleRightExample = new RoleRightExample();
    	roleRightExample.createCriteria()
    	                .andRoleIdEqualTo(roleRight.getRoleId())
    	                .andMenuIdEqualTo(roleRight.getMenuId())
    	                .andOperIdEqualTo(roleRight.getOperId());
//    	roleRightMapper.deleteObj(roleRight);
    	roleRightMapper.deleteByExample(roleRightExample);
    	
        //对角色进行权限配置后,重载security的metaSource
//        metadataSource.loadResourceDefine();
    }

    @CacheEvict(value={"operatorCache", "roleRightCache"}, allEntries=true)
	@Override
	public void deleteObjById(int id) {
		return;
	}

	@Override
	public void modifyObj(RoleRight t) {
		if (t.getMenuId() == null || t.getMenuId() ==0 ||
				t.getRoleId() == null || t.getRoleId() ==0 ||
				t.getOperId() == null || t.getOperId() ==0) {
			throw new IllegalArgumentException("参数不全，没法修改");
		}
		return;
	}

	@Override
	public RoleRight queryObjById(int id) {
		return null;
	}

	@Cacheable(value="roleRightCache", key="'RoleRightService' +#root.methodName + '_' + #example.toJson()")
	@Override
	public List<RoleRight> queryAllObjByExample(RoleRightExample example) {
		return this.roleRightMapper.selectByExample(example);
	}

	@Override
	public PageView<RoleRight> queryObjByPage(RoleRightExample example) {
		return null;
	}

	@Cacheable(value="roleRightCache", key="'RoleRightService' +#root.methodName + '_' + #url")
	@Override
	public List<Role> getRoleByOperatorUrl(String url) {
		OperatorExample operatorExample = new OperatorExample();
		operatorExample.createCriteria().andOperURLEqualTo(url);
		if (!url.contains(".html")) {
			operatorExample.or().andOperURLEqualTo(url + ".html");
		}
		
		List<Operator> operList = this.operatorMapper.selectByExample(operatorExample);
		List<Integer> operIdList = new ArrayList<>();
		for(int i=0; i<operList.size(); i++){
			operIdList.add(operList.get(i).getOperId());
		}
		
		if (CollectionUtils.isEmpty(operIdList)) {
			return null;
		}
		
		RoleRightExample roleRightExample = new RoleRightExample();
		roleRightExample.createCriteria().andOperIdIn(operIdList);
		List<RoleRight> roleRightList = this.roleRightMapper.selectByExample(roleRightExample);
		
		List<Integer> roleIdList = new ArrayList<>();
		for(int i=0; i<roleRightList.size(); i++){
			roleIdList.add(roleRightList.get(i).getRoleId());
		}
		
		if (CollectionUtils.isEmpty(roleIdList)) {
			return null;
		}
		
		RoleExample roleExample = new RoleExample();
		roleExample.createCriteria().andRoleIdIn(roleIdList);
		List<Role> roleList = this.roleMapper.selectByExample(roleExample);
		return roleList;
	}

}
