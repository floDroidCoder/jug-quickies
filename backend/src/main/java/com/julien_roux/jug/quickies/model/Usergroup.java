package com.julien_roux.jug.quickies.model;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jug_usergroup")
public class Usergroup extends Entity {

	private String name;
	
	private User creator;
	
	private Date creationDate;

	// ************************************************************************
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usergroup other = (Usergroup) obj;
		return new EqualsBuilder().//
				append(name, other.name).//
				isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().//
				append(name).//
				toHashCode();
	}
	
	public String getCreatorName() {
		if(this.getCreator() == null) {
			return "void";
		}
		return String.format("%s %s", this.getCreator().getFirstname(), this.getCreator().getLastname());
	}
	
	public String getCreatorId() {
		if(this.getCreator() == null) {
			return "0";
		}
		return String.valueOf(this.getCreator().getId());
	}

	// ************************************************************************


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
