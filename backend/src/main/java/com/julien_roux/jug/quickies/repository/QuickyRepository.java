package com.julien_roux.jug.quickies.repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.julien_roux.jug.quickies.model.Quicky;
import com.julien_roux.jug.quickies.model.User;

public interface QuickyRepository extends MongoRepository<Quicky, BigInteger> {
	public List<Quicky> findByPresenter(User presenter);
	public List<Quicky> findBySubmissionDateBefore(Date date);
	public List<Quicky> findBySubmissionDateAfter(Date date);
}
