package com.java.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories
public class MongoConfig extends AbstractMongoConfiguration{

	@Override
	protected String getDatabaseName() {
		return "etl-filmweb";
	}
	
	@Override
	public MongoClient mongoClient() {
		return new MongoClient("localhost", 27017);
	}
	
	 @Override
	    protected String getMappingBasePackage() {
	        return "com.java";
	    }
}
