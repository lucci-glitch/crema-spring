package com.crema.creamaspring.controllers;

import com.crema.creamaspring.models.ForumThread;
import com.crema.creamaspring.services.ForumThreadService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@Log4j2
public class ForumThreadController {

    final ForumThreadService forumThreadService;

    @Autowired
    public ForumThreadController(ForumThreadService forumThreadService) {
        this.forumThreadService = forumThreadService;
    }

    @GetMapping("/forumthreads")
    public ResponseEntity<List<ForumThread>> allForumThreads() {
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");
        List<ForumThread> forumThreads = forumThreadService.getAll();
        return new ResponseEntity<>(forumThreads, HttpStatus.OK);
    }

    @PostMapping("/forumthreads/scrape")
    public ResponseEntity<String> addScrapedForumThreads() {
        forumThreadService.scrapeAndPersistForumThreads();
        return new ResponseEntity<>("Detta gick SÅÅÅÅ TITTA alla våra titlar!", HttpStatus.CREATED);
    }
}
