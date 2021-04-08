package com.ram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ram.model.request.UserDetailsRequestModel;
import com.ram.service.EmployeeService;

@RestController
@RequestMapping("users")
public class UserController
{
	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<Object> createUser(@RequestBody UserDetailsRequestModel userDetails)
	{
		return null;
	}

	

}
