package com.ram.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ram.dto.UserDTO;
import com.ram.exception.UserServiceException;
import com.ram.model.request.UserDetailsRequestModel;
import com.ram.model.ui.UserRest;
import com.ram.service.UserService;
import com.ram.utils.ErrorMessages;

@RestController
@RequestMapping("users")
public class UserController
{
	@Autowired
	private UserService userService;

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception
	{
		if(userDetails.getEmail().isEmpty())
		{
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
		}
		
		UserRest userRest = new UserRest();

		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userDetails, userDTO);

		userService.createUser(userDTO);
		BeanUtils.copyProperties(userDTO, userRest);
		return null;
	}

	@GetMapping(path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest getUser(@PathVariable String userId)
	{
		UserRest userRest = new UserRest();
		UserDTO userDTO = userService.getUserByUserId(userId);
		BeanUtils.copyProperties(userDTO, userRest);
		return userRest;
	}

}
