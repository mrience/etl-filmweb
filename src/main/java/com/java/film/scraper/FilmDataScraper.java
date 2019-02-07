package com.java.film.scraper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.java.film.entity.FilmFactory;
import com.java.film.entity.SingleFilm;
import org.springframework.stereotype.Component;

@Component
public class FilmDataScraper implements Scraper{
	private Document doc;
	private FilmFactory factory = new FilmFactory();
	private SingleFilm film;
	private final static String SERIAL = "serial", FILM = "film";
		
	public FilmDataScraper(Document doc) {
		super();
		this.doc = doc;
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
		Element el = null;
		try {
            el = doc.getElementsByClass("inline filmTitle").first().child(0);
        } catch (NullPointerException e) {
		    e.printStackTrace();
		};
		if(el != null)
            film.setTitle(el.ownText());
		else
            film.setTitle("");
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
		Elements els = null;
		try {
            els = doc.getElementsByClass("posterLightbox").select("img");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
		if(els != null)
			film.setPoster(els.attr("src"));
		else
            film.setPoster("");
    }
	
	public void scrapFilmPilot() {
		Element el = null;
		try {
            el = doc.getElementsByClass("filmPlot bottom-15").first().child(0);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
		if (el != null)
			film.setFilmPilot(el.ownText());
		else
            film.setFilmPilot("");
    }
	
	public void scrapDescription() {
		Element el = null;
		try {
            el = doc.getElementsByClass("pageBox filmMainDescription").first();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
		if (el != null)
			film.setDescription(el.child(0).ownText().concat(el.select("span").text()));
		else
            film.setDescription("");
    }
	
	public void scrapYear() {
		String str;
		Element el = null;
		try {
            el = doc.getElementsByClass("inline filmTitle")
                    .first().getElementsByTag("span").first();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
		if(el != null) {
			str = el.ownText();
			film.setYear(str.substring(1, str.indexOf(')')));
		} else
            film.setYear("");
    }
	
	public void scrapFilmwebRanking() {
	    Element el = null;
	    try {
            el = doc.getElementsByClass("worldRanking").first();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
		if (el != null)
			film.setFilmwebRanking(el.ownText());
		else
		    film.setFilmwebRanking("");
    }
	
	public void scrapCommunityRate() {
		Element el = null;
		try {
            el = doc.select("[itemprop = ratingValue]").first();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
		if (el != null)
			film.setCommunityRate(el.text());
		else
            film.setCommunityRate("");
    }
	
	public void scrapCreator() {
		Elements els = null;
		try {
            els = doc.getElementsByClass("filmInfo bottom-15")
                    .first().selectFirst("tr").select("a");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
		if (els != null)
			film.setCreator(els.eachText());
		else
            film.setCreator(List.of());
    }
	
	public void scrapImages() {
		Elements els = null;
		try {
            els = doc.getElementsByClass("film-photos film-gallery-parent")
                    .first().getElementsByClass("zoom-1");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
		if (els != null)
			film.setImages(els.eachAttr("data-photo"));
		else
			film.setImages(List.of());
	}
	
	public void scrapGenres() {
		Elements els = null;
		try {
            els = doc.getElementsByClass("filmInfo bottom-15")
                    .first().select("td").last().select("a");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
		if (els != null)
			film.setGenres(els.eachText());
		else
            film.setGenres(List.of());
	}
	
	public void scrapUserReviews() {
		Elements els = null;
		final String PREFIX = "https://www.filmweb.pl";
		List <String> usrReviews = new ArrayList<>();
		try {
            els = doc.getElementsByClass("userReviews").first().select("p");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
		if (els != null) {
			for (String str : els.select("[href^=/review]").eachAttr("href")) {
				usrReviews.add(PREFIX.concat(str));
			}
			film.setUserReviews(usrReviews);
		} else
            film.setUserReviews(List.of());
    }
	
	public void scrapCuriosities() {
		Elements els = null;
		try {
            els = doc.getElementsByClass("sep-space text")
                    .first().getElementsByTag("span");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
		if (els != null)
            film.setCuriosities(els.eachText());
		else
            film.setCuriosities(List.of());
    }
	
	public void scrapActors() {
		Elements elements = null;
		Map <String, String> actors = new HashMap<>();
		try {
            elements = doc.getElementsByClass("filmCast filmCastCast").first().getElementsByTag("tr");
		} catch (NullPointerException e) {
		    e.printStackTrace();
		};
		if (elements != null) {
            for (Element el : elements) {
                actors.put(el.select("[itemprop = name]").text(), el.select("[itemprop = characterName]").text());
            }
            film.setActors(actors);
        } else
            film.setActors(Map.of());
	}
	
	public void setFilmURL(String URL) {
		this.film.setUrl(URL);
	}
	
	public SingleFilm getFilm() {
		return film;
	}

	public void setFilm(){
		film = factory.createFilm();
	};

	public Document getDoc() {
		return doc;
	}
	
	public void setDoc(Document doc) {
		this.doc = doc;
	}
	
}
