package com.java.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableMongoRepositories(basePackages = "com.java.mongoDB")
public class MongoConfig extends AbstractMongoConfiguration{

	@Override
	protected String getDatabaseName() {
		return "etl-filmweb";
	}
	
	@Bean
	public MongoClient mongoClient() {
		return new MongoClient("localhost", 27017);
	}
	
	@Override
	protected Collection <String> getMappingBasePackages() {
		Collection collection = new ArrayList();
		collection.add("com.java");
		return collection;
	}
}
