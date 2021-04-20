package com.ram.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ram.dto.UserDTO;

public interface UserService extends UserDetailsService
{
	public UserDTO createUser(UserDTO userDTO);
	public UserDTO getUser(String email);
	
}
