package com.satyait.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket createSwaggerDocket() 
	{
		return new Docket(DocumentationType.SWAGGER_2) 
				.select()
				.apis(RequestHandlerSelectors
						.basePackage("com.satyait.restController")) 
				.paths(PathSelectors.regex("/student.*"))
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Student Application", "SAMPLE APP", "3.2GA", 
				"https://nareshit.in/",
				new Contact("SATYA", "https://www.facebook.com/satyaranjanpatra/",
						"dev.satyaranjan@gmail.com"),
				"NIT Licence", "https://nareshit.in/", 
				new ArrayList<VendorExtension>());
	}
}
