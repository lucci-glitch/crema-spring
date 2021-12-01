package com.crema.creamaspring.controllers;

import com.crema.creamaspring.models.ForumThread;
import com.crema.creamaspring.service.ForumThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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
        List<ForumThread> forumThreads = forumThreadService.allForumThreads();
        return new ResponseEntity<>(forumThreads, HttpStatus.OK);
    }

    @PostMapping("/forumthreads/scrape")
    public ResponseEntity<String> addScrapedForumThreads() {
        forumThreadService.addScrapedForumThread();
        return new ResponseEntity<>("Detta gick SÅÅÅÅ TITTA alla våra titltar!", HttpStatus.CREATED);
    }
}
