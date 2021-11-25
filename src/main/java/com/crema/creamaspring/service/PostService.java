package com.crema.creamaspring.service;

import com.crema.creamaspring.models.Post;
import com.crema.creamaspring.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public List<Post> allPosts() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }
}
