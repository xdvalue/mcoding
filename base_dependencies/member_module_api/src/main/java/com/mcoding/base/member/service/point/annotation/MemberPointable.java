package com.mcoding.base.member.service.point.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记可以操作会员的积分，加分或扣分
 * @author hzy
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MemberPointable {
	
	Class<? extends MemberPointRuleHandler> using() ;
	
	/**
	 * 方法中，与会员关联的参数的索引
	 * @return
	 */
	int targetMemberIndex();
	
	/**
	 * 方法中，与积分关联的参数的索引
	 * @return
	 */
	int pointSourceIndex() default -1;

}
