package com.java.film.entity;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
	
	
	public SingleFilm() {
		super();
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getFilmPilot() {
		return filmPilot;
	}

	public void setFilmPilot(String brief) {
		this.filmPilot = brief;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getFilmwebRanking() {
		return filmwebRanking;
	}

	public void setFilmwebRanking(String filmwebRanking) {
		this.filmwebRanking = filmwebRanking;
	}

	public String getCommunityRate() {
		return communityRate;
	}

	public void setCommunityRate(String communityRate) {
		this.communityRate = communityRate;
	}

	public List<String> getCreator() {
		return creator;
	}

	public void setCreator(List <String> creator) {
		this.creator = creator;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public List<String> getUserReviews() {
		return userReviews;
	}

	public void setUserReviews(List<String> userReviews) {
		this.userReviews = userReviews;
	}

	public List<String> getCuriosities() {
		return curiosities;
	}

	public void setCuriosities(List<String> curiosities) {
		this.curiosities = curiosities;
	}

	public Map <String, String> getActors() {
		return actors;
	}

	public void setActors(Map <String, String> actors) {
		this.actors = actors;
	}
	
}
