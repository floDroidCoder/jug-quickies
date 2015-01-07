package com.julien_roux.jug.quickies.repository.mongodb;

import static org.fest.assertions.Assertions.assertThat;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.julien_roux.jug.quickies.QuickiesLauncher;
import com.julien_roux.jug.quickies.model.Entity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {QuickiesLauncher.class})
public abstract class AbstractMongodbTest<T extends Entity> {
	
	@Autowired
	protected MongoRepository<T, BigInteger> repository;

	abstract T getEntity();
	abstract void updateEntity(T entity);
	
	@Before
	public void setup() {
		repository.deleteAll();
	}

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
	public void equalsSameEntitiesDifferentId() {
		T entity = getEntity();
		entity.setId(BigInteger.valueOf(1));
		T other = getEntity();
		other.setId(BigInteger.valueOf(2));
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
	public void hascodeSameEntitiesDifferentId() {
		T entity = getEntity();
		entity.setId(BigInteger.valueOf(1));
		T other = getEntity();
		other.setId(BigInteger.valueOf(2));
		assertThat(entity.hashCode()).isEqualTo(other.hashCode());
	}
	
	@Test
	public void hascodeDifferentInstances() {
		T entity = getEntity();
		T other = getEntity();
		assertThat(entity.hashCode()).isEqualTo(other.hashCode());
	}
	
	@Test
	public void saveEntity() {
		T entity = repository.save(getEntity());
		assertThat(entity.getId()).isNotNull();
	}

	@Test
	public void updateEntity() {
		T entity = repository.save(getEntity());
		assertThat(entity.getId()).isNotNull();

		updateEntity(entity);
		repository.save(entity);
	}
	
	@Test
	public void findAllEntities() {
		repository.save(getEntity());
		repository.save(getEntity());
		Iterable<T> all = repository.findAll();
		assertThat(all).hasSize(2);
	}
	
	@Test
	public void countEntities() {
		repository.save(getEntity());
		repository.save(getEntity());
		long count = repository.count();
		assertThat(count).isEqualTo(2);
	}
	
	@Test
	public void findEntity() {
		T entity = repository.save(getEntity());
		T loaded = repository.findOne(entity.getId());
		assertThat(entity).isEqualTo(loaded);
	}
	
	@Test
	public void deleteEntity() {
		T entity = repository.save(getEntity());
		assertThat(entity.getId()).isNotNull();
		
		repository.delete(entity);
		T loaded = repository.findOne(entity.getId());
		assertThat(loaded).isNull();;
	}
}
