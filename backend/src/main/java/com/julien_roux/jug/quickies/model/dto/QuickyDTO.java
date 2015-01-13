package com.julien_roux.jug.quickies.model.dto;

import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import com.julien_roux.jug.quickies.model.Quicky;

public class QuickyDTO {

	private String id;
	private String title, description, usergroup, email, location;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date submissionDate;

	public QuickyDTO() {
	}

	public QuickyDTO(Quicky quicky) {
		this();
		this.id = String.valueOf(quicky.getId());
		this.title = quicky.getTitle();
		this.description = quicky.getDescription();
		this.usergroup = quicky.getUsergroup();
		this.email = quicky.getPresenter().getEmail();
		this.submissionDate = quicky.getSubmissionDate();
		this.setLocation(quicky.getLocation());
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuickyDTO other = (QuickyDTO) obj;
		return new EqualsBuilder().//
				append(title, other.title).//
				append(description, other.description).//
				append(location, other.location).//
				append(submissionDate, other.submissionDate).//
				append(email, other.email).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().//
				append(title).//
				append(description).//
				append(location).//
				append(submissionDate).//
				append(email).toHashCode();
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
