package com.crema.creamaspring.controllers;

import com.crema.creamaspring.models.Post;
import com.crema.creamaspring.services.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Log4j2
@RestController
@RequestMapping("/api")
public class PostController {

    final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> allPosts() {
        log.info("Getting all posts");
        List<Post> posts = postService.getAll();
        if (posts.isEmpty()) {
            log.error("No posts");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


}
