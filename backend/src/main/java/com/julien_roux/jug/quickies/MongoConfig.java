package com.julien_roux.jug.quickies;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

@Configuration
@ComponentScan
@EnableMongoRepositories
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${mongo.db.name}")
    private String databaseName;

    @Value("${mongo.db.server}")
    private String databaseServer;

    @Override
    public String getDatabaseName() {
        return databaseName;
    }

	@Override
	public Mongo mongo() throws Exception {
		Mongo mongo = new MongoClient(databaseServer);
		mongo.setWriteConcern(WriteConcern.SAFE);
		return mongo;
	}

}
