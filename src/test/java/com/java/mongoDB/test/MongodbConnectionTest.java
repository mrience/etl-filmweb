package com.java.mongoDB.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.java.film.entity.SingleFilm;
import com.java.mongoDB.FilmRepository;

class MongodbConnectionTest {

	@Autowired
	FilmRepository repo;
	
	@Test
	void test() {
		final String TITLE = "Monty Python i Święty Graal";
		SingleFilm film = new SingleFilm();
		film.setTitle(TITLE);
		repo.insert(film);
		Assertions.assertEquals(repo.findById(TITLE), film, "true");
		
	}

}
