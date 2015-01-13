package com.julien_roux.jug.quickies.repository.model;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.julien_roux.jug.quickies.QuickiesLauncher;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {QuickiesLauncher.class})
public abstract class AbstractModelTest<T> {
	
	public abstract T getEntity();

	@Test
	public void equalsSameInstances() {
		T entity = getEntity();
		assertThat(entity.equals(entity)).isTrue();
	}

	@Test
	public void equalsDifferentInstancess() {
		T entity = getEntity();
		T other = getEntity();
		assertThat(entity.equals(other)).isTrue();
	}
	
	@Test
	public void equasNull() {
		assertThat(getEntity().equals(null)).isFalse();
	}
	
	@Test
	public void equalsDifferentClass() {
		assertThat(getEntity().equals(new Object())).isFalse();
	}
	
	@Test
	public void hascodeSameInstances() {
		T entity = getEntity();
		assertThat(entity.hashCode()).isEqualTo(entity.hashCode());
	}
	
	@Test
	public void hascodeDifferentInstances() {
		T entity = getEntity();
		T other = getEntity();
		assertThat(entity.hashCode()).isEqualTo(other.hashCode());
	}
}
