package com.java.film.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.java.film.entity.SingleFilm;

public interface FilmServiceInterface {
	
	public ResponseEntity<Object> doLoad();
	public ResponseEntity<SingleFilm> doTransform();
	public ResponseEntity <String> doExtract(String url);
	public Optional<SingleFilm> getFilmById(String title);
	public void insertFilm(SingleFilm film);
	public void updateFilm(SingleFilm film);
	public void clearRepository();
}

