package com.java.film.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.java.csv.CsvGenerator;
import com.java.film.entity.SingleFilm;
import com.java.film.scraper.FilmDataScraper;
import com.java.jsoup.connection.ExctractedDocument;
import com.java.jsoup.connection.JsoupConnector;
import com.java.mongoDB.FilmRepository;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@Service
public class FilmService implements FilmServiceInterface {

	private Document doc;
	private SingleFilm film;
	
	@Autowired
	private FilmDataScraper scraper;
	@Autowired
	FilmRepository filmRepo;

	
	/* (non-Javadoc)
	 * @see com.java.film.service.FilmServiceInterface#extract(java.lang.String)
	 */
	@Override
	public void extract(String url) {
		JsoupConnector conn = new JsoupConnector();
		Connection con = conn.connect(url);
		doc = null;
		try {
			ExctractedDocument exDoc = new ExctractedDocument(con);
			doc = exDoc.getDoc();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.java.film.service.FilmServiceInterface#extractResponse(java.lang.String)
	 */
	@Override
	public ResponseEntity<String> extractResponse(String url) {
		extract(url);
		if (doc != null)
			return new ResponseEntity<String>(doc.outerHtml(), HttpStatus.OK);
		else return new ResponseEntity<String>("empty document", HttpStatus.CONFLICT);
	}
	
	/* (non-Javadoc)
	 * @see com.java.film.service.FilmServiceInterface#transform()
	 */
	@Override
	public void transform() {
		scraper.setDoc(doc);
		scraper.setFilm();
		scraper.scrap();
	}
	
	/* (non-Javadoc)
	 * @see com.java.film.service.FilmServiceInterface#transformResponse()
	 */
	@Override
	public ResponseEntity<SingleFilm> transformResponse(){
		transform();
		return new ResponseEntity<SingleFilm>(scraper.getFilm(), HttpStatus.OK);
	}
	
	/* (non-Javadoc)
	 * @see com.java.film.service.FilmServiceInterface#load()
	 */
	@Override
	public void load() {
		if(!filmRepo.existsById(scraper.getFilm().getUrl())) {
			insertFilm(scraper.getFilm());
			film = filmRepo.findById(scraper.getFilm().getUrl()).get();	
		} else film = null;
	}
	
	/* (non-Javadoc)
	 * @see com.java.film.service.FilmServiceInterface#loadResponse()
	 */
	@Override
	public ResponseEntity<SingleFilm> loadResponse(){
		load();
		if(film != null) {
			cleanFilmData();
			return new ResponseEntity<SingleFilm>(film, HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	/* (non-Javadoc)
	 * @see com.java.film.service.FilmServiceInterface#completeEtlProcess(java.lang.String)
	 */
	@Override
	public ResponseEntity<SingleFilm> completeEtlProcess(String url){
		extract(url);
		transform();
		return loadResponse();
	}
	
	/* (non-Javadoc)
	 * @see com.java.film.service.FilmServiceInterface#cleanRepo()
	 */
	@Override
	public ResponseEntity<String> cleanRepo(){
		if(filmRepo.count() != 0)
			filmRepo.deleteAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/* (non-Javadoc)
	 * @see com.java.film.service.FilmServiceInterface#insertFilm(com.java.film.entity.SingleFilm)
	 */
	@Override
	public void insertFilm(SingleFilm film) {
		if(filmRepo.existsById(film.getUrl()) == false)
			filmRepo.insert(film);
	}
	
	/* (non-Javadoc)
	 * @see com.java.film.service.FilmServiceInterface#updateFilm(java.lang.String)
	 */
	@Override
	public ResponseEntity<SingleFilm> updateFilm(String url) {
		if(filmRepo.existsById(url)) {
			SingleFilm film = filmRepo.findById(url).get();
			extract(url);
			transform();
			if(scraper.getFilm().equals(film)) {
				filmRepo.save(scraper.getFilm());
			}
		}
		return new ResponseEntity<>(film, HttpStatus.OK);
	}

	/* (non-Javadoc)
	 * @see com.java.film.service.FilmServiceInterface#cleanFilmData()
	 */
	@Override
	public void cleanFilmData() {
		doc = null;
		scraper = null;
	}

	/* (non-Javadoc)
	 * @see com.java.film.service.FilmServiceInterface#exportCSV()
	 */
	@Override
	public ResponseEntity<InputStreamResource> exportCSV() throws IOException, NullPointerException{

		List <SingleFilm> films = filmRepo.findAll();
		try {
			new CsvGenerator(films).generateCsv();
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e){
			e.printStackTrace();
		}
		ClassPathResource csvFile = new ClassPathResource("/Users/mateusz/eclipse-workspace/etl-filmweb/src/main/resources/Films.csv");
		return new ResponseEntity<>(new InputStreamResource(csvFile.getInputStream()), HttpStatus.OK);
	}
	
	/* (non-Javadoc)
	 * @see com.java.film.service.FilmServiceInterface#exportTxt()
	 */
	@Override
	public ResponseEntity<String> exportTxt(String url){
		
		SingleFilm film = filmRepo.findById(url).get();
		if (film.equals(null))
			return new ResponseEntity<>("film available", HttpStatus.CONFLICT);
		else
			return new ResponseEntity<> (film.toString(), HttpStatus.OK);
	}
	
	/* (non-Javadoc)
	 * @see com.java.film.service.FilmServiceInterface#getFilmById(java.lang.String)
	 */
	@Override
	public SingleFilm getFilmById(String title) {
		return filmRepo.findById(title).get();
	}
	
	/* (non-Javadoc)
	 * @see com.java.film.service.FilmServiceInterface#findAll()
	 */
    @Override
	public ResponseEntity <List> findAll(){
		return new ResponseEntity<>(filmRepo.findAll(), HttpStatus.OK);
	}

}
