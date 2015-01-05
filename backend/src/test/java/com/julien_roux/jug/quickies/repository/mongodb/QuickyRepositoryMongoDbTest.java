package com.julien_roux.jug.quickies.repository.mongodb;

import java.util.Date;

import com.julien_roux.jug.quickies.model.Quicky;

public class QuickyRepositoryMongoDbTest extends AbstractMongodbTest<Quicky> {

	public Quicky getEntity() {
		Quicky entity = new Quicky();
		entity.setDescription("description");
		entity.setId(null);
		entity.setPresenter(null);
		entity.setSubmissionDate(new Date());
		entity.setTitle("title");
		entity.setUsergroup("usergroup");
		return entity;
	}

	@Override
	public void updateEntity(Quicky entity) {
		entity.setDescription("description UPDATED");
		entity.setSubmissionDate(new Date());
		entity.setTitle("title UPDATED");
		entity.setUsergroup("usergroup UPDATED");
	}
	
	
	
}