package com.julien_roux.jug.quickies.repository.mongodb;

import java.util.Date;

import com.julien_roux.jug.quickies.model.Quicky;
import com.julien_roux.jug.quickies.repository.model.QuickyTest;

public class QuickyRepositoryMongoDbTest extends AbstractMongodbTest<Quicky> {

	public Quicky getEntity() {
		return new QuickyTest().getEntity();
	}

	@Override
	public void updateEntity(Quicky entity) {
		entity.setDescription("description UPDATED");
		entity.setSubmissionDate(new Date());
		entity.setTitle("title UPDATED");
		entity.setUsergroup("usergroup UPDATED");
	}
	
	
	
}
