package com.java.film.controllers;

import com.java.film.entity.SingleFilm;
import com.java.film.service.FilmServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EtlController {

    @Autowired
    FilmServiceInterface service;

    @PostMapping("/extract")
    public ResponseEntity<String> extractSubmit(@RequestParam String url) {
        return service.extractResponse(url);
    }

    @PostMapping("/transform")
    public ResponseEntity<SingleFilm> transformSubmit(){
        return service.transformResponse();
    }

    @PostMapping("/load")
    public ResponseEntity<SingleFilm> loadSubmit(){
        return service.loadResponse();
    }

    @PostMapping("/completeEtlProcess")
    @ResponseBody
    public ResponseEntity<SingleFilm> completeEtlProcess(@RequestParam String url){
        return service.completeEtlProcess(url);
    }
}
