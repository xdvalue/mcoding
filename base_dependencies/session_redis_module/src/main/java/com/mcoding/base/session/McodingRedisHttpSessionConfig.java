package com.mcoding.base.session;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.mcoding.base.ui.utils.spring.SpringContextHolder;

@EnableRedisHttpSession
public class McodingRedisHttpSessionConfig {

	@Bean
	public JedisConnectionFactory connectionFactory() {

		return SpringContextHolder.getBean("httpSessionRedisConnectionFactory");
	}

}
