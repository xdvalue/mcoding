package com.mcoding.base.member.service.member;

import com.mcoding.base.ui.bean.member.IMemberExtInfo;

/**
 * 会员拓展信息的加载器
 * @author hzy
 *
 */
public interface IMemberExtInfoLoader {
	
	/**
	 * 返回拓展信息的类型
	 * @return
	 */
	public String getMemberExtInfoType();
	
	/**
	 * 返回拓展信息
	 * @param memberId
	 * @return
	 */
	public IMemberExtInfo getMemberExtInfo(int memberId);

}
