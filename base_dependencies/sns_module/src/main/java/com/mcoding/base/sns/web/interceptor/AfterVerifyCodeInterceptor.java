package com.mcoding.base.sns.web.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.point.bean.MemberPointData;
import com.mcoding.base.point.service.level.MemberLevelService;
import com.mcoding.base.sns.service.rule.SnsPointSynchronizer;
import com.mcoding.base.sns.utils.SnsPointType;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

/**
 * 验证手机号码之后的拦截器
 * @author hzy
 *
 */
public class AfterVerifyCodeInterceptor extends HandlerInterceptorAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(AfterVerifyCodeInterceptor.class);
	
	@Resource
	protected MemberLevelService memberLevelService;
	
	private static ThreadLocal<Member> threadLocal = new ThreadLocal<>();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Member member = (Member) request.getSession().getAttribute("member");
		if (member != null) {
			this.setMember(member);
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
		
		try {
			Member before = this.getMember();
			Member after = (Member) request.getSession().getAttribute("member");
			
			if(StringUtils.isBlank(before.getMobilephone())
					&& StringUtils.isNotBlank(after.getMobilephone())){
				
				int point = 10;
				
				MemberPointData pointData = new MemberPointData();
				pointData.setMember(after);
				pointData.setPoint(point);
				pointData.setPointType(SnsPointType.VERIFY_PHONE);
				pointData.setStoreId(StoreUtils.getStoreFromThreadLocal().getId());
				
				SnsPointSynchronizer snsMemberPointHandler = SpringContextHolder.getOneBean(SnsPointSynchronizer.class);
				snsMemberPointHandler.addPoint(pointData);
			}
			
		} catch (Exception e) {
			logger.error("=======加会员积分异常===========");
			logger.error(e.getMessage());
		}
	}
	
	private void setMember(Member member) {
		threadLocal.set(member);
	}
	
	private Member getMember(){
		return threadLocal.get();
	}

}
