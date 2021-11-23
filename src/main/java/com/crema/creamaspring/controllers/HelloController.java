package com.crema.creamaspring.controllers;

import com.crema.creamaspring.scraper.QuoteScraper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class HelloController {

    @GetMapping("/hello")
    public void Hello() {
        QuoteScraper quoteScraper = new QuoteScraper();

        quoteScraper.retrieveData();

    }

}
