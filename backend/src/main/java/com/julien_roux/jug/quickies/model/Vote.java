package com.julien_roux.jug.quickies.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "jug_vote")
public class Vote extends Entity {

	private Quicky quicky;

	private User voter;

	@DateTimeFormat(pattern = "YYYY-MM-DD'T'hh:mm")
	private Date submissionDate;

	public Quicky getQuicky() {
		return quicky;
	}

	public void setQuicky(Quicky quicky) {
		this.quicky = quicky;
	}

	public User getVoter() {
		return voter;
	}

	public void setVoter(User voter) {
		this.voter = voter;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

}
