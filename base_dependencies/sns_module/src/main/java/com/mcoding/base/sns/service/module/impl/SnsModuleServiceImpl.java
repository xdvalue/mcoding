package com.mcoding.base.sns.service.module.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.base.sns.bean.module.SnsModule;
import com.mcoding.base.sns.bean.module.SnsModuleExample;
import com.mcoding.base.sns.persistence.module.SnsModuleMapper;
import com.mcoding.base.sns.service.module.SnsModuleService;

@Service("snsModuleService")
public class SnsModuleServiceImpl implements SnsModuleService {

	@Resource
	private SnsModuleMapper snsModuleMapper;

	@CacheEvict(value={"snsModule"}, allEntries=true)
    @Override
    public void addObj(SnsModule t) {
        this.snsModuleMapper.insertSelective(t);
    }

    @CacheEvict(value={"snsModule"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.snsModuleMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"snsModule"}, allEntries=true)
    @Override
    public void modifyObj(SnsModule t) {
        if (t.getId() == null || t.getId() ==0) {
        }
        this.snsModuleMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="snsModule", key="'SnsModuleService_' + #root.methodName + '_' +#id")
    @Override
    public SnsModule queryObjById(int id) {
        return this.snsModuleMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="snsModule", key="'SnsModuleService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<SnsModule> queryAllObjByExample(SnsModuleExample example) {
        return this.snsModuleMapper.selectByExample(example);
    }

    @Cacheable(value="snsModule", key="'SnsModuleService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<SnsModule> queryObjByPage(SnsModuleExample example) {
        PageView<SnsModule> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.snsModuleMapper.selectByExampleByPage(example));
        return pageView;
    }

    @Cacheable(value="snsModule", key="'SnsModuleService_' + #root.methodName + '_'+ #storeId+ '_' + #moduleId")
	@Override
	public List<SnsModule> queryChildModulesById(int storeId, int moduleId) {
		SnsModuleExample allExample = new SnsModuleExample();
		allExample.createCriteria().andStoreIdEqualTo(storeId);
		List<SnsModule> sourceModules = snsModuleMapper.selectByExample(allExample);
		System.out.println(sourceModules);
		List<SnsModule> targetModules = new ArrayList<>();
		System.out.println(targetModules);
		if (CollectionUtils.isNotEmpty(sourceModules)) {
			getRealationChildModuleById(moduleId, sourceModules, targetModules);
		}

		return targetModules;
	}

	private void getRealationChildModuleById(int id, List<SnsModule> allModules, List<SnsModule> targetModules) {
		List<SnsModule> childModules = getChildModulesById(id, allModules);
		if (CollectionUtils.isEmpty(childModules)) {
			return;
		} else {
			targetModules.addAll(childModules);
			for (SnsModule module : childModules) {
				getRealationChildModuleById(module.getId(), allModules, targetModules);
			}
		}
	}

	private List<SnsModule> getChildModulesById(int id, List<SnsModule> allModules) {
		List<SnsModule> childModules = new ArrayList<>();
		for (SnsModule module : allModules) {
			if (module.getParentId() == id) {
				childModules.add(module);
			}
		}
		return childModules;
	}

	@CacheEvict(value={"snsModule"}, allEntries=true)
	@Override
	public void addPostCountNum(int moduleId, int addNum) {
		this.snsModuleMapper.addPostNumById(moduleId, addNum);
	}
}
