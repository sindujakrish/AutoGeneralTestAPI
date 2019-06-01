package com.autogeneral.test.api.AutoGeneralTestAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.autogeneral.test.api.AutoGeneralTestAPI.controller.TaskController;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * The class which holds Swagger documentation configuration for the application.
 *
 */
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
@ComponentScan(basePackageClasses = TaskController.class)
@Configuration
public class SwaggerConfig {
	
	private static final String SWAGGER_API_VERSION = "1.0";
	private static final String LICENSE = "License";
	private static final String title = "AutoGeneral Test API";
	private static final String description = "Sample Test APIs to join Auto General";
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(title)
				.description(description)
				.license(LICENSE)
				.version(SWAGGER_API_VERSION)
				.build();
	}
	
	@Bean
	public Docket productsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.apiInfo(apiInfo())
				.pathMapping("/")
				.select()
	            .paths(Predicates.not(PathSelectors.regex("/error.*")))
				.build();
	}
}
