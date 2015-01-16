package com.julien_roux.jug.quickies.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.julien_roux.jug.quickies.model.Quicky;
import com.julien_roux.jug.quickies.model.User;
import com.julien_roux.jug.quickies.model.Vote;

public interface VoteRepository extends MongoRepository<Vote, BigInteger> {
	public Vote findByVoterAndQuicky(User user, Quicky quicky);
	public List<Vote> findByQuicky(Quicky quicky);
}
