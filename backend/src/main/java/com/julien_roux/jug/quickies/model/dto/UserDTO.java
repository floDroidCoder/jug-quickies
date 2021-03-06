package com.julien_roux.jug.quickies.model.dto;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.julien_roux.jug.quickies.model.User;
import com.julien_roux.jug.quickies.utils.MD5Encoder;

public class UserDTO {

	private String id;
	private String lastname;
	private String firstname;

	@NotEmpty
	@Email
	private String email;
	private String role;
	private String shamefulTechnologie;
	private String company;
	private String about;
	private String password;

	public UserDTO() {
	}

	public UserDTO(User user) {
		this();
		this.id = user.getId() != null ? String.valueOf(user.getId()) : StringUtils.EMPTY;
		this.lastname = user.getLastname();
		this.firstname = user.getFirstname();
		this.email = user.getEmail();
		this.shamefulTechnologie = user.getShamefulTechnologie();
		this.company = user.getCompany();
		this.about = user.getAbout();
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMd5email() {
		return MD5Encoder.toMD5(email);
	}
}
