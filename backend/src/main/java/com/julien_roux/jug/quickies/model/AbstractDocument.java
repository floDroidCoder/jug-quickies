package com.julien_roux.jug.quickies.model;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;

public class AbstractDocument {

	@Id
	private BigInteger id;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

}
