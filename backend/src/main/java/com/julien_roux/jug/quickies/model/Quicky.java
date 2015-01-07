package com.julien_roux.jug.quickies.model;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Quicky extends Entity {

	private String title, description, usergroup;

	private Account presenter;

	private Date submissionDate;

	public Quicky() {
		// TODO Auto-generated constructor stub
	}

	// ************************************************************************

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quicky other = (Quicky) obj;
		return new EqualsBuilder().append(title, other.title).append(description, other.description)
				.append(submissionDate, other.submissionDate).append(presenter, other.presenter).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(title).append(description).append(submissionDate).append(presenter)
				.toHashCode();
	}

	// ************************************************************************

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
