package com.java.jsoup.scraper.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.java.film.scraper.FilmDataScraper;
import com.java.jsoup.connection.ExctractedDocument;
import com.java.jsoup.connection.JsoupConnector;

@TestInstance(Lifecycle.PER_CLASS)
class FilmDataScraperTest {
	final String FILM_URL = "https://www.filmweb.pl/serial/Gra+o+tron-2011-476848";
	FilmDataScraper scraper;
	Document doc;
	
	@BeforeAll
	public void initializeAll() {
		try {
			doc= new ExctractedDocument(new JsoupConnector()
					.connect(FILM_URL)).getDoc();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@BeforeEach
	public void initializeEach() {
		scraper = new FilmDataScraper(doc);
		scraper.getFilm().setUrl(FILM_URL);
	}
	
	@AfterEach
	public void destruct() {
		scraper = null;
	}
	
	@Test
	public void testScraper() {
		scraper.scrap();
		System.out.print(scraper.getFilm().toString());
	}

	
	@Test
	public void testTitle() {
		String string;

		scraper.scrapTitle();
		Assertions.assertEquals("Gra o tron", scraper.getFilm().getTitle());
	}
	
	@Test
	public void testType() {
		scraper.scrapType();
		Assertions.assertEquals("serial", scraper.getFilm().getType());
	}
	
	@Test
	public void testDescription() {			
			scraper.scrapDescription();
			Assertions.assertNotNull(scraper.getFilm().getDescription());
	}
	
	@Test
	public void testFilmPilot() {
		scraper.scrapFilmPilot();	
		Assertions.assertNotNull(scraper.getFilm().getFilmPilot());
	}
	
	@Test
	public void testPoster() {
		scraper.scrapPoster();
		Assertions.assertNotNull(scraper.getFilm().getPoster());
	}
	
	@Test public void testYear() {
		scraper.scrapYear();
		Assertions.assertEquals("2011-2019", scraper.getFilm().getYear() );
	}
	
	@Test
	public void testFilmwebRank() {
		scraper.scrapFilmwebRanking();
		Assertions.assertNotNull(scraper.getFilm().getFilmwebRanking());
	}
	
	@Test
	public void testCommunityRate() {
		scraper.scrapCommunityRate();
		Assertions.assertNotNull(scraper.getFilm().getCommunityRate());
	}
	
	@Test 
	public void testCreator() {
		scraper.scrapCreator();
		Assertions.assertNotNull(scraper.getFilm().getCreator());
	}
	
	@Test
	public void testImages() {
		scraper.scrapImages();
		Assertions.assertNotNull(scraper.getFilm().getImages());
	}
	
	@Test
	public void testGenres() {
		scraper.scrapGenres();
		Assertions.assertNotNull(scraper.getFilm().getGenres());
	}

	@Test
	public void testUserReviews() {
		scraper.scrapUserReviews();
		Assertions.assertNotNull(scraper.getFilm().getUserReviews());
		System.out.print(scraper.getFilm().getUserReviews());
	}
	
	@Test
	public void testCuriosities() {
		scraper.scrapCuriosities();
		Assertions.assertNotNull(scraper.getFilm().getCuriosities());
	}

	@Test
	public void testActors(){
		scraper.scrapActors();
		Assertions.assertNotNull(scraper.getFilm().getActors());

	}
}
