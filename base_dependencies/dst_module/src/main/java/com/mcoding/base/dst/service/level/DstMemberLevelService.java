package com.mcoding.base.dst.service.level;

import java.util.List;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.dst.bean.level.DstLevel;
import com.mcoding.base.dst.bean.level.DstMemberLevel;
import com.mcoding.base.dst.bean.level.DstMemberLevelExample;
import com.mcoding.base.member.bean.member.Member;

public interface DstMemberLevelService extends BaseService<DstMemberLevel, DstMemberLevelExample> {
	
	/**
	 * 根据上级，申请当经销商
	 * @param memberId
	 * @param parentMemberId
	 */
	public DstMemberLevel applyForDistributor(Member member, Member parentMember);
	
	/**
	 * 根据级别，申请当经销商
	 * @param member
	 * @param dstLevel
	 */
	public DstMemberLevel applyForDistributor(Member member, DstLevel dstLevel);
	
	/**
	 * 招收新的下线会员(非经销商)
	 * @param member
	 * @param parentMember
	 * @return
	 */
	public DstMemberLevel recruitNewMember(Member member, Member parentMember);
	
	/**
	 * 查找会员的经销商级别
	 * @param memberId
	 * @return
	 */
	public DstMemberLevel queryByMemberId(int memberId);
	
	/**
	 * 查询上级经销商
	 * @param memberId
	 * @return
	 */
	public Member queryParentDistributor(int memberId);
	
	/**
	 * 查询下一级的下线，包括经销商和普通会员
	 * @param memberId
	 * @return
	 */
	public List<Member> queryChildrenDistributor(int memberId);
	
	
}