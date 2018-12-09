package com.java.film.entity;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter
@NoArgsConstructor @ToString
@Document(collection = "Films")
public class SingleFilm {
	
	private String title;
	private String type;
	@Id
	private String url;
	private String poster;
	private String filmPilot;
	private String description;
	private String year;
	private String filmwebRanking;
	private String communityRate;
	private List <String> creator;
	private List <String> images;
	private List <String> genres;
	private List <String> userReviews;
	private List <String> curiosities;
	private Map <String, String> actors;
	
}
