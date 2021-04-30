package com.ram.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ram.dto.UserDTO;
import com.ram.entity.UserEntity;
import com.ram.exception.UserServiceException;
import com.ram.repository.UserRepository;
import com.ram.service.UserService;
import com.ram.utils.ErrorMessages;
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
		if (userEntityByEmail != null)
		{
			throw new RuntimeException("Record already exists");
		}

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDTO, userEntity);

		String publicUserId = utils.generateUserId(20);
		userEntity.setUserId(publicUserId);
		userEntity.setEncryptedPassword(bcryptPasswordEncoder.encode(userDTO.getPassword()));

		UserEntity storedUserEntity = userRepository.save(userEntity);

		UserDTO returnUserDTO = new UserDTO();
		BeanUtils.copyProperties(storedUserEntity, returnUserDTO);

		return returnUserDTO;
	}

	@Override
	public UserDTO getUser(String email)
	{
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
		{
			throw new UsernameNotFoundException(email);
		}
		UserDTO returnUserDTO = new UserDTO();
		BeanUtils.copyProperties(userEntity, returnUserDTO);
		return returnUserDTO;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
	{
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
		{
			throw new UsernameNotFoundException(email);
		}
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(),
				new ArrayList<>());
	}

	@Override
	public UserDTO getUserByUserId(String userId)
	{
		UserDTO returnUserDTO = new UserDTO();
		UserEntity userEntityByUserId = userRepository.findByUserId(userId);

		if (userEntityByUserId == null)
		{
			throw new UsernameNotFoundException(userId);
		}

		BeanUtils.copyProperties(userEntityByUserId, returnUserDTO);

		return returnUserDTO;
	}

	@Override
	public UserDTO updateUser(String userId, UserDTO userDTO)
	{
		UserDTO returnUserDTO = new UserDTO();
		UserEntity userEntityByUserId = userRepository.findByUserId(userId);

		if (userEntityByUserId == null)
		{
			throw new UserServiceException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
		}

		userEntityByUserId.setFirstName(userDTO.getFirstName());
		userEntityByUserId.setLastName(userDTO.getLastName());
		userEntityByUserId.setEmail(userDTO.getEmail());
		userEntityByUserId
				.setEncryptedPassword(bcryptPasswordEncoder.encode(userDTO.getPassword()));

		UserEntity updatedUserEntity = userRepository.save(userEntityByUserId);

		BeanUtils.copyProperties(updatedUserEntity, returnUserDTO);

		return returnUserDTO;
	}

	@Override
	public void deleteUser(String userId)
	{
		UserEntity userEntityByUserId = userRepository.findByUserId(userId);

		if (userEntityByUserId == null)
		{
			throw new UserServiceException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
		}
		userRepository.delete(userEntityByUserId);

	}

	@Override
	public List<UserDTO> getUsers(int page, int limit)
	{
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		
		if(page>1)
		{
			page = page-1; 
		}
		
		Pageable pageable= PageRequest.of(page, limit);
		Page<UserEntity> usersPage = userRepository.findAll(pageable);
		
		List<UserEntity> userEntityList = usersPage.getContent();
		
		for (UserEntity userEntity : userEntityList)
		{
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(userEntity, userDTO);
			userDTOList.add(userDTO);
		}
		
		return userDTOList;
	}

}
