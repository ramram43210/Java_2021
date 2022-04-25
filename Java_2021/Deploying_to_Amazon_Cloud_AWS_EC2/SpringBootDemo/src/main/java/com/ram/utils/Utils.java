package com.ram.utils;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.ram.security.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Utils
{
	private final Random RANDOM = new SecureRandom();
	private final String ALPHA_NUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public String generateUserId(int length)
	{
		return generateRandomString(length);
	}

	public String generateAddressId(int length)
	{
		return generateRandomString(length);
	}

	private String generateRandomString(int length)
	{
		StringBuilder stringBuilder = new StringBuilder(length);
		for (int i = 0; i < length; i++)
		{
			stringBuilder.append(ALPHA_NUMERIC.charAt(RANDOM.nextInt(ALPHA_NUMERIC.length())));
		}
		return new String(stringBuilder);
	}

	public boolean hasTokenExpired(String token)
	{
		boolean isTokenExpired = false;
		try
		{
			Claims claims = Jwts.parser().setSigningKey(SecurityConstants.getTokenSecret())
					.parseClaimsJws(token).getBody();
	
			Date tokenExpirationDate = claims.getExpiration();
			Date todayDate = new Date();
	
			isTokenExpired = tokenExpirationDate.before(todayDate);
	
			return isTokenExpired;
		}
		catch(ExpiredJwtException exe)
		{
			return isTokenExpired;
		}

	}

	public String generateEmailVerificationToken(String userId)
	{
		String token = Jwts.builder().setSubject(userId)
				.setExpiration(
						new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret()).compact();

		return token;

	}
}
