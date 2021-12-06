package com.crema.creamaspring.controllers;
import com.crema.creamaspring.models.Quote;
import com.crema.creamaspring.components.filter.Filter;
import com.crema.creamaspring.services.QuoteService;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000") //CORS fix, koppla ihop med react
@Log4j2
@RestController
@RequestMapping("/api")
public class QuoteController {

    final QuoteService quoteService;
    final Filter filter;

    @Autowired
    public QuoteController(QuoteService quoteService, Filter filter) {
        this.quoteService = quoteService;
        this.filter = filter;
    }

    @GetMapping("/quotes")
    public ResponseEntity<List<Quote>> allQuotes() {
        log.info("Getting all quotes");
        List<Quote> quotes = quoteService.getAll();
        if (quotes.isEmpty()) {
            log.error("No quotes");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }

    @GetMapping("/quotes/find")
    public ResponseEntity<Quote>findQuotes(@RequestParam String text) throws JSONException, IOException {
        String filteredWord = filter.filterSentence(text);
        log.info("findQuote called on this word");
        Quote quote = quoteService.getRandomMatchingQuote(filteredWord);
        return new ResponseEntity<>(quote, HttpStatus.OK);
    }
}
