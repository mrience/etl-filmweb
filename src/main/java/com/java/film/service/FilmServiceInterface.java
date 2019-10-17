package com.java.film.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import com.java.film.entity.SingleFilm;

public interface FilmServiceInterface {

	ResponseEntity<String> extractResponse(String url);

	ResponseEntity<SingleFilm> transformResponse();

	ResponseEntity<SingleFilm> loadResponse();

	ResponseEntity<SingleFilm> completeEtlProcess(String url);

	ResponseEntity<String> cleanRepo();

	void insertFilm(SingleFilm film);

	ResponseEntity<SingleFilm> updateFilm(String url);

	void cleanFilmData();
	
	public ResponseEntity<InputStreamResource> exportCSV();
	
	public ResponseEntity<String> exportTxt(String url);

	SingleFilm getFilmById(String title);

	public ResponseEntity <List> findAll();

}