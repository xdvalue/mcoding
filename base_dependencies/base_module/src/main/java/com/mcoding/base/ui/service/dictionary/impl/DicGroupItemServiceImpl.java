package com.mcoding.base.ui.service.dictionary.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.bean.dictionary.DicGroup;
import com.mcoding.base.ui.bean.dictionary.DicGroupExample;
import com.mcoding.base.ui.bean.dictionary.DicGroupItem;
import com.mcoding.base.ui.bean.dictionary.DicGroupItemExample;
import com.mcoding.base.ui.persistence.dictionary.DicGroupItemMapper;
import com.mcoding.base.ui.service.dictionary.DicGroupItemService;
import com.mcoding.base.ui.service.dictionary.DicGroupService;

@Service("dicGroupItemService")
public class DicGroupItemServiceImpl implements DicGroupItemService {

	@Resource
	protected DicGroupItemMapper dicGroupItemMapper;
	
	@Resource
	protected DicGroupService dicGroupService;
	
	@CacheEvict(value="dicGroupItem", allEntries=true)
	@Override
	public void addObj(DicGroupItem t) {
		this.dicGroupItemMapper.insertSelective(t);
	}

	@CacheEvict(value="dicGroupItem",  allEntries=true)
	@Override
	public void deleteObjById(int id) {
		this.dicGroupItemMapper.deleteByPrimaryKey(id);
	}
	
	@CacheEvict(value="dicGroupItem", allEntries=true)
	@Override
	public void deleteItemsByGroupId(int dicGroupId) {
		DicGroupItemExample example = new DicGroupItemExample();
		example.createCriteria().andGroupIdEqualTo(dicGroupId);
		
		this.dicGroupItemMapper.deleteByExample(example);
	}

	@CacheEvict(value="dicGroupItem",  allEntries=true)
	@Override
	public void modifyObj(DicGroupItem t) {
		this.dicGroupItemMapper.updateByPrimaryKeySelective(t);
	}

	@Cacheable(value="dicGroupItem", key="'DicGroupItemService_' +#root.methodName + '_' + #id")
	@Override
	public DicGroupItem queryObjById(int id) {
		return this.dicGroupItemMapper.selectByPrimaryKey(id);
	}

	@Cacheable(value="dicGroupItem", key="'DicGroupItemService_' +#root.methodName + '_' + #example.toJson()")
	@Override
	public List<DicGroupItem> queryAllObjByExample(DicGroupItemExample example) {
		return this.dicGroupItemMapper.selectByExample(example);
	}

	@Cacheable(value="dicGroupItem", key="'DicGroupItemService_' +#root.methodName +'_'+ #example.toJson()")
	@Override
	public PageView<DicGroupItem> queryObjByPage(DicGroupItemExample example) {
		PageView<DicGroupItem> pageView = example.getPageView();
		if (pageView == null) {
			pageView = new PageView<>(1, 10);
			example.setPageView(pageView);
		}
		pageView.setQueryResult(this.dicGroupItemMapper.selectByExampleByPage(example));
		return pageView;
	}

	@Cacheable(value="dicGroupItem", key="'DicGroupItemService_' +#root.methodName +'_'+ #dicGroupCode")
	@Override
	public List<DicGroupItem> queryItemsByGroupCode(String dicGroupCode) {
		DicGroupExample dicGroupExample = new DicGroupExample();
		dicGroupExample.createCriteria().andCodeEqualTo(dicGroupCode);
		
		List<DicGroup> groupList = this.dicGroupService.queryAllObjByExample(dicGroupExample);
		if (CollectionUtils.isEmpty(groupList)) {
			return null;
		}
		
		if (groupList.size() >1) {
			throw new RuntimeException("数据异常，dicGroupCode下,数据多余一条");
		}
		
		DicGroup dicGroup = groupList.get(0);
		DicGroupItemExample dicGroupItemExample = new DicGroupItemExample();
		dicGroupItemExample.createCriteria().andGroupIdEqualTo(dicGroup.getId());
		
		return this.queryAllObjByExample(dicGroupItemExample);
	}

	@Cacheable(value="dicGroupItem", key="'DicGroupItemService_' +#root.methodName +'_'+ #dicGroupCode +'_' +#dicGroupItemCode")
	@Override
	public DicGroupItem queryItems(String dicGroupCode, String dicGroupItemCode) {
		if (StringUtils.isBlank(dicGroupCode) || StringUtils.isBlank(dicGroupItemCode)) {
			throw new NullPointerException("dicGroupCode, dicGroupItemCode参数都不能为空");
		}
		
		List<DicGroupItem> list = this.queryItemsByGroupCode(dicGroupCode);
		for(int i=0; CollectionUtils.isNotEmpty(list) && i<list.size(); i++){
			DicGroupItem dicGroupItem = list.get(i);
			if (dicGroupItem.getCode().equals(dicGroupItemCode)) {
				return dicGroupItem;
			}
		}
		
		return null;
	}

	@Override
	public DicGroupItem queryItemByValue(String dicGroupCode, String dicGroupItemValue) {
		if (StringUtils.isBlank(dicGroupCode) || StringUtils.isBlank(dicGroupItemValue)) {
			throw new NullPointerException("dicGroupCode, dicGroupItemValue参数都不能为空");
		}
		
		List<DicGroupItem> list = this.queryItemsByGroupCode(dicGroupCode);
		for(int i=0; i<list.size(); i++){
			DicGroupItem dicGroupItem = list.get(i);
			if (dicGroupItem.getValue().equals(dicGroupItemValue)) {
				return dicGroupItem;
			}
		}
		
		return null;
	}

}
