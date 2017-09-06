package com.mcoding.base.cms.service.template.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.cms.bean.template.Template;
import com.mcoding.base.cms.bean.template.TemplateExample;
import com.mcoding.base.cms.persistence.template.TemplateMapper;
import com.mcoding.base.cms.service.template.TemplateService;
import com.mcoding.base.core.PageView;

/**
 * TemplateServiceImpl
 * 
 * @author acer
 * 
 */
@Service
public class TemplateServiceImpl implements TemplateService {

	@Autowired
	protected TemplateMapper templateMapper;

	@Override
	public void addObj(Template template) {
		templateMapper.insertSelective(template);
	}

	@Override
	public void deleteObjById(int templateId) {
		templateMapper.deleteByPrimaryKey(templateId);
	}

	@Override
	public void modifyObj(Template template) {
		templateMapper.updateByPrimaryKeySelective(template);
	}

	@Override
	public List<Template> queryAllObjByExample(TemplateExample example) {
		return templateMapper.selectByExample(example);
	}

	@Override
	public Template queryObjById(int id) {
		return templateMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageView<Template> queryObjByPage(TemplateExample example) {
		PageView<Template> pageView = example.getPageView();
		if (pageView == null) {
			pageView = new PageView<Template>();
			pageView.setPageNo(1);
			pageView.setPageSize(10);
			
			example.setPageView(pageView);
		}
		List<Template> list = templateMapper
				.selectByExampleByPage(example);
		pageView.setQueryResult(list);
		return pageView;
	}

}
