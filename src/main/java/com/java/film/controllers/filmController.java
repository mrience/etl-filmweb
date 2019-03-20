package com.java.film.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.java.film.entity.SingleFilm;
import com.java.film.service.FilmServiceInterface;
import com.java.jsoup.connection.ExctractedDocument;
import com.java.jsoup.connection.JsoupConnector;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@RestController
public class filmController {
	
	@Autowired
	FilmServiceInterface service;
	
	@PostMapping("/extract")
	public ResponseEntity <String> extractSubmit(@RequestParam String url) {
		return service.extractResponse(url);
	}
	
	@PostMapping("/transform")
	public ResponseEntity<SingleFilm> transformSubmit(){
		return service.transformResponse();
	}
	
	@PostMapping("/load")
	public ResponseEntity<SingleFilm> loadSubmit(){
		return service.loadResponse();
	}
	
	@PostMapping("/update")
	public ResponseEntity <SingleFilm> updateFilm(@RequestParam String url){
		return service.updateFilm(url);
	}
	
	@PostMapping("/completeEtlProcess")
	@ResponseBody
	public ResponseEntity<SingleFilm> completeEtlProcess(@RequestParam String url){
		return service.completeEtlProcess(url);
	}
	
	@DeleteMapping("/cleanRepo")
	public ResponseEntity<String> cleanRepo(){
		return service.cleanRepo();
	}
	
	@PostMapping(value = "/exportCSV", produces="text/csv")
	public ResponseEntity <InputStreamResource> exportCSV() {
		return service.exportCSV();
	}
	
	@PostMapping("/exportFilmTxt")
	public ResponseEntity <String> exportFilmTxt(@RequestParam String url) {
		return service.exportTxt(url);
	}

	@PostMapping("/getAllFilms")
	public ResponseEntity<List> getAllFilms() { return service.findAll(); }
}
