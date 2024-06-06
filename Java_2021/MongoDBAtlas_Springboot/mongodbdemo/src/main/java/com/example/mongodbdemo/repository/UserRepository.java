package com.example.mongodbdemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.mongodbdemo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User,String>
{
	// Define custom queries or use the default CRUD operations provided by MongoRepository
}
