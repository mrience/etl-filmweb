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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
	
	@GetMapping
	public String index(Model model) {
		return "index.html";
	}
	
	@PostMapping("/extract")
	public ResponseEntity <String> extractSubmit(@RequestBody String url) {
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
	public ResponseEntity <SingleFilm> updateFilm(@RequestBody String url){
		return service.updateFilm(url);
	}
	
	@PostMapping("/completeEtlProcess")
	@ResponseBody
	public ResponseEntity<SingleFilm> completeEtlProcess(@RequestBody String url){
		return service.completeEtlProcess(url);
	}
	
	@DeleteMapping("/cleanRepo")
	public ResponseEntity<String> cleanRepo(){
		return service.cleanRepo();
	}
	
	@PostMapping(value = "/exportCSV", produces="text/csv")
	@ResponseBody
	public ResponseEntity <InputStreamResource> exportCSV() throws IOException, NullPointerException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException{
		return service.exportCSV();
	}
	
	@PostMapping("/exportFilmTxt")
	public ResponseEntity <String> exportFilmTxt(@RequestBody String url) {
		return service.exportTxt(url);
	}
	
}
