package com.ram.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ram.entity.UserEntity;  

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>
{
	UserEntity findByEmail(String email);
}
