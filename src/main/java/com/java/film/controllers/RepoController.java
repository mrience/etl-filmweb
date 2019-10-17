package com.java.film.controllers;

import com.java.film.entity.SingleFilm;
import com.java.film.service.FilmServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RepoController {
	
	@Autowired
	FilmServiceInterface service;

	@PostMapping("/update")
	public ResponseEntity <SingleFilm> updateFilm(@RequestParam String url){
		return service.updateFilm(url);
	}
	
	@DeleteMapping("/cleanRepo")
	public ResponseEntity<String> cleanRepo(){
		return service.cleanRepo();
	}

	@PostMapping("/getAllFilms")
	public ResponseEntity<List> getAllFilms() { return service.findAll(); }
}
