package com.gabrielvalerio.desafio.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.gabrielvalerio.desafio.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	public User findByUsername(String username);

}