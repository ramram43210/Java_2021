package com.ram.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ram.entity.UserEntity;  

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>
{
	UserEntity findByEmail(String email);

	UserEntity findByUserId(String userId);
}
