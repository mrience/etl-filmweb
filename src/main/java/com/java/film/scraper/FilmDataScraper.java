package com.java.film.scraper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.java.film.entity.FilmFactory;
import com.java.film.entity.SingleFilm;

public class FilmDataScraper implements Scraper{
	private Document doc;
	private FilmFactory factory = new FilmFactory();
	private SingleFilm film;
	private final String SERIAL = "serial", FILM = "film";
		
	public FilmDataScraper(Document doc) {
		super();
		this.doc = doc;
		film = factory.createFilm();
	}

	public FilmDataScraper() {
		super();
	}

	public void scrap() {
		scrapTitle();
		scrapActors();
		scrapCommunityRate();
		scrapCreator();
		scrapCuriosities();
		scrapDescription();
		scrapFilmPilot();
		scrapFilmwebRanking();
		scrapGenres();
		scrapImages();
		scrapPoster();
		scrapType();
		scrapUserReviews();
		scrapYear();
	}

	public void scrapTitle() {
		Element el;
		el = doc.getElementsByClass("inline filmTitle").first().child(0);
		if(el != null)
			film.setTitle(el.ownText());
		else film.setTitle("");
	}
	
	public void scrapType() {
		if(film.getUrl().contains("serial"))
			film.setType(SERIAL);
		else {
			if(film.getUrl().contains(FILM))
				film.setType(FILM);
			else {
				film.setType("");
			}
		}
	}
	
	public void scrapPoster() {
		Elements els;
		els = doc.getElementsByClass("posterLightbox").select("img");
		if(els != null)
			film.setPoster(els.attr("src"));
		else film.setPoster("");
	}
	
	public void scrapFilmPilot() {
		Element el;
		el = doc.getElementsByClass("filmPlot bottom-15").first().child(0);
		if(el != null)
			film.setFilmPilot(el.ownText());
		else 
			film.setFilmPilot("");
	}
	
	public void scrapDescription() {
		Element el;
		el = doc.getElementsByClass("pageBox filmMainDescription").first();
		if(el != null) 
			film.setDescription(el.child(0).ownText().concat(el.select("span").text()));
		else
			film.setDescription("");
	}
	
	public void scrapYear() {
		String str;
		Element el;
		el = doc.getElementsByClass("inline filmTitle")
				.first().getElementsByTag("span").first();
		if(el != null) {
			str = el.ownText();
			film.setYear(str.substring(1, str.indexOf(')')));
		} else film.setYear("");
	}
	
	public void scrapFilmwebRanking() {
		Element el = doc.getElementsByClass("worldRanking").first();
		if(el != null) 
			film.setFilmwebRanking(el.ownText());
		 else film.setFilmwebRanking("");
	}
	
	public void scrapCommunityRate() {
		String el = doc.getElementsByClass("ratingInfo").text();
		film.setCommunityRate(el);
			System.out.println(el);
	}
	
	public void scrapCreator() {
		Elements els;
		els = doc.getElementsByClass("filmInfo bottom-15")
				.first().selectFirst("tr").select("a");
		if(els != null)
			film.setCreator(els.eachText());
		else 
			film.setCreator(List.of());
	}
	
	public void scrapImages() {
		Elements els;
		els = doc.getElementsByClass("film-photos film-gallery-parent")
				.first().getElementsByClass("zoom-1");
		if(els != null)
			film.setImages(els.eachAttr("data-photo"));
		else
			film.setImages(List.of());
	}
	
	public void scrapGenres() {
		Elements els;
		els = doc.getElementsByClass("filmInfo bottom-15")
				.first().select("td").last().select("a");
		if(els != null)
			film.setGenres(els.eachText());
		else
			film.setGenres(List.of());
	}
	
	public void scrapUserReviews() {
		Element el;
;
		el = doc.getElementsByClass("userReviews")
				.first();
		if(el!= null)
			film.setUserReviews(el.select("[href^=/review]").eachAttr("href"));
		else
			film.setUserReviews(List.of());
	}
	
	public void scrapCuriosities() {
		Elements els;
		els = doc.getElementsByClass("sep-space text")
				.first().getElementsByTag("span");
		if(els != null)
			film.setCuriosities(els.eachText());
		else
			film.setCuriosities(List.of());
	}
	
	public void scrapActors() {
		Elements elements;
		Map <String, String> actors = new HashMap<>();
		elements = doc.getElementsByClass("filmCast filmCastCast").first().getElementsByTag("tr");
		if(elements != null) {
		for (Element el : elements) {
			actors.put(el.select("[itemprop = name]").text(), el.select("[itemprop = characterName]").text());
		}
		film.setActors(actors);
		} else film.setActors(Map.of());
	}
	
	public void setFilmURL(String URL) {
		this.film.setUrl(URL);
	}
	
	public SingleFilm getFilm() {
		return film;
	}

	public void setFilm(SingleFilm film) {
		this.film = film;
	}

	public Document getDoc() {
		return doc;
	}
	
	public void setDoc(Document doc) {
		this.doc = doc;
	}
	
}
