package com.ram.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ram.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>
{
	UserEntity findByEmail(String email);

	UserEntity findByUserId(String userId);

	@Query(value = "SELECT * FROM users WHERE email_verification_status=true",
			countQuery = "SELECT COUNT(*) FROM users WHERE email_verification_status=true",
			nativeQuery = true)
	Page<UserEntity> findAllUsersWithConfirmedEmailAddress(Pageable pageable);
	
	@Query(value = "SELECT * FROM users WHERE first_name=?1 and last_name=?2", nativeQuery = true)
	List<UserEntity> findUserByName(String firstName, String lastName);
	
	@Query(value = "SELECT * FROM users WHERE email=:emailId", nativeQuery = true)
	List<UserEntity> findUserByEmail(@Param("emailId") String emailId);
	
	@Query(value = "SELECT * FROM users WHERE first_name Like %:firstNameKeyword%", nativeQuery = true)
	List<UserEntity> findUserByKeyword(@Param("firstNameKeyword") String firstNameKeyword);
	
	@Query(value = "SELECT first_name,last_name FROM users WHERE email=:emailId", nativeQuery = true)
	List<Object[]> findFirstNameAndLastNameByEmail(@Param("emailId") String emailId);
	
	@Transactional
	@Modifying
	@Query(value = "Update users set email_verification_token=:token WHERE email=:emailId", nativeQuery = true)
	void updateEmailVerificationToken(@Param("token") String token,@Param("emailId") String emailId);

}
