package com.ram.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
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

import com.ram.dto.AddressDTO;
import com.ram.dto.UserDTO;
import com.ram.entity.AddressEntity;
import com.ram.entity.UserEntity;
import com.ram.exception.UserServiceException;
import com.ram.repository.AddressRepository;
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
	AddressRepository addressRepository;

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

		List<AddressDTO> addressDTOList = userDTO.getAddresses();		
		for (AddressDTO addressDTO : addressDTOList)
		{
			addressDTO.setAddressId(utils.generateAddressId(30));
			addressDTO.setUserDetails(userDTO);
		}
		
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);

		String publicUserId = utils.generateUserId(20);
		userEntity.setUserId(publicUserId);
		userEntity.setEncryptedPassword(bcryptPasswordEncoder.encode(userDTO.getPassword()));

		UserEntity storedUserEntity = userRepository.save(userEntity);
		UserDTO returnUserDTO = modelMapper.map(storedUserEntity, UserDTO.class);

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
		UserEntity userEntityByUserId = userRepository.findByUserId(userId);

		if (userEntityByUserId == null)
		{
			throw new UsernameNotFoundException(userId);
		}

		ModelMapper modelMapper = new ModelMapper();
		UserDTO returnUserDTO = modelMapper.map(userEntityByUserId, UserDTO.class);

		return returnUserDTO;
	}

	@Override
	public UserDTO updateUser(String userId, UserDTO userDTO)
	{
		UserEntity userEntityfromDB = userRepository.findByUserId(userId);

		if (userEntityfromDB == null)
		{
			throw new UserServiceException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
		}
		
		List<AddressEntity> addressEntityList = userEntityfromDB.getAddresses();
		for (AddressEntity addressEntity : addressEntityList)
		{
			addressEntity.setUserDetails(null);
			addressRepository.save(addressEntity);
		}
		
		List<AddressDTO> addressDTOList = userDTO.getAddresses();		
		for (AddressDTO addressDTO : addressDTOList)
		{
			addressDTO.setAddressId(utils.generateAddressId(30));
			addressDTO.setUserDetails(userDTO);
		}
		
		ModelMapper modelMapper = new ModelMapper();	

		UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
		userEntity.setId(userEntityfromDB.getId());
		userEntity.setUserId(userEntityfromDB.getUserId());
		userEntity.setEncryptedPassword(bcryptPasswordEncoder.encode(userDTO.getPassword()));
			
		UserEntity updatedUserEntity = userRepository.save(userEntity);

		UserDTO returnUserDTO =  modelMapper.map(updatedUserEntity, UserDTO.class);
		
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

		if (page > 0)
		{
			page = page - 1;
		}

		Pageable pageable = PageRequest.of(page, limit);
		Page<UserEntity> usersPage = userRepository.findAll(pageable);

		List<UserEntity> userEntityList = usersPage.getContent();

		for (UserEntity userEntity : userEntityList)
		{
			
			ModelMapper modelMapper = new ModelMapper();
			UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
			userDTOList.add(userDTO);
		}

		return userDTOList;
	}

}
