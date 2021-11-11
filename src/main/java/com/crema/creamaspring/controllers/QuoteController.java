package com.crema.creamaspring.controllers;

import com.crema.creamaspring.models.Quote;
import com.crema.creamaspring.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") //CORS fix, koppla ihop med react
@RestController
@RequestMapping("/api")
public class QuoteController {

    @Autowired
    QuoteRepository quoteRepository;

    @GetMapping("/quotes")
    public ResponseEntity<List<Quote>> allQuotes() {
        List<Quote> quotes = (List<Quote>)quoteRepository.findAll();
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }

    @GetMapping("/quotes/find")
    public ResponseEntity<List<Quote>> findQuotes(@RequestParam String text) {
        List<Quote> quotes = quoteRepository.findQuotesByTextContaining(text);
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }

    @PostMapping("/quotes/add") // Map ONLY POST Requests
    public ResponseEntity<Quote> addQuote (@RequestBody Quote quote) {
        quoteRepository.save(quote);
        return new ResponseEntity<>(quote, HttpStatus.CREATED);
    }



}
