package com.julien_roux.jug.quickies.repository.model;

import com.julien_roux.jug.quickies.model.User;

public class UserTest extends AbstractEntityTest<User> {

	public User getEntity() {
		User entity = new User("email test", "password", "role test");
		entity.setCompany("company test");
		entity.setFirstname("firstname test");
		entity.setLastname("lastname test");
		entity.setShamefulTechnologie("shamefulTechnologie test");
		return entity;
	}

}
