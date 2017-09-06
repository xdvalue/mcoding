package com.mcoding.base.ui.service.dictionary.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.bean.dictionary.DicGroup;
import com.mcoding.base.ui.bean.dictionary.DicGroupExample;
import com.mcoding.base.ui.persistence.dictionary.DicGroupMapper;
import com.mcoding.base.ui.service.dictionary.DicGroupItemService;
import com.mcoding.base.ui.service.dictionary.DicGroupService;

@Service("dicGroupService")
public class DicGroupServiceImpl implements DicGroupService {
	
	@Resource
	protected DicGroupMapper dicGroupMapper;
	
	@Resource
	protected DicGroupItemService dicGroupItemService;

	@CacheEvict(value={"dicGroup"}, allEntries=true)
	@Override
	public void addObj(DicGroup t) {
		this.dicGroupMapper.insertSelective(t);
	}

	@CacheEvict(value={"dicGroup"}, allEntries=true)
	@Transactional
	@Override
	public void deleteObjById(int id) {
		this.dicGroupMapper.deleteByPrimaryKey(id);
		this.dicGroupItemService.deleteItemsByGroupId(id);
	}

	@CacheEvict(value={"dicGroup"}, allEntries=true)
	@Override
	public void modifyObj(DicGroup t) {
		this.dicGroupMapper.updateByPrimaryKeySelective(t);
	}

	@Cacheable(value="dicGroup", key="'DicGroupService_' + #root.methodName + '_' +#id")
	@Override
	public DicGroup queryObjById(int id) {
		return this.dicGroupMapper.selectByPrimaryKey(id);
	}

	@Cacheable(value="dicGroup", key="'DicGroupService_' + #root.methodName + '_'+ #example.toJson()")
	@Override
	public List<DicGroup> queryAllObjByExample(DicGroupExample example) {
		return this.dicGroupMapper.selectByExample(example);
	}

	@Cacheable(value="dicGroup", key="'DicGroupService_' + #root.methodName + '_'+ #example.toJson()")
	@Override
	public PageView<DicGroup> queryObjByPage(DicGroupExample example) {
		PageView<DicGroup> pageView = example.getPageView();
		if (pageView == null) {
			pageView = new PageView<>(1, 10);
			example.setPageView(pageView);
		}
		pageView.setQueryResult(this.dicGroupMapper.selectByExampleByPage(example));
		return pageView;
	}

}
