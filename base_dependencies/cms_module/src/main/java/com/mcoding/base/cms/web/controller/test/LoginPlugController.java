package com.mcoding.base.cms.web.controller.test;

import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.auth.bean.User;
import com.mcoding.base.auth.bean.UserExample;
import com.mcoding.base.auth.bean.UserRole;
import com.mcoding.base.auth.service.UserRoleService;
import com.mcoding.base.auth.service.UserService;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

@Controller
public class LoginPlugController {
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	private UserRoleService rightService;
	
	@RequestMapping("front/loginByHk")
	public ModelAndView loginByHk(HttpSession session, String elsAccount, String elsSubAccount) {
		if(StringUtils.isBlank(elsAccount)){
			elsAccount = (String) session.getAttribute("elsAccount");// 账号
		}
		if (StringUtils.isBlank(elsSubAccount)) {
			elsSubAccount = (String) session.getAttribute("elsSubAccount");// 账号
		}
		
		if (StringUtils.isBlank(elsAccount) || StringUtils.isBlank(elsSubAccount)) {
			throw new HTTPException(403);
		}
		
		String loginName = elsAccount + "_" + elsSubAccount;
		
		UserExample userExample = new UserExample();
		userExample.createCriteria().andLoginNameEqualTo(loginName);
		
		User user = this.userService.findusername(loginName);
		if (user == null) {
			user = new User();
			user.setLoginName(loginName);
			user.setPassword("123456");
			this.userService.addObj(user);
			
			UserRole userRole = new UserRole();
			userRole.setRoleId(2);
			userRole.setUserId(user.getUserId());
			rightService.addObj(userRole);
		}
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginName, "123456");
		token.setDetails(user);
		AuthenticationManager authenticationManager = SpringContextHolder.getBean("authenticationManager");
		Authentication authenticatedUser = authenticationManager.authenticate(token);
		
		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        
        return new ModelAndView("redirect:/index.html");
	}
	
	
}
