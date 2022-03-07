package com.ram.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AutherizationFilter extends BasicAuthenticationFilter
{

	public AutherizationFilter(AuthenticationManager authenticationManager)
	{
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		String header = request.getHeader(SecurityConstants.HEADER_STRING);

		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX))
		{
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request)
	{
		String header = request.getHeader(SecurityConstants.HEADER_STRING);
		if (header != null)
		{
			String token = header.replace(SecurityConstants.TOKEN_PREFIX, "");
			String user = Jwts.parser().setSigningKey(SecurityConstants.getTokenSecret())
					.parseClaimsJws(token).getBody().getSubject();
			if(user!=null)
			{
				return new UsernamePasswordAuthenticationToken(user,null,new ArrayList<>());
			}
			return null;
		}
		return null;
	}

}
