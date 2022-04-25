package com.ram.ws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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
public class SwaggerConfig
{

	Contact contact = new Contact("Ram", "http://localhost:8080/user-app-ws/v2/api-docs", "ram@yahoo.com");

	List<VendorExtension> vendorExtenstions = new ArrayList<>();

	ApiInfo apiInfo = new ApiInfo("User RESTful Webservice documentation",
			"User WebService EndPoints", "1.0", "http://localhost:8080/user-app-ws/v2/api-docs",
			contact, "Apache 2.0", "https://www.apache.org/licenses/LICENSE-2.0",
			vendorExtenstions);

	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.protocols(new HashSet<>(Arrays.asList("http", "https"))).apiInfo(apiInfo).select()
				.apis(RequestHandlerSelectors.basePackage("com.ram.controller"))
				.paths(PathSelectors.any()).build();
	}
}
