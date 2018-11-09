package com.java.jsoup.connection;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;

public class ExctractedDocument {
	Document doc;
	Connection conn;
	
	public ExctractedDocument(Connection conn) throws IOException {
		this.conn = conn;
		this.doc = conn.get();
	}
	
	public Document getDoc() {
		return this.doc;
	}
	
	public void setDoc(Document doc) {
		this.doc = doc;
	}
	
}
