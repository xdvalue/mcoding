package com.mcoding.base.dst.service.income;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.dst.bean.income.DstIncomeMember;
import com.mcoding.base.dst.bean.income.DstIncomeMemberExample;
import com.mcoding.base.member.bean.member.Member;

public interface DstIncomeMemberService extends BaseService<DstIncomeMember, DstIncomeMemberExample> {
	
	/**
	 * 添加总佣金活积分
	 * @param incomeTotal
	 * @param member
	 * @return
	 */
	public DstIncomeMember addIncome(int incomeTotal, int pointTotal, Member member);
}