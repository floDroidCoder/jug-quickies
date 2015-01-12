package com.julien_roux.jug.quickies.model.dto;

import java.math.BigInteger;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.julien_roux.jug.quickies.model.User;

public class UserDTO {

	private BigInteger id;

	private String lastname, firstname, email, role, shamefulTechnologie, company, about, password;

	public UserDTO() {
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.lastname = user.getLastname();
		this.firstname = user.getFirstname();
		this.email = user.getEmail();
		this.shamefulTechnologie = user.getShamefulTechnologie();
		this.company = user.getCompany();
		this.about = user.getAbout();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return new EqualsBuilder().append(email, other.email).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(email).toHashCode();
	}
	
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

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}
	
	

}
