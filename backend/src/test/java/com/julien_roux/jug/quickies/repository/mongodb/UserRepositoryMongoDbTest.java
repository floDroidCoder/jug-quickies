package com.julien_roux.jug.quickies.repository.mongodb;

import com.julien_roux.jug.quickies.model.User;
import com.julien_roux.jug.quickies.repository.model.UserTest;

public class UserRepositoryMongoDbTest extends AbstractMongodbTest<User> {

	public User getEntity() {
		return new UserTest().getEntity();
	}

	@Override
	public void updateEntity(User entity) {
		entity.setCompany("company UPDATED");
		entity.setFirstname("firstname UPDATED");
		entity.setLastname("lastname UPDATED");
		entity.setShamefulTechnologie("shamefulTechnologie UPDATED");
	}
	
	
	
}
