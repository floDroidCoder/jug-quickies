package com.julien_roux.jug.quickies.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Quicky extends AbstractDocument {

	private String title, description, usergroup;
	
	private Account presenter;
	
	private Date submissionDate;
}
