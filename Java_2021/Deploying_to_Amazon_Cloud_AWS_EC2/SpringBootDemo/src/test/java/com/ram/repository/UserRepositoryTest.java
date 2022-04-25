package com.ram.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ram.entity.UserEntity;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest
{
	@Autowired
	UserRepository userRepository;
	static boolean recordsCreated = false;

	@BeforeEach
	void setUp() throws Exception
	{
		if(!recordsCreated)
		{
			createUserRecords();
		}

	}

	private void createUserRecords()
	{
		UserEntity userEntity1 = new UserEntity();
		userEntity1.setFirstName("Roy");
		userEntity1.setLastName("p");
		userEntity1.setUserId("13koojkj");
		userEntity1.setEncryptedPassword("zzz");
		userEntity1.setEmail("Roy@yahoo.com");
		userEntity1.setEmailVerificationStatus(true);

		UserEntity userEntity2 = new UserEntity();
		userEntity2.setFirstName("Arun");
		userEntity2.setLastName("d");
		userEntity2.setUserId("898989");
		userEntity2.setEncryptedPassword("rrrr");
		userEntity2.setEmail("Arun@yahoo.com");
		userEntity2.setEmailVerificationStatus(false);

		UserEntity userEntity3 = new UserEntity();
		userEntity3.setFirstName("John");
		userEntity3.setLastName("t");
		userEntity3.setUserId("ggg34");
		userEntity3.setEncryptedPassword("44er");
		userEntity3.setEmail("John@yahoo.com");
		userEntity3.setEmailVerificationStatus(true);

		userRepository.save(userEntity1);
		userRepository.save(userEntity2);
		userRepository.save(userEntity3);
		
		recordsCreated = true;
	}

	@Test
	void testGetVerifiedUsers()
	{
		Pageable pageable = PageRequest.of(0, 2);
		Page<UserEntity> pages = userRepository.findAllUsersWithConfirmedEmailAddress(pageable);
		assertNotNull(pages);
		List<UserEntity> userEntityList = pages.getContent();
		assertNotNull(userEntityList);
		assertTrue(userEntityList.size() == 2);
	}
	
	@Test
	void testFindUserByName()
	{
		List<UserEntity> userEntityList = userRepository.findUserByName("Roy", "p");
		assertNotNull(userEntityList);
		assertTrue(userEntityList.size() == 1);
	}
	
	@Test
	void testFindUserByEmail()
	{
		List<UserEntity> userEntityList = userRepository.findUserByEmail("Roy@yahoo.com");
		assertNotNull(userEntityList);
		assertTrue(userEntityList.size() == 1);
	}
	
	@Test
	void testFindUserByKeyword()
	{
		String keyword = "n";
		List<UserEntity> userEntityList = userRepository.findUserByKeyword(keyword);
		assertNotNull(userEntityList);
		assertTrue(userEntityList.size() == 2);

		for (UserEntity userEntity : userEntityList)
		{
			System.out.println("First Name = " + userEntity.getFirstName());
			assertTrue(userEntity.getFirstName().contains(keyword));
		}
	}
	
	@Test
	void testFindFirstNameAndLastNameByEmail()
	{
		List<Object[]> objectArrayList = userRepository.findFirstNameAndLastNameByEmail("Roy@yahoo.com");
		assertNotNull(objectArrayList);

		for (Object[] objects : objectArrayList)
		{
			String firstName = (String) objects[0];
			String lastName = (String) objects[1];

			assertNotNull(firstName);
			assertNotNull(lastName);

			System.out.println("firstName = " + firstName + " , " + "LastName = " + lastName);
		}

	}
	
	@Test
	void testUpdateEmailVerificationToken()
	{
		String token = "ksjfkljdskj$%%khdjksh";
		userRepository.updateEmailVerificationToken(token, "Roy@yahoo.com");

		UserEntity userEntity = userRepository.findByEmail("Roy@yahoo.com");
		assertTrue(userEntity.getEmailVerificationToken().equals(token));

	}
	
	@Test
	void testFindUserEntityByUserId()
	{
		String userId = "13koojkj";
		UserEntity userEntity = userRepository.findUserEntityByUserId(userId);
		assertNotNull(userEntity);
		assertTrue(userEntity.getUserId().equals(userId));
	}
	
	@Test
	void testGetFullNamebyUserId()
	{
		List<Object[]> objectArrayList = userRepository.getFullNamebyUserId("13koojkj");
		assertNotNull(objectArrayList);

		for (Object[] objects : objectArrayList)
		{
			String firstName = (String) objects[0];
			String lastName = (String) objects[1];

			assertNotNull(firstName);
			assertNotNull(lastName);

			System.out.println("firstName = " + firstName + " , " + "LastName = " + lastName);
		}
	}
	
	@Test
	void testUpdateUserEmailVerificationToken()
	{
		String token = "jkuiyuyhjhuyhjg";
		userRepository.updateEmailVerificationToken(token, "Roy@yahoo.com");

		UserEntity userEntity = userRepository.findByEmail("Roy@yahoo.com");
		assertTrue(userEntity.getEmailVerificationToken().equals(token));
	}

}
