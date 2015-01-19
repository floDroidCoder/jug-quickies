package com.julien_roux.jug.quickies.repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.julien_roux.jug.quickies.model.Quicky;
import com.julien_roux.jug.quickies.model.User;

public interface QuickyRepository extends MongoRepository<Quicky, BigInteger> {
	public List<Quicky> findByPresenter(User presenter);

	public List<Quicky> findByUsergroup(String usergroup);
	
	public List<Quicky> findBySubmissionDateBeforeOrderBySubmissionDateDesc(Date date);
	
	public List<Quicky> findBySubmissionDateAfterOrderBySubmissionDateDesc(Date date);
	
	public List<Quicky> findBySubmissionDateBeforeAndUsergroupEqualsOrderBySubmissionDateDesc(Date date, String usergroup);
	
	public List<Quicky> findBySubmissionDateAfterAndUsergroupEqualsOrderBySubmissionDateDesc(Date date, String usergroup);

	public List<Quicky> findFirst3BySubmissionDateBeforeOrderByNbVoteDesc(Date date);

	public List<Quicky> findFirst3BySubmissionDateAfterOrderByNbVoteDesc(Date date);

	public List<Quicky> findFirst3BySubmissionDateBeforeAndUsergroupEqualsOrderByNbVoteDesc(Date date, String usergroup);

	public List<Quicky> findFirst3BySubmissionDateAfterAndUsergroupEqualsOrderByNbVoteDesc(Date date, String usergroup);
}
