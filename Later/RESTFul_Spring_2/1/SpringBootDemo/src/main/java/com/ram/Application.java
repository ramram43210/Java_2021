package com.ram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ram.security.AppProperties;

@SpringBootApplication
public class Application
{

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
