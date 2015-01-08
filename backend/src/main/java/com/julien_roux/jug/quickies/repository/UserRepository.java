package com.julien_roux.jug.quickies.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.julien_roux.jug.quickies.model.User;

public interface UserRepository extends MongoRepository<User, BigInteger> {

	public User findByEmail(String email);
}
