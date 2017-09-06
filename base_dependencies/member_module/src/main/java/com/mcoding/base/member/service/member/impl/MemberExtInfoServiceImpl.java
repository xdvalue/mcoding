package com.mcoding.base.member.service.member.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.member.MemberExtInfo;
import com.mcoding.base.member.bean.member.MemberExtInfoExample;
import com.mcoding.base.member.persistence.member.MemberExtInfoMapper;
import com.mcoding.base.member.service.member.MemberExtInfoService;

@Service("memberExtInfoService")
public class MemberExtInfoServiceImpl implements MemberExtInfoService {

	@Resource
	protected MemberExtInfoMapper memberExtInfoMapper;

	@Override
	public void addObj(MemberExtInfo t) {
		this.memberExtInfoMapper.insert(t);
	}

	@Override
	public void deleteObjById(int id) {
		this.memberExtInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void modifyObj(MemberExtInfo t) {
		this.memberExtInfoMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public MemberExtInfo queryObjById(int id) {
		return this.memberExtInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<MemberExtInfo> queryAllObjByExample(MemberExtInfoExample example) {
		return this.memberExtInfoMapper.selectByExample(example);
	}

	@Override
	public PageView<MemberExtInfo> queryObjByPage(MemberExtInfoExample example) {
		PageView<MemberExtInfo> pageView = example.getPageView();
		if (pageView == null) {
			pageView = new PageView<>(1, 10);
			example.setPageView(pageView);
		}
		pageView.setQueryResult(this.memberExtInfoMapper.selectByExampleByPage(example));
		return pageView;
	}

}
