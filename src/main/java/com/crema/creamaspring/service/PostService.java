package com.crema.creamaspring.service;

import com.crema.creamaspring.models.ForumThread;
import com.crema.creamaspring.models.Post;
import com.crema.creamaspring.repositories.ForumThreadRepository;
import com.crema.creamaspring.repositories.PostRepository;
import com.crema.creamaspring.scraper.PostScraper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    final PostRepository postRepository;
    final ForumThreadRepository forumThreadRepository;
    final PostScraper postScraper;

    public PostService(PostRepository postRepository, ForumThreadRepository forumThreadRepository, PostScraper postScraper) {
        this.postRepository = postRepository;
        this.forumThreadRepository = forumThreadRepository;
        this.postScraper = postScraper;
    }

    public List<Post> allPosts() {
        return postRepository.findAll();
    }

    public void saveAllPosts() {
        List<ForumThread> forumThreads = forumThreadRepository.findAll();

        for (ForumThread forumThread : forumThreads) {
            postRepository.saveAll(postScraper.retrieveData(forumThread));
            try {
                System.out.println("Going to sleep");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
