package com.ram.security;

import com.ram.SpringApplicationContext;

public class SecurityConstants
{
	public static final long EXPIRATION_TIME=864000000; //10 Days
	public static final String TOKEN_PREFIX="Bearer ";
	public static final String HEADER_STRING="Authorization";
	public static final String SIGNUP_URL="/users"; 
	
	public static String getTokenSecret()
	{
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
		return appProperties.getTokenSecret();
	}

}
