package com.julien_roux.jug.quickies.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.julien_roux.jug.quickies.model.Account;

public interface AccountRepository extends MongoRepository<Account, Long> {

	public Account findByEmail(String email);
}
