package com.ram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ram.security.AppProperties;

@SpringBootApplication
public class Application extends SpringBootServletInitializer
{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		return application.sources(Application.class);
	}
	
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bcryptPasswordEncoder()
	{
		return  new BCryptPasswordEncoder();
	}
	
	@Bean
	public SpringApplicationContext SpringApplicationContext()
	{
		return  new SpringApplicationContext();
	}
	
	@Bean
	public AppProperties AppProperties()
	{
		return  new AppProperties();
	}

}
