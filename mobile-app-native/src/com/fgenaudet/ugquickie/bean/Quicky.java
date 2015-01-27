package com.fgenaudet.ugquickie.bean;


public class Quicky {
	private String id;
	private String title;
	private String description;
	private String usergroup;
	private String location;
	private Integer nbVote;
	private String presenterName, presenterId, presenterMD5;
	private String submissionDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getPresenterMD5() {
		return presenterMD5;
	}

	public void setPresenterMD5(String presenterMD5) {
		this.presenterMD5 = presenterMD5;
	}

	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	@Override
	public String toString() {
		return this.title + "[" + this.id + "]";
	}

}
