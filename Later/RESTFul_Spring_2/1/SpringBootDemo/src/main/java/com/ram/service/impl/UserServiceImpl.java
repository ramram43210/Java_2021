package com.ram.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ram.dto.UserDTO;
import com.ram.entity.UserEntity;
import com.ram.repository.UserRepository;
import com.ram.service.UserService;
import com.ram.utils.Utils;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Override
	public UserDTO createUser(UserDTO userDTO)
	{
		UserEntity userEntityByEmail = userRepository.findByEmail(userDTO.getEmail());
		if(userEntityByEmail!=null)
		{
			throw new RuntimeException("Record already exists");
		}
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDTO, userEntity);
		
		String publicUserId = utils.generateUserId(20);
		userEntity.setUserId(publicUserId);
		userEntity.setEncryptedPassword(userDTO.getPassword());
		
		UserEntity storedUserEntity = userRepository.save(userEntity);
		
		UserDTO returnUserDTO = new UserDTO();
		BeanUtils.copyProperties(storedUserEntity, returnUserDTO);
		
		return returnUserDTO;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		// TODO Auto-generated method stub
		return null;
	}

	
}
