package com.julien_roux.jug.quickies.model.dto;

import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.julien_roux.jug.quickies.model.Usergroup;

public class UsergroupDTO {

	private String id, name, creatorName, creatorId;

	private Date creationDate;

	public UsergroupDTO() {
	}

	public UsergroupDTO(Usergroup usergroup) {
		this();
		this.id = usergroup.getId() != null ? String.valueOf(usergroup.getId()) : StringUtils.EMPTY;
		this.name = usergroup.getName();
		this.creatorName = usergroup.getCreatorName();
		this.creatorId = usergroup.getCreatorId();
	}

	public Usergroup toUsergroup() {
		Usergroup usergroup = new Usergroup();
		usergroup.setName(this.name);
		usergroup.setId(StringUtils.isEmpty(this.id) ? null : new BigInteger(this.id));
		return usergroup;
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
		UsergroupDTO other = (UsergroupDTO) obj;
		return new EqualsBuilder().//
		            append(name, other.name).//
		            append(creatorName, other.creatorName).//
		            isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().//
		            append(name).//
		            append(creatorName).//
		            toHashCode();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
