package com.java.film.entity;

import java.util.List;

public class SingleFilm {
	private String title;
	private String description;
	private String year;
	private String rate;
	private String director;
	private String genre;
	private String awards;
	private List <String> actors;
	
	public SingleFilm(String title, String description, String year, String rate, String director, String genre,
			String awards, List<String> actors) {
		super();
		this.title = title;
		this.description = description;
		this.year = year;
		this.rate = rate;
		this.director = director;
		this.genre = genre;
		this.awards = awards;
		this.actors = actors;
	}
	
	public SingleFilm() {
		super();
	}
	
	
}
