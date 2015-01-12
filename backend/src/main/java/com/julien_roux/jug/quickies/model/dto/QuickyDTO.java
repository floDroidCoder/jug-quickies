package com.julien_roux.jug.quickies.model.dto;

import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import com.julien_roux.jug.quickies.model.Quicky;

public class QuickyDTO {

	private BigInteger id;
	
	private String title, description, usergroup, email;
	
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private Date submissionDate;

	public QuickyDTO() {
	}
	
	public QuickyDTO(Quicky quicky) {
		this();
		
		this.title = quicky.getTitle();
		this.description = quicky.getDescription();
		this.usergroup = quicky.getUsergroup();
		this.email = quicky.getPresenter().getEmail();
		this.id = quicky.getId();
		this.submissionDate = quicky.getSubmissionDate();
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
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
		return new EqualsBuilder().append(title, other.title).append(description, other.description)
				.append(submissionDate, other.submissionDate).append(email, other.email).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(title).append(description).append(submissionDate).append(email)
				.toHashCode();
	}
}
