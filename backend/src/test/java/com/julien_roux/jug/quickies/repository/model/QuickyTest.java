package com.julien_roux.jug.quickies.repository.model;

import java.util.Date;

import com.julien_roux.jug.quickies.model.Quicky;

public class QuickyTest extends AbstractEntityTest<Quicky> {

	public Quicky getEntity() {
		Quicky entity = new Quicky("titleDb", "descriptionDb", "usergroupDb");
		entity.setId(null);
		entity.setPresenter(null);
		entity.setSubmissionDate(new Date());
		entity.setLocation("locationDb");
		return entity;
	}	
}
