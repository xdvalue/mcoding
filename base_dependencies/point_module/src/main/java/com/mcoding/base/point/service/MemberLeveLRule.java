package com.mcoding.base.point.service;

import com.mcoding.base.point.bean.level.MemberLevel;

/**
 * 关于会员级别的规则。<p/>
 * 对于不同的模块，会员级别可能有不用的初始化规则、升级规则。各个模块可实现各自规则，并且在配置文件spring-mlr.xml上做配置
 * @author hzy
 *
 */
public interface MemberLeveLRule {
	
	/**
	 * 会员级别升级规则
	 * @param memberId
	 */
	public void upgrade(int memberId, int moduleType, int storeId);
	
	/**
	 * 为会员创建默认的级别的规则
	 * @param member
	 * @return 
	 */
	public MemberLevel createDefaultLevel(int memberId, Integer moduleType, int storeId);

}
