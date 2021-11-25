package com.crema.creamaspring.controllers;

import com.crema.creamaspring.models.ForumThread;
import com.crema.creamaspring.models.Post;
import com.crema.creamaspring.models.Quote;
import com.crema.creamaspring.repositories.ForumThreadRepository;
import com.crema.creamaspring.repositories.PostRepository;
import com.crema.creamaspring.repositories.QuoteRepository;
import com.crema.creamaspring.scraper.QuoteScraper;
import com.crema.creamaspring.scraper.TitleScraper;
import com.crema.creamaspring.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@CrossOrigin(origins = "http://localhost:3000") //CORS fix, koppla ihop med react
@RestController
@RequestMapping("/api")
public class QuoteController {

    @Autowired
    QueryService queryService;

    @GetMapping("/quotes")
    public ResponseEntity<List<Quote>> allQuotes() {
        List<Quote> quotes = queryService.allQuotes();
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }

    @GetMapping("/quotes/find")
    public ResponseEntity<Quote>findQuotes(@RequestParam String text) {
        Quote quote = queryService.findQuote(text);
        return new ResponseEntity<>(quote, HttpStatus.OK);
    }

    @PostMapping("/quotes/add") // Map ONLY POST Requests
    public ResponseEntity<Quote> addQuote (@RequestBody Quote quote) {
        queryService.addQuote(quote);
        return new ResponseEntity<>(quote, HttpStatus.CREATED);
    }

    @PostMapping("/quotes/scrape")
    public ResponseEntity<String> addScrapedQuotes() {
        queryService.scrapeAndPersistQuotes();
        return new ResponseEntity<>("Detta gick SÅÅÅÅ bra", HttpStatus.CREATED);
    }

    @PostMapping("/quotes/scrape/title")
    public ResponseEntity<String> addScrapedTitles() {
        queryService.addScrapedTitles();
        return new ResponseEntity<>("Detta gick SÅÅÅÅ TITTA alla våra titltar!", HttpStatus.CREATED);
    }
}
