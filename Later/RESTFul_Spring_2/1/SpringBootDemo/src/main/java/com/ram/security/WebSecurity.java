package com.ram.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ram.service.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter
{
	private final UserService userDetailsService;
	private final BCryptPasswordEncoder bcryptPasswordEncoder;

	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bcryptPasswordEncoder)
	{
		this.userDetailsService = userDetailsService;
		this.bcryptPasswordEncoder = bcryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.POST, SecurityConstants.SIGNUP_URL).permitAll().anyRequest()
				.authenticated().and().addFilter(new AuthenticationFilter(authenticationManager()));
		super.configure(http);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService).passwordEncoder(bcryptPasswordEncoder);
		super.configure(auth);
	}

}
