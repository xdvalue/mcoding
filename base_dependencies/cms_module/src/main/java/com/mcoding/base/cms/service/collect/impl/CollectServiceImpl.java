package com.mcoding.base.cms.service.collect.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.cms.bean.collect.Collect;
import com.mcoding.base.cms.bean.collect.CollectExample;
import com.mcoding.base.cms.persistence.collect.CollectMapper;
import com.mcoding.base.cms.service.collect.CollectService;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;

/**
 * CollectServiceImpl
 * 
 * @author acer
 * 
 */
@Service
public class CollectServiceImpl implements CollectService {

	@Autowired
	protected CollectMapper collectMapper;

	@Override
	public void addObj(Collect collect) {
		collectMapper.insertSelective(collect);
	}

	@Override
	public void deleteObjById(int collectId) {
		collectMapper.deleteByPrimaryKey(collectId);
	}

	@Override
	public void modifyObj(Collect collect) {
		collectMapper.updateByPrimaryKeySelective(collect);
	}

	@Override
	public List<Collect> queryAllObjByExample(CollectExample collectExample) {
		return collectMapper.selectByExample(collectExample);
	}

	@Override
	public Collect queryObjById(int collectId) {
		return collectMapper.selectByPrimaryKey(collectId);
	}

	@Override
	public PageView<Collect> queryObjByPage(CollectExample example) {
		PageView<Collect> pageView = example.getPageView();
		if (pageView == null) {
			pageView = new PageView<Collect>();
			pageView.setPageNo(1);
			pageView.setPageSize(10);
			example.setPageView(pageView);
		}
		List<Collect> list = collectMapper.selectByExampleByPage(example);
		pageView.setQueryResult(list);
		return pageView;
	}

	@Override
	public JsonResult<String> collectOrUncollect(Integer memberId,
			Integer articleId, Integer status, Integer storeId) {
		String code = "00";
		String msg = "ok";

		CollectExample example = new CollectExample();
		example.createCriteria().andArticleIdEqualTo(articleId)
				.andMemberIdEqualTo(memberId).andStoreIdEqualTo(storeId);
		List<Collect> list = collectMapper.selectByExample(example);

		Collect collect = new Collect();
		collect.setArticleId(articleId);
		collect.setMemberId(memberId);
		collect.setStoreId(storeId);

		if (CollectionUtils.isNotEmpty(list)) {
			if (list.get(0).getStatus().equals(status)) {
				code = "-1";
				msg = "不能重复操作。";
			} else {
				collect.setId(list.get(0).getId());
				collectMapper.updateByPrimaryKeySelective(collect);
			}
		} else {
			collectMapper.insertSelective(collect);
		}
		JsonResult<String> result = new JsonResult<String>();
		result.setData(null);
		result.setMsg(msg);
		result.setStatus(code);
		return result;
	}
}
