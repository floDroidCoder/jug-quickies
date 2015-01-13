package com.julien_roux.jug.quickies.repository.model;

import com.julien_roux.jug.quickies.model.User;

public class UserTest extends AbstractEntityTest<User> {

	public User getEntity() {
		User entity = new User("email", "password", "role");
		entity.setCompany("company");
		entity.setFirstname("firstname");
		entity.setLastname("lastname");
		entity.setShamefulTechnologie("shamefulTechnologie");
		return entity;
	}

}
