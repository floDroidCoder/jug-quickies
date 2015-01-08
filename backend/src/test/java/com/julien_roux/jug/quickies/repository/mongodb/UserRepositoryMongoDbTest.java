package com.julien_roux.jug.quickies.repository.mongodb;

import com.julien_roux.jug.quickies.model.User;

public class UserRepositoryMongoDbTest extends AbstractMongodbTest<User> {

	public User getEntity() {
		User entity = new User("email", "password", "role");
		entity.setCompany("company");
		entity.setFirstname("firstname");
		entity.setLastname("lastname");
		entity.setShamefulTechnologie("shamefulTechnologie");
		return entity;
	}

	@Override
	public void updateEntity(User entity) {
		entity.setCompany("company UPDATED");
		entity.setFirstname("firstname UPDATED");
		entity.setLastname("lastname UPDATED");
		entity.setShamefulTechnologie("shamefulTechnologie UPDATED");
	}
	
	
	
}
