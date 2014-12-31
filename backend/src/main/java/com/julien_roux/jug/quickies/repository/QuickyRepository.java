package com.julien_roux.jug.quickies.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.julien_roux.jug.quickies.model.Quicky;

public interface QuickyRepository extends MongoRepository<Quicky, Long> {

}
