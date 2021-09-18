package com.ram.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ram.dto.UserDTO;
import com.ram.exception.UserServiceException;
import com.ram.model.request.UserDetailsRequestModel;
import com.ram.model.response.OperationStatusModel;
import com.ram.model.ui.UserRest;
import com.ram.service.UserService;
import com.ram.utils.ErrorMessages;
import com.ram.utils.RequestOperationName;
import com.ram.utils.RequestOperationResult;

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
		if (userDetails.getEmail().isEmpty())
		{
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
		}

		UserRest userRest = new UserRest();

		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userDetails, userDTO);

		UserDTO createdUserDTO = userService.createUser(userDTO);
		BeanUtils.copyProperties(createdUserDTO, userRest);
		return userRest;
	}

	@GetMapping(path = "/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest getUser(@PathVariable("id") String userId)
	{
		UserRest userRest = new UserRest();
		UserDTO userDTO = userService.getUserByUserId(userId);
		BeanUtils.copyProperties(userDTO, userRest);
		return userRest;
	}

	@PutMapping(path = "/{id}",
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest updateUser(@PathVariable("id") String userId,
			@RequestBody UserDetailsRequestModel userDetails) throws Exception
	{
		if (userDetails.getEmail().isEmpty())
		{
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
		}

		UserRest userRest = new UserRest();

		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userDetails, userDTO);

		UserDTO updatedUserDTO = userService.updateUser(userId, userDTO);
		BeanUtils.copyProperties(updatedUserDTO, userRest);
		return userRest;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable("id") String userId)
	{
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
		
		userService.deleteUser(userId);
		
		operationStatusModel.setOperationResult(RequestOperationResult.SUCCESS.name());
		return operationStatusModel;
	}

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "3") int limit)
	{
		List<UserRest> userRestList = new ArrayList<UserRest>();

		List<UserDTO> userDTOList = userService.getUsers(page,limit);
		
		for (UserDTO userDTO : userDTOList)
		{
			UserRest userRest = new UserRest();
			BeanUtils.copyProperties(userDTO, userRest);
			userRestList.add(userRest);
		}
		
		return userRestList;
	}

}
