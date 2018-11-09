package com.java.jsoup.connection.test;


import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.java.jsoup.connection.ConnectionUrlMatcher;
import com.java.jsoup.connection.JsoupConnector;

class ConnectionUrlTest {

	@Test
	public void urlAndTitleShouldBeMatched() {
		final String TITLE = "Game of Thrones";

		Assertions.assertEquals("https://www.filmweb.pl/search?q=Game+of+Thrones",
				new ConnectionUrlMatcher(TITLE).getUrl());
	}

}
