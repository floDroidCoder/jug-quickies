package com.julien_roux.jug.quickies.repository.mongodb;

import static org.fest.assertions.Assertions.assertThat;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.julien_roux.jug.quickies.model.Entity;
import com.julien_roux.jug.quickies.repository.model.AbstractEntityTest;

public abstract class AbstractMongodbTest<T extends Entity> extends AbstractEntityTest<T> {
	
	@Autowired
	protected MongoRepository<T, BigInteger> repository;

	abstract void updateEntity(T entity);
	
	@Before
	public void setup() {
		repository.deleteAll();
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
