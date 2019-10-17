package com.java.film.controllers;

import com.java.film.service.FilmServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExportFilesController {

    @Autowired
    FilmServiceInterface service;

    @PostMapping(value = "/exportCSV", produces="text/csv")
    public ResponseEntity <InputStreamResource> exportCSV() {
        return service.exportCSV();
    }

    @PostMapping("/exportFilmTxt")
    public ResponseEntity<String> exportFilmTxt(@RequestParam String url) {
        return service.exportTxt(url);
    }
}
