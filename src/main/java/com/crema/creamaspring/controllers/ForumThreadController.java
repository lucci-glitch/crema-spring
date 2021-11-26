package com.crema.creamaspring.controllers;

import com.crema.creamaspring.models.ForumThread;
import com.crema.creamaspring.service.ForumThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ForumThreadController {

    @Autowired
    ForumThreadService forumThreadService;

    @GetMapping("/forumthreads")
    public ResponseEntity<List<ForumThread>> allForumThreads() {
        List<ForumThread> forumThreads = forumThreadService.allForumThreads();
        return new ResponseEntity<>(forumThreads, HttpStatus.OK);
    }
}
