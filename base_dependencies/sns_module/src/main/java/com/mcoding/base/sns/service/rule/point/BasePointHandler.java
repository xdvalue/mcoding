package com.mcoding.base.sns.service.rule.point;

import java.util.List;

import com.mcoding.base.point.bean.MemberPointData;
import com.mcoding.base.point.service.MemberPointRuleHandler;
import com.mcoding.base.sns.service.rule.SnsPointSynchronizer;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

public abstract class BasePointHandler<M , P , R > implements MemberPointRuleHandler<M , P , R >{
	
	@Override
	public boolean beforeAddPoint(M memberTarget, P pointSource, R methodResult) {
		return true;
	}

	@Override
	public void afterAddPoint(M memberTarget, P pointSource, R methodResult, List<MemberPointData> pointList) {
		SnsPointSynchronizer snsMemberPointHandler = SpringContextHolder.getOneBean(SnsPointSynchronizer.class);
		
		for(int i=0; i<pointList.size(); i++){
			snsMemberPointHandler.syncToWMall(pointList.get(i));
		}
	}
	
}
