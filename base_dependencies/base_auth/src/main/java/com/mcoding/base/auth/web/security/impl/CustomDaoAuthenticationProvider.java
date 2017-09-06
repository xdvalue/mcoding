package com.mcoding.base.auth.web.security.impl;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 用户名密码验证器，验证提交的用户名密码是否正确
 * @author hzy
 *
 */
public class CustomDaoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private PasswordEncoder passwordEncoder;
	
	private UserDetailsService userDetailsService;
	
	private SaltSource saltSource;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		Object salt = null;

		if (this.saltSource != null) {
			salt = this.saltSource.getSalt(userDetails);
		}

		if (authentication.getCredentials() == null) {
			logger.debug("Authentication failed: no credentials provided");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"));
		}

		String presentedPassword = authentication.getCredentials().toString();
		if (salt != null) {
			//add salt to password
			presentedPassword = presentedPassword + salt.toString();
		}
		
		if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
			logger.debug("Authentication failed: password does not match stored value");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"));
		}
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		UserDetails loadedUser;

		try {
			loadedUser = this.getUserDetailsService().loadUserByUsername(username);
			
		}catch (UsernameNotFoundException notFound) {
			throw notFound;
			
		}catch (Exception repositoryProblem) {
			throw new InternalAuthenticationServiceException(
					repositoryProblem.getMessage(), repositoryProblem);
		}

		if (loadedUser == null) {
			throw new InternalAuthenticationServiceException(
					"UserDetailsService returned null, which is an interface contract violation");
		}
		return loadedUser;
	}
	
	public void setSaltSource(SaltSource saltSource) {
		this.saltSource = saltSource;
	}

	protected SaltSource getSaltSource() {
		return saltSource;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	protected UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
}
