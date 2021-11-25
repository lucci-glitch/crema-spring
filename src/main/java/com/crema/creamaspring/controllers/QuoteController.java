package com.crema.creamaspring.controllers;

import com.crema.creamaspring.models.ForumThread;
import com.crema.creamaspring.models.Post;
import com.crema.creamaspring.models.Quote;
import com.crema.creamaspring.repositories.ForumThreadRepository;
import com.crema.creamaspring.repositories.PostRepository;
import com.crema.creamaspring.repositories.QuoteRepository;
import com.crema.creamaspring.scraper.QuoteScraper;
import com.crema.creamaspring.scraper.TitleScraper;
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
    QuoteRepository quoteRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ForumThreadRepository forumThreadRepository;

    @GetMapping("/quotes")
    public ResponseEntity<List<Quote>> allQuotes() {
        List<Quote> quotes = (List<Quote>)quoteRepository.findAll();
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }

    @GetMapping("/quotes/find")
    public ResponseEntity<Quote>findQuotes(@RequestParam String text) {
        List<Quote> quotes = quoteRepository.findQuotesByTextContaining(text);
        int randomNum = ThreadLocalRandom.current().nextInt(0, quotes.size());

        return new ResponseEntity<Quote>(quotes.get(randomNum), HttpStatus.OK);
    }

    @PostMapping("/quotes/add") // Map ONLY POST Requests
    public ResponseEntity<Quote> addQuote (@RequestBody Quote quote) {
        quoteRepository.save(quote);
        return new ResponseEntity<>(quote, HttpStatus.CREATED);
    }

    @PostMapping("/quotes/scrape")
    public ResponseEntity<String> addScrapedQuotes(){
        QuoteScraper quoteScraper = new QuoteScraper();

        quoteRepository.saveAll(quoteScraper.retrieveData());

        return new ResponseEntity<>("Detta gick SÅÅÅÅ bra",HttpStatus.CREATED);
    }

    @PostMapping("/quotes/scrape/title")
    public ResponseEntity<String> addScrapedTitles(){
        TitleScraper titleScraper = new TitleScraper();
        forumThreadRepository.saveAll(titleScraper.retrieveData());

        return new ResponseEntity<>("Detta gick SÅÅÅÅ TITTA alla våra titltar!",HttpStatus.CREATED);
    }

    @GetMapping("forumthreads/get")
    public ResponseEntity<List<ForumThread>> allForumThreads() {
        List<ForumThread> forumThreads = forumThreadRepository.findAll();
        return new ResponseEntity<>(forumThreads, HttpStatus.OK);
    }

    @GetMapping("posts/get")
    public ResponseEntity<List<Post>> allPosts() {
        List<Post> posts = postRepository.findAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
