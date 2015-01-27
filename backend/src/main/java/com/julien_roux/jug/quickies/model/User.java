package com.julien_roux.jug.quickies.model;

import java.util.Collection;
import java.util.Collections;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Document(collection = "jug_user")
public class User extends Entity {

	public static final String ROLE_USER = "USER_ROLE";
	public static final String ROLE_ADMIN = "ADMIN_ROLE";

	private String lastname, firstname, email, role, shamefulTechnologie, company, password, about;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.role = role;
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
		User other = (User) obj;
		return new EqualsBuilder().//
				append(lastname, other.lastname).//
				append(firstname, other.firstname).//
				append(email, other.email).//
				append(role, other.role).//
				append(shamefulTechnologie, other.shamefulTechnologie).//
				append(company, other.company).//
				append(about, other.about).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().//
				append(lastname).//
				append(firstname).//
				append(email).//
				append(role).//
				append(shamefulTechnologie).//
				append(company).//
				append(about).toHashCode();
	}

	// ************************************************************************

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(getRole()));
	}

	public boolean isAdmin() {
		return ROLE_ADMIN.equals(getRole());
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
}
