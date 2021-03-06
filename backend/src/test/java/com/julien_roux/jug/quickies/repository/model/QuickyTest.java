package com.julien_roux.jug.quickies.repository.model;

import java.util.Date;

import com.julien_roux.jug.quickies.model.Quicky;

public class QuickyTest extends AbstractEntityTest<Quicky> {

	public Quicky getEntity() {
		Quicky entity = new Quicky("titleDb", "descriptionDb", "usergroupDb");
		entity.setId(null);
		entity.setPresenter(new UserTest().getEntity());
		entity.setSubmissionDate(new Date(1));
		entity.setLocation("locationDb");
		entity.setNbVote(1);
		return entity;
	}	
}
