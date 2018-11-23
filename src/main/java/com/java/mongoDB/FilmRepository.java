package com.java.mongoDB;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.java.film.entity.SingleFilm;

@Repository
public class FilmRepository extends MongoRepository <SingleFilm, String> {

}
