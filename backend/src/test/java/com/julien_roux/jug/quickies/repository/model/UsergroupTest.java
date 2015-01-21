package com.julien_roux.jug.quickies.repository.model;

import java.util.Date;

import com.julien_roux.jug.quickies.model.Usergroup;

public class UsergroupTest extends AbstractEntityTest<Usergroup> {

	public Usergroup getEntity() {
		Usergroup entity = new Usergroup();
		entity.setCreationDate(new Date());
		entity.setCreator(null);
		entity.setId(null);
		entity.setName("name");
		return entity;
	}

}
