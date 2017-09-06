package com.mcoding.base.ui.plugin.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {

	@Bean 
	public Docket customImplementation() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo(
				"基础框架", 
				"目前基础框架,具有的功能包括，用户管理，菜单管理，数据字典管理。接入redis，swagger自动api等插件", 
				"0.0.1", "",  "",  "", "");
		return apiInfo;
	}

}
