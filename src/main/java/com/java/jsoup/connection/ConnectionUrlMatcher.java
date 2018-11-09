package com.java.jsoup.connection;


public class ConnectionUrlMatcher{
	private final String PREFIX = "https://www.filmweb.pl/search?q=";
	private String filmUrl;
	
	public ConnectionUrlMatcher(String title) {
		filmUrl = PREFIX.concat(title.replace(" ", "+"));
	}

	public String getUrl() {
		return filmUrl;
	}
}
