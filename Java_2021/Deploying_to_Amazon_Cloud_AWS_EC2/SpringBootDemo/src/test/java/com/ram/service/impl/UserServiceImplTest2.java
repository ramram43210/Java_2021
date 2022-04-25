package com.ram.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ram.dto.AddressDTO;
import com.ram.dto.UserDTO;
import com.ram.entity.UserEntity;
import com.ram.repository.UserRepository;
import com.ram.utils.Utils;

class UserServiceImplTest2
{

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;
	
	@Mock
	Utils utils;

	@Mock
	BCryptPasswordEncoder bcryptPasswordEncoder;
	

	UserEntity userEntity;
	

	@BeforeEach
	void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		
		userEntity = new UserEntity();
		userEntity.setId(1L);
		userEntity.setFirstName("Arun");
		userEntity.setEmail("Arun@yahoo.com");
		userEntity.setLastName("P");
		userEntity.setUserId("90908998");
	}

	@Test
	void testGetUser()
	{

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
	
	@Test
	void testCreateUser()
	{

		when(userRepository.findByEmail(anyString())).thenReturn(null);
		when(utils.generateUserId(30)).thenReturn("8787878786767676767676767");
		when(utils.generateAddressId(30)).thenReturn("67676huyuhj67676jhhghg7");
		when(bcryptPasswordEncoder.encode(anyString())).thenReturn("%$%^%^^%^^%^%$%$%$&");
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
		
		AddressDTO billingAddressDTO = new AddressDTO();
		billingAddressDTO.setCity("Bangalore");
		billingAddressDTO.setCountry("India");
		billingAddressDTO.setStreetName("123 West");
		billingAddressDTO.setPostalCode("560098");
		billingAddressDTO.setType("Billing");
		
		AddressDTO shipingAddressDTO = new AddressDTO();
		shipingAddressDTO.setCity("Chennai");
		shipingAddressDTO.setCountry("India");
		shipingAddressDTO.setStreetName("123 South");
		shipingAddressDTO.setPostalCode("787878");
		shipingAddressDTO.setType("Shipping");
		 
		List<AddressDTO> addressDTOList = new ArrayList<>();
		addressDTOList.add(billingAddressDTO);
		addressDTOList.add(shipingAddressDTO);
		
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName("Arun");
		userDTO.setEmail("Arun@yahoo.com");
		userDTO.setLastName("P");
		userDTO.setUserId("90908998");
		userDTO.setAddresses(addressDTOList);
	
		UserDTO storedUserDetails = userService.createUser(userDTO);
		
		assertNotNull(storedUserDetails);
		assertEquals(userEntity.getFirstName(), storedUserDetails.getFirstName());
		verify(utils,times(2)).generateAddressId(30);
		
	}
	
	@Test
	void testCreateUser_RuntimeException()
	{

		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);		
		when(utils.generateUserId(30)).thenReturn("8787878786767676767676767");
		when(utils.generateAddressId(30)).thenReturn("67676huyuhj67676jhhghg7");
		when(bcryptPasswordEncoder.encode(anyString())).thenReturn("%$%^%^^%^^%^%$%$%$&");
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
		
		AddressDTO billingAddressDTO = new AddressDTO();
		billingAddressDTO.setCity("Bangalore");
		billingAddressDTO.setCountry("India");
		billingAddressDTO.setStreetName("123 West");
		billingAddressDTO.setPostalCode("560098");
		billingAddressDTO.setType("Billing");
		
		AddressDTO shipingAddressDTO = new AddressDTO();
		shipingAddressDTO.setCity("Chennai");
		shipingAddressDTO.setCountry("India");
		shipingAddressDTO.setStreetName("123 South");
		shipingAddressDTO.setPostalCode("787878");
		shipingAddressDTO.setType("Shipping");
		 
		List<AddressDTO> addressDTOList = new ArrayList<>();
		addressDTOList.add(billingAddressDTO);
		addressDTOList.add(shipingAddressDTO);
		
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName("Arun");
		userDTO.setEmail("Arun@yahoo.com");
		userDTO.setLastName("P");
		userDTO.setUserId("90908998");
		userDTO.setAddresses(addressDTOList);
	

		assertThrows(RuntimeException.class, () -> {
		
			userService.createUser(userDTO);

		});
	}


}
