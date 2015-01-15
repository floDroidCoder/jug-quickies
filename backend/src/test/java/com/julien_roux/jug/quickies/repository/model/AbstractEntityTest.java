package com.julien_roux.jug.quickies.repository.model;

import static org.fest.assertions.Assertions.assertThat;

import java.math.BigInteger;

import org.junit.Test;

import com.julien_roux.jug.quickies.model.Entity;

public abstract class AbstractEntityTest<T extends Entity> extends AbstractModelTest<T> {
	
	@Test
	public void equalsSameEntitiesDifferentId() {
		T entity = getEntity();
		entity.setId(BigInteger.valueOf(1));
		T other = getEntity();
		other.setId(BigInteger.valueOf(2));
		assertThat(entity.equals(other)).isTrue();
	}
	
	@Test
	public void hascodeSameEntitiesDifferentId() {
		T entity = getEntity();
		entity.setId(BigInteger.valueOf(1));
		T other = getEntity();
		other.setId(BigInteger.valueOf(2));
		assertThat(entity.hashCode()).isEqualTo(other.hashCode());
	}
}
