package com.java.film.scraper;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ListedFilmUrlScraper implements Scraper {
	Document doc;
	private final String CLASSNAME = "filmPreview__link";
	private final String PREFIX = "https://www.filmweb.pl";
	private List <Element> elements = new ArrayList<Element>();
	private String filmURL;
	private Element element;
	
	public ListedFilmUrlScraper(Document doc) {
		super();
		this.doc = doc;
	}
	
	public void scrap() {
		elements = doc.getElementsByClass(CLASSNAME);
		if(elements.isEmpty()) {
			System.out.println("no elements");
		} else {
			element = elements.get(0);
			filmURL = PREFIX.concat(element.attr("href"));
		}
	}

	public String getFilmURL() {
		return this.filmURL;
	}

}
