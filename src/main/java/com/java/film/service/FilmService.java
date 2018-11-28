package com.java.film.service;

import java.io.IOException;
import java.util.Optional;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.java.film.entity.SingleFilm;
import com.java.film.scraper.FilmDataScraper;
import com.java.jsoup.connection.ExctractedDocument;
import com.java.jsoup.connection.JsoupConnector;
import com.java.mongoDB.FilmRepository;

@Service
public class FilmService implements FilmServiceInterface{
	private Document doc;
	private FilmDataScraper scraper;
	
	@Autowired
	FilmRepository filmRepo;
	
	public ResponseEntity<String> doExtract(String url) {
		JsoupConnector conn = new JsoupConnector();
		Connection con = conn.connect(url);
		doc = null;
		try {
		ExctractedDocument exDoc = new ExctractedDocument(con);
		doc = exDoc.getDoc();
		}catch (IOException e) {
			return new ResponseEntity<String>("Problem while extracting document", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(doc.toString(), HttpStatus.OK);
	}
	
	public ResponseEntity<SingleFilm> doTransform(){
		scraper = new FilmDataScraper(doc);
		return new ResponseEntity<SingleFilm>(scraper.getFilm(), HttpStatus.OK);
	}
	
	public ResponseEntity<Object> doLoad(){
		insertFilm(scraper.getFilm());
		if(filmRepo.existsById(scraper.getFilm().getUrl())) {
		Object o = filmRepo.findById(scraper.getFilm().getUrl());
		return new ResponseEntity<Object>(o, HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		
	}
	
	public Optional<SingleFilm> getFilmByUrl(String url) {
		return filmRepo.findById(url);
	}
	
	
	public void insertFilm(SingleFilm film) {
		if(filmRepo.existsById(film.getUrl()) == false)
			filmRepo.insert(film);
	}
	
	public void updateFilm(SingleFilm film) {
		if(filmRepo.existsById(film.getUrl())) {
			filmRepo.deleteById(film.getUrl());
			filmRepo.insert(film);
		}
	}
	
	public void clearRepository() {
		filmRepo.deleteAll();
	}
}
