package com.crema.creamaspring.controllers;

import com.crema.creamaspring.models.Post;
import com.crema.creamaspring.services.PostService;
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

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> allPosts() {
        List<Post> posts = postService.getAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


}
