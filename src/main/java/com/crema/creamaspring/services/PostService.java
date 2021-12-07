package com.crema.creamaspring.services;

import com.crema.creamaspring.models.Post;
import com.crema.creamaspring.repositories.PostRepository;
import com.crema.creamaspring.components.scraper.ContentScraper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class PostService {
    final PostRepository postRepository;
    final ContentScraper contentScraper;

    public PostService(PostRepository postRepository, ContentScraper contentScraper) {
        this.postRepository = postRepository;
        this.contentScraper = contentScraper;
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public void add(Post post) {
        //TODO: validate post
        postRepository.save(post);
    }
}
