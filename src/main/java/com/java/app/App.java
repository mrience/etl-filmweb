package com.java.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan(basePackages = "com.java")
@SpringBootApplication
public class App {
	public static void main(String [] args) {
		SpringApplication.run(App.class, args);
	}
}
