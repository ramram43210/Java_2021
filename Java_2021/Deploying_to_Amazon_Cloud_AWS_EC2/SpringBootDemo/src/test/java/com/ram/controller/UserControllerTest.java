package com.ram.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ram.dto.AddressDTO;
import com.ram.dto.UserDTO;
import com.ram.model.ui.UserRest;
import com.ram.service.impl.UserServiceImpl;

class UserControllerTest
{

	@InjectMocks
	UserController userController;

	@Mock
	UserServiceImpl userService;

	UserDTO userDto;

	private final String USER_ID = "123jijij7";

	@BeforeEach
	void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		userDto = new UserDTO();
		userDto.setFirstName("Arun");
		userDto.setLastName("Kumar");
		userDto.setEmail("arun@yahoo.com");
		userDto.setUserId(USER_ID);
		userDto.setEncryptedPassword("jkjk7878#$$4");
		
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
		
		userDto.setAddresses(addressDTOList);

	}

	@Test
	void testGetUser()
	{
		when(userService.getUserByUserId(anyString())).thenReturn(userDto);
		UserRest userRest = userController.getUser("123jijij7");
		assertNotNull(userRest);
		assertEquals(USER_ID, userRest.getUserId());
		assertEquals(userDto.getFirstName(),userRest.getFirstName());
		assertTrue(userDto.getAddresses().size()==userRest.getAddresses().size());
	}

}
