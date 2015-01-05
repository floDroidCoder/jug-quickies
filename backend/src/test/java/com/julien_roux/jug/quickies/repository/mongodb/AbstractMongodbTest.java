package com.julien_roux.jug.quickies.repository.mongodb;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.julien_roux.jug.quickies.QuickiesLauncher;
import com.julien_roux.jug.quickies.model.AbstractDocument;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {QuickiesLauncher.class})
public abstract class AbstractMongodbTest<T extends AbstractDocument> {
	
	@Autowired
	protected MongoRepository<T, Long> repository;

	abstract T getEntity();
	abstract void updateEntity(T entity);
	
	@Before
	public void setup() {
		repository.deleteAll();
	}
	
	@Test
	public void saveEntity() {
		T entity = getEntity();
		T save = repository.save(entity);
		assertThat(save.getId()).isNotNull();
	}

	@Test
	public void updateEntity() {
		T entity = getEntity();
		T save = repository.save(entity);
		assertThat(save.getId()).isNotNull();

		updateEntity(save);
		repository.save(save);
	}
	
	@Test
	public void findAllEntities() {
		repository.save(getEntity());
		repository.save(getEntity());
		Iterable<T> all = repository.findAll();
		assertThat(all).hasSize(2);
	}
}
