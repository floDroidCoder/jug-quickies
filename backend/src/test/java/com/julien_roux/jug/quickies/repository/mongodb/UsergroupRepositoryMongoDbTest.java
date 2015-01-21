package com.julien_roux.jug.quickies.repository.mongodb;

import java.util.Date;

import com.julien_roux.jug.quickies.model.Usergroup;
import com.julien_roux.jug.quickies.repository.model.UserTest;
import com.julien_roux.jug.quickies.repository.model.UsergroupTest;

public class UsergroupRepositoryMongoDbTest extends AbstractMongodbTest<Usergroup> {

	public Usergroup getEntity() {
		return new UsergroupTest().getEntity();
	}

	@Override
	public void updateEntity(Usergroup entity) {
		entity.setCreationDate(new Date());
		entity.setCreator(new UserTest().getEntity());
		entity.setName("name update");
	}
}
