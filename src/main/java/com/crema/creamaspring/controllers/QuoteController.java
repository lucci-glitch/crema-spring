package com.crema.creamaspring.controllers;
import com.crema.creamaspring.models.Quote;
import com.crema.creamaspring.service.FilterService;
import com.crema.creamaspring.service.QueryService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000") //CORS fix, koppla ihop med react
@RestController
@RequestMapping("/api")
public class QuoteController {

    @Autowired
    QueryService queryService;

    @Autowired
    FilterService filterService;

    @GetMapping("/quotes")
    public ResponseEntity<List<Quote>> allQuotes() {
        List<Quote> quotes = queryService.allQuotes();
        if (quotes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }

    @GetMapping("/quotes/find")
    public ResponseEntity<Quote>findQuotes(@RequestParam String text) throws JSONException, IOException {
        String filteredWord = filterService.filterSentence(text);
        System.out.println("------------------------------------------------");
        System.out.println("findQuote called on this word: ");
        System.out.println(filteredWord);
        System.out.println("------------------------------------------------");
        Quote quote = queryService.findQuote(filteredWord);
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
