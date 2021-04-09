package com.ram.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ram.dto.UserDTO;
import com.ram.model.request.UserDetailsRequestModel;
import com.ram.model.ui.UserRest;
import com.ram.service.UserService;

@RestController
@RequestMapping("users")
public class UserController
{
	@Autowired
	private UserService userService;

	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails)
	{
		UserRest userRest = new UserRest();
		
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userDetails, userDTO);
		
		userService.createUser(userDTO);
		BeanUtils.copyProperties(userDTO, userRest);
		return null;
	}

	

}
