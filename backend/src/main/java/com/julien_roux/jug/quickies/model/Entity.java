package com.julien_roux.jug.quickies.model;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;

public abstract class Entity {

	@Id
	private BigInteger id;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

}
