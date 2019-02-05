package com.java.jsoup.scraper.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.java.film.scraper.ListedFilmUrlScraper;
import com.java.jsoup.connection.ConnectionUrlMatcher;
import com.java.jsoup.connection.ExctractedDocument;
import com.java.jsoup.connection.JsoupConnector;


class ListedFilmUrlScraperTest {

	@Test
	void urlReturnsProperly() throws IOException {
		Document doc;

		doc = new ExctractedDocument(new JsoupConnector()
					.connect(new ConnectionUrlMatcher("Gra o tron")
					.getUrl())).getDoc();
		ListedFilmUrlScraper scraper = new ListedFilmUrlScraper(doc);
		scraper.scrap();
		Assertions.assertEquals("https://www.filmweb.pl/serial/Gra+o+tron-2011-476848", scraper.getFilmURL());
	}
}
