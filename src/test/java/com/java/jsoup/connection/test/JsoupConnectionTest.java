package com.java.jsoup.connection.test;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.java.jsoup.connection.JsoupConnector;


public class JsoupConnectionTest {

	@Test
	public void shouldReturnTrueIfPageHasText() {
		boolean hasText = false;	
		try {
			hasText = new JsoupConnector().connect("http://en.wikipedia.org/").get().getElementById("Did_you_know...").hasText();
		}catch(IOException e) {
				e.printStackTrace();
			}		
		Assertions.assertTrue(hasText);
	}

}
