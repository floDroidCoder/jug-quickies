package com.julien_roux.jug.quickies.repository.model;

import java.util.Date;

import com.julien_roux.jug.quickies.model.Vote;

public class VoteTest extends AbstractEntityTest<Vote> {

	public Vote getEntity() {
		Vote entity = new Vote();
		entity.setId(null);
		entity.setQuicky(new QuickyTest().getEntity());
		entity.setSubmissionDate(new Date(1));
		entity.setVoter(new UserTest().getEntity());
		return entity;
	}

}
