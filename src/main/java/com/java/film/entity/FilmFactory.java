package com.java.film.entity;

import java.util.List;

public class FilmFactory {
	
	public SingleFilm createFilm(String title, String description, String year, String rate, String director, String genre,
			String awards, List<String> actors) {
		SingleFilm singleFilm;
		singleFilm = new SingleFilm(title, description, year, rate, director, genre, awards, actors);
		return singleFilm;
	}
}
