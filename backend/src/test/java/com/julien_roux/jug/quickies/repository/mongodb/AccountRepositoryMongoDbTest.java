package com.julien_roux.jug.quickies.repository.mongodb;

import com.julien_roux.jug.quickies.model.Account;

public class AccountRepositoryMongoDbTest extends AbstractMongodbTest<Account> {

	public Account getEntity() {
		Account entity = new Account("email", "password", "role");
		entity.setCompany("company");
		entity.setFirstname("firstname");
		entity.setLastname("lastname");
		entity.setShamefulTechnologie("shamefulTechnologie");
		return entity;
	}

	@Override
	public void updateEntity(Account entity) {
		entity.setCompany("company UPDATED");
		entity.setFirstname("firstname UPDATED");
		entity.setLastname("lastname UPDATED");
		entity.setShamefulTechnologie("shamefulTechnologie UPDATED");
	}
	
	
	
}
