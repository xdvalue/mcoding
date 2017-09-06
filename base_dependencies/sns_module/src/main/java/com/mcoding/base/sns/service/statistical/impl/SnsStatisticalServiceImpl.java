package com.mcoding.base.sns.service.statistical.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.base.sns.persistence.statistical.SnsStatisticalMapper;
import com.mcoding.base.sns.service.statistical.SnsStatisticalService;

@Service
public class SnsStatisticalServiceImpl implements SnsStatisticalService {
	
	@Resource
	private SnsStatisticalMapper snsStatisticalMapper;

	@Override
	public PageView<HashMap<String, Object>> newUserForDay(Date startTime, Date endTime, int storeId,
			String iDisplayStart, String iDisplayLength) {
		PageView<HashMap<String, Object>> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		List<HashMap<String, Object>> list = snsStatisticalMapper.newUserForDayByPage(pageView, startTime, endTime, storeId);
		pageView.setQueryResult(list);
		return pageView;
	}

	@Override
	public PageView<HashMap<String, Object>> loginedUserForDay(Date startTime, Date endTime, int storeId,
			String iDisplayStart, String iDisplayLength) {
		PageView<HashMap<String, Object>> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		List<HashMap<String, Object>> list = snsStatisticalMapper.loginedUserForDayByPage(pageView, startTime, endTime, storeId);
		pageView.setQueryResult(list);
		return pageView;
	}



	@Override
	public PageView<HashMap<String, Object>> postCountForDay(Date startTime, Date endTime, int storeId,
			String iDisplayStart, String iDisplayLength) {
		PageView<HashMap<String, Object>> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		List<HashMap<String, Object>> list = snsStatisticalMapper.postCountForDayByPage(pageView, startTime, endTime, storeId);
		pageView.setQueryResult(list);
		return pageView;
	}



	@Override
	public PageView<HashMap<String, Object>> commentCountForDay(Date startTime, Date endTime, int storeId,
			String iDisplayStart, String iDisplayLength) {
		PageView<HashMap<String, Object>> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		List<HashMap<String, Object>> list = snsStatisticalMapper.commentCountForDayByPage(pageView, startTime, endTime, storeId);
		pageView.setQueryResult(list);
		return pageView;
	}

	@Override
	public int postViewCount(Integer storeId) {
		
		return this.snsStatisticalMapper.postViewCount(storeId);
	}

}
