package com.mcoding.base.auth.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.auth.bean.Operator;
import com.mcoding.base.auth.bean.OperatorExample;
import com.mcoding.base.auth.bean.RoleRight;
import com.mcoding.base.auth.bean.RoleRightExample;
import com.mcoding.base.auth.persistence.OperatorMapper;
import com.mcoding.base.auth.persistence.RoleRightMapper;
import com.mcoding.base.auth.service.OperatorService;
import com.mcoding.base.auth.utils.SpringSecurityUtils;
import com.mcoding.base.core.PageView;

/**
 * Created by LiBing on 2014-07-17  14:02
 */
@Service
public class OperatorServiceImpl implements OperatorService {
    
    @Resource
    private OperatorMapper operatorMapper;
    
    @Resource
    private RoleRightMapper roleRightMapper;

    @CacheEvict(value={"operatorCache", "roleRightCache"}, allEntries=true)
	@Override
	public void addObj(Operator operator) {
		Date date = new Date();
		operator.setCreateTime(date);
		operator.setUpdateTime(date);
		operatorMapper.insertSelective(operator);
	}

    @CacheEvict(value={"operatorCache", "roleRightCache"} , allEntries=true)
    @Override
    public void deleteObjById(int id) {
        operatorMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"operatorCache", "roleRightCache"}, allEntries=true)
    @Override
    public void modifyObj(Operator operator) {
    	if (operator ==null || operator.getOperId()==null || operator.getOperId()==0) {
    		throw new IllegalArgumentException("id不能为空");
		}
    	
    	 Date date = new Date();
         operator.setUpdateTime(date);
         operatorMapper.updateByPrimaryKeySelective(operator);
    }
    
    @Override
    public void editOperatorsByMenuId(Operator operator) {
    	Integer operId = operator.getOperId();
        if (operId != null) {
            this.modifyObj(operator);
        } else {
            this.addObj(operator);
        }
    	
    }

    @Cacheable(value="operatorCache", key="'OperatorService_' +#root.methodName +'_'+#id")
    @Override
    public Operator queryObjById(int id) {
        return this.operatorMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="operatorCache", key="'OperatorService_' +#root.methodName +'_'+#menuId")
    @Override
    public PageView<Operator> queryOperatorsByPage(String menuId) {
        PageView<Operator> pageView = new PageView<Operator>("1", "20");
        List<Operator> operatorList = operatorMapper.queryByMenuId(menuId);
        pageView.setQueryResult(operatorList);
        return pageView;
    }

    @Cacheable(value="operatorCache", key="'OperatorService_' +#root.methodName +'_'+#menuId")
    @Override
    public List<Operator> queryOperatorsByMenuId(String menuId) {
        return operatorMapper.queryByMenuId(menuId);
    }

    @Cacheable(value="operatorCache", key="'OperatorService_' +#root.methodName +'_'+#roleId + '_'+#menuId +'_'+ T(com.mcoding.base.utils.json.JsonUtilsForMcoding).writeValueAsString(#userRoleIds)")
    @Override
    public PageView<Operator> queryRoleOperatorsByMenuId(int roleId, int menuId, List<Integer> userRoleIds) {
        PageView<Operator> pageView = new PageView<Operator>("1", "30");
        List<Operator> operatorList = operatorMapper.queryByMenuId(String.valueOf(menuId));
        
        RoleRightExample roleRightExample = new RoleRightExample();
        roleRightExample.createCriteria().andRoleIdEqualTo(roleId);
        List<RoleRight> roleRights = this.roleRightMapper.selectByExample(roleRightExample);
        
        if (SpringSecurityUtils.isManagerLoginUser()) {
            for (Operator oper : operatorList) {
                for (RoleRight right : roleRights) {
                    if (oper.getOperId() == right.getOperId() && oper.getMenuId() == right.getMenuId()) {
                        oper.setAuthorized(true);
                        break;
                    }
                }
            }
            pageView.setQueryResult(operatorList);
        } else {
            List<Operator> userOperList = new ArrayList<Operator>();
            List<Integer> userOperIds = roleRightMapper.queryOperIdsByRoleIds(userRoleIds);
            for (Operator oper : operatorList) {
                if (userOperIds.contains(oper.getOperId())) {
                    userOperList.add(oper);
                    for (RoleRight right : roleRights) {
                        if (oper.getOperId() == right.getOperId() && oper.getMenuId() == right.getMenuId()) {
                            oper.setAuthorized(true);
                            break;
                        }
                    }
                }
            }
            pageView.setRowCount(userOperList.size());
            pageView.setQueryResult(userOperList);
        }

        return pageView;
    }

    @Cacheable(value="operatorCache", key="'OperatorService_' +#root.methodName +'_'+ #menuId +'_'+T(com.mcoding.base.utils.json.JsonUtilsForMcoding).writeValueAsString(#userRoleIds)")
    @Override
    public List<Operator> queryUserOperatorsByMenuId(String menuId, List<Integer> userRoleIds) {
        List<Operator> operatorList = operatorMapper.queryByMenuId(menuId);
        List<Operator> userOperList = new ArrayList<Operator>();
        List<Integer> userOperIds = roleRightMapper.queryOperIdsByRoleIds(userRoleIds);
        for (Operator oper : operatorList) {
            if (userOperIds.contains(oper.getOperId())) {
                userOperList.add(oper);
            }
        }
        return userOperList;
    }

    @Cacheable(value="operatorCache", key="'OperatorService_' +#root.methodName +'_'+ #example.toJson()")
	@Override
	public List<Operator> queryAllObjByExample(OperatorExample example) {
		return this.operatorMapper.selectByExample(example);
	}

	@Cacheable(value="operatorCache", key="'OperatorService_' +#root.methodName +'_'+ #example.toJson()")
	@Override
	public PageView<Operator> queryObjByPage(OperatorExample example) {
		PageView<Operator> pageView = example.getPageView();
		pageView.setQueryResult(this.operatorMapper.selectByExampleByPage(example));
		return pageView;
	}
}
