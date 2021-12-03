package com.crema.creamaspring.controllers;
import com.crema.creamaspring.models.Quote;
import com.crema.creamaspring.service.FilterService;
import com.crema.creamaspring.service.QuoteService;
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

    final QuoteService quoteService;
    final FilterService filterService;

    @Autowired
    public QuoteController(QuoteService quoteService, FilterService filterService) {
        this.quoteService = quoteService;
        this.filterService = filterService;
    }

    @GetMapping("/quotes")
    public ResponseEntity<List<Quote>> allQuotes() {
        List<Quote> quotes = quoteService.getAll();
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
        Quote quote = quoteService.findQuote(filteredWord);
        return new ResponseEntity<>(quote, HttpStatus.OK);
    }

    @PostMapping("/quotes/add") // Map ONLY POST Requests
    public ResponseEntity<Quote> addQuote (@RequestBody Quote quote) {
        quoteService.addQuote(quote);
        return new ResponseEntity<>(quote, HttpStatus.CREATED);
    }

    @PostMapping("/quotes/scrape")
    public ResponseEntity<String> addScrapedQuotes() {
//        quoteService.scrapeAndPersistQuotes();
        return new ResponseEntity<>("Detta gick SÅÅÅÅ bra", HttpStatus.CREATED);
    }
}
