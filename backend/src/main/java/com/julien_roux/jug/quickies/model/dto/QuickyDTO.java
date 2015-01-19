package com.julien_roux.jug.quickies.model.dto;

import java.math.BigInteger;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.julien_roux.jug.quickies.model.Quicky;

public class QuickyDTO {

	private String id;
	
	@Length(max=125)
	@NotNull
	private String title;
	
	@NotNull
	private String description;
	@NotNull
	private String usergroup;
	
	@Length(max=125)
	@NotNull
	private String location;
	
	
	private Integer nbVote;
	private String presenterName, presenterId;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@NotNull
	private Date submissionDate;

	public QuickyDTO() {
	}

	public QuickyDTO(Quicky quicky) {
		this();
		this.id = quicky.getId() != null ? String.valueOf(quicky.getId()) : StringUtils.EMPTY;
		this.title = quicky.getTitle();
		this.description = quicky.getDescription();
		this.usergroup = quicky.getUsergroup();
		this.presenterName = quicky.getPresenter().getFirstname() + " " + quicky.getPresenter().getLastname();
		this.presenterId = String.valueOf(quicky.getPresenter().getId());
		this.submissionDate = quicky.getSubmissionDate();
		this.nbVote = quicky.getNbVote();
		this.setLocation(quicky.getLocation());
	}

	public Quicky toQuicky() {
		Quicky quicky = new Quicky();
		quicky.setDescription(this.getDescription());
		quicky.setLocation(this.getLocation());
		quicky.setUsergroup(this.getUsergroup());
		quicky.setTitle(this.getTitle());
		quicky.setSubmissionDate(this.getSubmissionDate());
		quicky.setId(StringUtils.isEmpty(this.id) ? null : new BigInteger(this.id));

		return quicky;
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
		            isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().//
		            append(title).//
		            append(description).//
		            append(location).//
		            append(submissionDate).//
		            toHashCode();
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

	public String getPresenterName() {
		return presenterName;
	}

	public void setPresenterName(String presenterName) {
		this.presenterName = presenterName;
	}

	public String getPresenterId() {
		return presenterId;
	}

	public void setPresenterId(String presenterId) {
		this.presenterId = presenterId;
	}

	public Integer getNbVote() {
		return nbVote;
	}

	public void setNbVote(Integer nbVote) {
		this.nbVote = nbVote;
	}
}
