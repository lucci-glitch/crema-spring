package com.crema.creamaspring.controllers;

import com.crema.creamaspring.models.ForumThread;
import com.crema.creamaspring.services.ForumThreadService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/api")
public class ForumThreadController {

    final ForumThreadService forumThreadService;

    @Autowired
    public ForumThreadController(ForumThreadService forumThreadService) {
        this.forumThreadService = forumThreadService;
    }

    @GetMapping("/forumthreads")
    public ResponseEntity<List<ForumThread>> allForumThreads() {
        log.info("Getting all forum threads");
        List<ForumThread> forumThreads = forumThreadService.getAll();
        if (forumThreads.isEmpty()) {
            log.error("No forum threads");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(forumThreads, HttpStatus.OK);
    }

    @PostMapping("/forumthreads/scrape")
    public ResponseEntity<String> addScrapedForumThreads() {
        log.info("Scraping and adding forum threads");
        forumThreadService.scrapeAndPersistForumThreads();
        return new ResponseEntity<>("Detta gick SÅÅÅÅ TITTA alla våra titlar!", HttpStatus.CREATED);
    }
}
