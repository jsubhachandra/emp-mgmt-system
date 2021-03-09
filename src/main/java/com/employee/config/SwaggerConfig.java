package com.employee.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author subhachandra
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	@Bean
	ApiInfo apiInfo() {
		return new ApiInfo("Employee Management System",
				"This project demonstrates enabling companies to hire & onboard their employees internationally, " 
						+ "some restfull api's using spring boot",
				"1.0", null,
				new Contact("Git URL", "https://github.com/jsubhachandra", null),
				"My Personal Portfolio", "https://jsubhachandra.netlify.app/",
				Collections.EMPTY_LIST);
	}
}