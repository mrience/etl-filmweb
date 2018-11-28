package com.java.film.controllers;

import java.io.IOException;

import org.jsoup.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.java.film.entity.SingleFilm;
import com.java.film.service.FilmService;
import com.java.jsoup.connection.ExctractedDocument;
import com.java.jsoup.connection.JsoupConnector;

@RestController
public class filmController {
	
	@Autowired
	FilmService service;
	
	@GetMapping("/")
	public String index() {
		ModelAndView mav = new ModelAndView();
		return "index";
	}
	
	@PostMapping("/extract")
	public ResponseEntity <String> extractSubmit(@ModelAttribute (value = "url") String url) {
		return service.doExtract(url);
	}
	
	@PostMapping("/transform")
	public ResponseEntity<SingleFilm> transformSubmit(){
		return service.doTransform();
	}
	
	@PostMapping("/load")
	public ResponseEntity<Object> loadSubmit(){
		return service.doLoad();
	}
}
