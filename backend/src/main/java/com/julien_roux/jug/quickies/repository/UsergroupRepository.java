package com.julien_roux.jug.quickies.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.julien_roux.jug.quickies.model.Usergroup;

public interface UsergroupRepository extends MongoRepository<Usergroup, BigInteger> {

}
