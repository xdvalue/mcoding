package com.mcoding.base.member.service.point.annotation;

import java.util.List;

import com.mcoding.base.member.bean.point.MemberPointRecord;
import com.mcoding.base.member.bean.point.vo.MemberPointData;

public interface MemberPointRuleHandler<M extends Object, P extends Object, R extends Object> {
	
	/**
	 * 添加修改的积分
	 * @param memberTarget 与会员关联的参数
	 * @param pointSource 与积分关联的参数
	 * @param methodResult 操作结果
	 * @return
	 */
//	public List<MemberPointRecord> addPoint(M memberTarget, P pointSource, R methodResult);
	public List<MemberPointData> addPoint(M memberTarget, P pointSource, R methodResult);
	
	/**
	 * 加积分前的处理，如果返回 false，则停止加分，如果返回true，则继续执行加分
	 * @param memberTarget
	 * @param pointSource
	 * @param methodResult
	 * @return
	 */
	public boolean beforeAddPoint(M memberTarget, P pointSource, R methodResult);
	
	/**
	 * 加分之后的操作
	 * @param memberTarget
	 * @param pointSource
	 * @param methodResult
	 * @param pointList
	 */
	public void afterAddPoint(M memberTarget, P pointSource, R methodResult, List<MemberPointData> pointList);
}
