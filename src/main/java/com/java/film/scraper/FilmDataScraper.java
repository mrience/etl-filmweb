package com.java.film.scraper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		
	}

	public void scrapTitle() {
		String title = "";
		title = doc.getElementsByClass("inline filmTitle").first().child(0).ownText();
		film.setTitle(title);
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
		film.setPoster(doc.getElementsByClass("posterLightbox").select("img").attr("src"));
	}
	
	public void scrapFilmPilot() {
		film.setFilmPilot(doc.getElementsByClass("filmPlot bottom-15").first().child(0).ownText());
	}
	
	public void scrapDescription() {
		Element element;
		element = doc.getElementsByClass("pageBox filmMainDescription").first();
		film.setDescription(element.child(0).ownText().concat(element.select("span").text()));
	}
	
	public void scrapYear() {
		String str;
		str = doc.getElementsByClass("inline filmTitle").first().getElementsByTag("span").first().ownText();
	}//////
	
	public void scrapFilmwebRanking() {
		film.setFilmwebRanking(doc.getElementsByClass("worldRanking").first().ownText());
	}
	
	public void scrapCommunityRate() {
		film.setCommunityRate(doc.getElementsByClass("vertical-align light ratingRateValue")
				.first().child(0).ownText());
	}
	
	public void scrapCreator() {
		film.setCreator(doc.getElementsByClass("filmInfo bottom-15")
				.first().selectFirst("tr").select("a").eachText());
	}
	
	public void scrapImages() {
		film.setImages(doc.getElementsByClass("film-photos film-gallery-parent")
		.first().getElementsByClass("zoom-1").eachAttr("data-photo"));
	}
	
	public void scrapGenres() {
		film.setGenres(doc.getElementsByClass("filmInfo bottom-15")
				.first().select("td").last().select("a").eachText());
	}
	
	public void scrapUserReviews() {
		film.setUserReviews(doc.getElementsByClass("userReviews")
				.first().select("[href^=/review]").eachAttr("href"));
	}
	
	public void scrapCuriosities() {
		film.setCuriosities(doc.getElementsByClass("sep-space text")
				.first().getElementsByTag("span").eachText());
	}
	
	public void scrapActors() {
		Elements elements;
		List <String> actors = new ArrayList<>();
		elements = doc.getElementsByClass("filmCast filmCastCast").first().getElementsByTag("tr");
		for (Element el : elements) {
			actors.add(el.select("span").text());
		}
		film.setActors(actors);
	System.out.print(film.getActors());
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
