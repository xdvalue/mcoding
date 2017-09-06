package com.mcoding.base.sns.service.favorite.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.base.sns.bean.favorite.SnsFavorite;
import com.mcoding.base.sns.bean.favorite.SnsFavoriteExample;
import com.mcoding.base.sns.persistence.favorite.SnsFavoriteMapper;
import com.mcoding.base.sns.service.favorite.SnsFavoriteService;
import com.mcoding.base.sns.service.post.SnsPostService;

@Service
public class SnsFavoriteServiceImpl implements SnsFavoriteService {

	@Resource
	private SnsFavoriteMapper snsFavoriteMapper ;

	@Resource
	protected SnsPostService snsPostService;

	@Override
	public void addObj(SnsFavorite t) {
		this.snsFavoriteMapper.insertSelective(t);
	}

	@Override
	public void deleteObjById(int id) {
		this.snsFavoriteMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void modifyObj(SnsFavorite t) {
		if (t.getId() == null || t.getId() <= 0) {
			throw new NullPointerException("id为空，无法修改");
		}

		this.snsFavoriteMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public SnsFavorite queryObjById(int id) {
		return this.snsFavoriteMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SnsFavorite> queryAllObjByExample(SnsFavoriteExample example) {
		return this.snsFavoriteMapper.selectByExample(example);
	}

	@Override
	public PageView<SnsFavorite> queryObjByPage(SnsFavoriteExample example) {
		PageView<SnsFavorite> pageView = example.getPageView();
		if (pageView == null) {
			pageView = new PageView<>(1, 10);
			example.setPageView(pageView);
		}

		pageView.setQueryResult(this.snsFavoriteMapper.selectByExampleByPage(example));
		return pageView;
	}

}
