package com.ram.ws;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer
{

	//@Override
	//public void addCorsMappings(CorsRegistry registry)
	//{
		//registry.addMapping("/users").allowedMethods("POST","GET").allowedOrigins("http://localhost:9090");
		//registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
	//}

}
