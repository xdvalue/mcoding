package com.mcoding.base.member.service.point;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.bean.point.MemberPointRecord;
import com.mcoding.base.member.bean.point.vo.MemberPointData;
import com.mcoding.base.member.service.level.MemberLevelService;
import com.mcoding.base.member.service.point.annotation.MemberPointRuleHandler;
import com.mcoding.base.member.service.point.annotation.MemberPointable;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

@Aspect
@Component
public class MemberPointManager {

	private Logger logger = LoggerFactory.getLogger(MemberPointManager.class);
	
	@Resource
	protected MemberLevelService memberLevelService;
	

	@Around(value="@annotation(memberPointable)", argNames="memberPointable")
	public void addPoint( final ProceedingJoinPoint joinPoint, final MemberPointable memberPointable)
			throws Throwable {
		logger.info("before addPoint");
		
		final Object result = joinPoint.proceed();

		// TODO 改为通过线程池获取线程里面操作
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					
					MemberPointManager.this.handleAddPoint(joinPoint, result, memberPointable);
					
				} catch (NoSuchMethodException e) {
					logger.error(e.getMessage());
				} catch (SecurityException e) {
					logger.error(e.getMessage());
				}
			}
		});
		thread.start();

//		return result;

	}
	
	private void handleAddPoint(JoinPoint joinPoint, Object result, MemberPointable memberPointable)
			throws NoSuchMethodException, SecurityException {
		// 1、获取被通知的方法
		Object[] args = joinPoint.getArgs();
		
		// 2、获取注解中的参数
		Class handlerClass = memberPointable.using();
		int mIndex = memberPointable.targetMemberIndex();
		int pIndex = memberPointable.pointSourceIndex();

		// 3、获取相应的参数
		if(mIndex < 0 || mIndex >args.length){
			throw new IllegalArgumentException("@MemberPointable 注解中 targetMemberIndex, 配置异常");
		}
		Object memberTarget = args[mIndex];
		
		Object pointSource = null;
		if (pIndex >=0 && pIndex < args.length) {
			pointSource = args[pIndex];
		}
		
		// 4、把参数注入处理器，并进行处理,获取积分列表
		Object bean = SpringContextHolder.getOneBean(handlerClass);
		MemberPointRuleHandler handler = (MemberPointRuleHandler) bean;
		
		if (!handler.beforeAddPoint(memberTarget, pointSource, result)) {
			return;
		}
		
//        List<MemberPointRecord> pointList = handler.addPoint(memberTarget, pointSource, result);
		List<MemberPointData> pointList = handler.addPoint(memberTarget, pointSource, result);
        
        if(CollectionUtils.isEmpty(pointList)){
        	return;
        }
        
        //5、记录积分总数
        for(MemberPointData point : pointList){
        	this.memberLevelService.addPoint(point);
        }
        
        handler.afterAddPoint(memberTarget, pointSource, result, pointList);
	}
	
}
