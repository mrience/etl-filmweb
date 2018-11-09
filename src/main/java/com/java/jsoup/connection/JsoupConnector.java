package com.java.jsoup.connection;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class JsoupConnector {
	private Connection connection;
	
	public Connection connect(String url) {
		connection = Jsoup.connect(url);
		return connection;
	}
	
}