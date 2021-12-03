package com.crema.creamaspring.services;

import com.crema.creamaspring.models.ForumThread;
import com.crema.creamaspring.models.Post;
import com.crema.creamaspring.components.scraper.ContentScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssemblyService {
    final ForumThreadService forumThreadService;
    final PostService postService;
    final ContentScraper contentScraper;

    @Autowired
    public AssemblyService(ForumThreadService forumThreadService, PostService postService, ContentScraper contentScraper) {
        this.forumThreadService = forumThreadService;
        this.postService = postService;
        this.contentScraper = contentScraper;
    }

    public void assemblePosts() {
        List<ForumThread> forumThreads = forumThreadService.getAll();

        for (ForumThread forumThread : forumThreads) {

            List<Post> posts = contentScraper.retrieveData(forumThread);

            for (Post post : posts) {
                postService.add(post);
            }
            posts.clear();
        }
    }
}
