package com.starter.app.repository;

import com.starter.app.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {

	User findByUsername(String username);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);

}
