package com.julien_roux.jug.quickies.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Quicky extends AbstractDocument {

	private String title, description, usergroup;
	
	private Account presenter;
	
	private Date submissionDate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsergroup() {
		return usergroup;
	}

	public void setUsergroup(String usergroup) {
		this.usergroup = usergroup;
	}

	public Account getPresenter() {
		return presenter;
	}

	public void setPresenter(Account presenter) {
		this.presenter = presenter;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}
}
