package com.java.csv;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;


import com.java.film.entity.SingleFilm;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class CsvGenerator {

	private final String OUTPUT_FILE = "/Users/mateusz/eclipse-workspace/"
			+ "etl-filmweb/src/main/resources/Films.csv";
	private List <SingleFilm> films;


	public CsvGenerator(List<SingleFilm> films) {
		super();
		this.films = films;
	}


	public void generateCsv() throws NullPointerException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException{
		
		try {
			Writer writer = new FileWriter(OUTPUT_FILE);
			StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
			beanToCsv.setOrderedResults(false);
			beanToCsv.write(films);
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}				
	}
	
}
