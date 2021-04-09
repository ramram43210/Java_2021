package com.ram.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ram.dto.UserDTO;
import com.ram.entity.UserEntity;
import com.ram.repository.UserRepository;
import com.ram.service.UserService;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDTO createUser(UserDTO userDTO)
	{
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDTO, userEntity);
		
		UserEntity storedUserEntity = userRepository.save(userEntity);
		
		UserDTO returnUserDTO = new UserDTO();
		BeanUtils.copyProperties(storedUserEntity, returnUserDTO);
		
		return returnUserDTO;
	}

	
}
