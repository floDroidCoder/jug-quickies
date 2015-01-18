package com.julien_roux.jug.quickies.model;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jug_quicky")
public class Quicky extends Entity {

	private String title, description, usergroup, location;

	private User presenter;

	private Date submissionDate;

	private Integer nbVote;

	public Quicky() {
		this.nbVote = 0;
	}

	public Quicky(String title, String description, String usergroup) {
		this.title = title;
		this.description = description;
		this.usergroup = usergroup;
		this.nbVote = 0;
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
		return new EqualsBuilder().//
		            append(title, other.title).//
		            append(description, other.description).//
		            append(submissionDate, other.submissionDate).//
		            append(presenter, other.presenter).//
		            append(location, other.location).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(). //
		            append(title).//
		            append(description).//
		            append(submissionDate).//
		            append(presenter).//
		            append(location).toHashCode();
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

	public User getPresenter() {
		return presenter;
	}

	public void setPresenter(User presenter) {
		this.presenter = presenter;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getNbVote() {
		return nbVote;
	}

	public void setNbVote(Integer nbVote) {
		this.nbVote = nbVote;
	}
}
