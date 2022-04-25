package com.ram.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UtilsTest
{

	@Autowired
	Utils utils;
	
	@BeforeEach
	void setUp() throws Exception
	{
	}

	@Test
	void testGenerateUserId()
	{
		String userId = utils.generateUserId(10);
		assertNotNull(userId);
		assertTrue(userId.length() == 10);
	}

	@Test
	void testHasTokenNotExpired()
	{
		String token = utils.generateEmailVerificationToken("jkkuihu");
		assertNotNull(token);
		boolean isTokenExpired = utils.hasTokenExpired(token);
		assertFalse(isTokenExpired);
	}
	
	@Test
	void testHasTokenExpired()
	{
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcnVuQHlhaG9vLmNvbSIsImV4cCI6MTYzNzg5NTgwNX0.jrnpIvvACTKFTGg2_eCdV2LktAqDUmQSov4xQ91BmxIo7ZAFzBf65cEMF1rvDlZ9wVcYV_mJKOAYgjqkJW437w";
		boolean isTokenExpired = utils.hasTokenExpired(token);
		assertTrue(isTokenExpired);
	}
}
