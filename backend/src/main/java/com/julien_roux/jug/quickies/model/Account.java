package com.julien_roux.jug.quickies.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Account extends AbstractDocument {

	private String lastname, firstname, email, role, shamefulTechnologie, company;

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getShamefulTechnologie() {
		return shamefulTechnologie;
	}

	public void setShamefulTechnologie(String shamefulTechnologie) {
		this.shamefulTechnologie = shamefulTechnologie;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
