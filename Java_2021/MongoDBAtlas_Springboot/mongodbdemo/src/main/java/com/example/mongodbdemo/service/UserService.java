package com.example.mongodbdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.mongodbdemo.domain.User;
import com.example.mongodbdemo.repository.UserRepository;

@Service
public class UserService
{
	@Autowired
	private UserRepository userRepository;

	public User addUser(@RequestBody User user)
	{
		return userRepository.save(user);
	}

	public List<User> getAllUser()
	{
		return userRepository.findAll();
	}
}
