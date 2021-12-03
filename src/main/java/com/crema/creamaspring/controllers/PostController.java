package com.crema.creamaspring.controllers;

import com.crema.creamaspring.models.Post;
import com.crema.creamaspring.scraper.PostScraper;
import com.crema.creamaspring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class PostController {

    final PostService postService;
    final PostScraper postScraper;

    @Autowired
    public PostController(PostService postService, PostScraper postScraper) {
        this.postService = postService;
        this.postScraper = postScraper;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> allPosts() {
        List<Post> posts = postService.getAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("/posts/scrape")
    public ResponseEntity<String> scrapePosts() {
        postService.saveAllPosts();
        return new ResponseEntity<>("Post scrape completed", HttpStatus.OK);
    }

}
