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

class UserServiceImplTest
{
	
	@InjectMocks // This allows to inject Mock objects.
	UserServiceImpl userServiceImpl;

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
		/*
		 *  This is needed for Mockito to be able to instantiate the Mock Objects
		 *  and Inject into the userServiceImpl object
		 */
		MockitoAnnotations.initMocks(this);
		
		userEntity = new UserEntity();
		userEntity.setId(1L);
		userEntity.setFirstName("Arun");
		userEntity.setLastName("P");
		userEntity.setEmail("Arun@yahoo.com");
		userEntity.setUserId("90908998");
	}

	@Test
	void testGetUser()
	{
		
		// Fake the findByEmail method call using mocked userRepository object
		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);

		UserDTO userDTO = userServiceImpl.getUser("Arun@yahoo.com");

		assertNotNull(userDTO);
		assertEquals("Arun", userDTO.getFirstName());
	}
	
	@Test
	void testGetUser_UsernameNotFoundException()
	{

		// Fake the findByEmail method call using mocked userRepository object
		when(userRepository.findByEmail(anyString())).thenReturn(null);

		assertThrows(UsernameNotFoundException.class, () -> {

			userServiceImpl.getUser("Arun@yahoo.com");

		});
	}
	
	@Test
	void testCreateUser()
	{

		// Fake the method calls using mocked objects
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
	
		UserDTO storedUserDetails = userServiceImpl.createUser(userDTO);
		
		assertNotNull(storedUserDetails);
		assertEquals(userEntity.getFirstName(), storedUserDetails.getFirstName());
		verify(utils,times(2)).generateAddressId(30);
		
	}
	
	@Test
	void testCreateUser_RuntimeException()
	{

		// Fake the method calls using mocked objects	
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
		
			userServiceImpl.createUser(userDTO);

		});
	}

}
