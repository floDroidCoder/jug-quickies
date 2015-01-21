package com.julien_roux.jug.quickies.model;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "jug_vote")
public class Vote extends Entity {

	private Quicky quicky;

	private User voter;

	@DateTimeFormat(pattern = "YYYY-MM-DD'T'hh:mm")
	private Date submissionDate;

	// ************************************************************************

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vote other = (Vote) obj;
		return new EqualsBuilder().//
				append(quicky, other.quicky).//
				append(submissionDate, other.submissionDate).//
				append(voter, other.voter).//
				isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().//
				append(quicky).//
				append(submissionDate).//
				append(voter).//
				toHashCode();
	}

	// ************************************************************************


	public void setQuicky(Quicky quicky) {
		this.quicky = quicky;
	}

	public Quicky getQuicky() {
		return quicky;
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
