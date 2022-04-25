package com.ram.service.impl;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ram.dto.UserDTO;
import com.ram.entity.UserEntity;
import com.ram.repository.UserRepository;

class UserServiceImplTest
{

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;

	@BeforeEach
	void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetUser()
	{

		UserEntity userEntity = new UserEntity();
		userEntity.setId(1L);
		userEntity.setFirstName("Arun");
		userEntity.setLastName("P");
		userEntity.setUserId("90908998");

		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);

		UserDTO userDTO = userService.getUser("Arun@yahoo.com");

		assertNotNull(userDTO);
		assertEquals("Arun", userDTO.getFirstName());

	}

	@Test
	void testGetUser_UsernameNotFoundException()
	{

		when(userRepository.findByEmail(anyString())).thenReturn(null);

		assertThrows(UsernameNotFoundException.class, () -> {

			userService.getUser("Arun@yahoo.com");

		});
	}

}
