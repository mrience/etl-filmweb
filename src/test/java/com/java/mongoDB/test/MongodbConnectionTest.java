package com.java.mongoDB.test;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.configuration.MongoConfig;
import com.java.mongoDB.FilmRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes  = {MongoConfig.class})
public class MongodbConnectionTest{
	
	@Autowired
	FilmRepository repo;
	
	public void init() {
		
	}
	
	@Test
	public void test() {
		Assertions.assertNotNull(repo.findAll());
		System.out.println(repo.toString());
	}
}
