package com.crema.creamaspring.service;

import com.crema.creamaspring.models.ForumThread;
import com.crema.creamaspring.repositories.ForumThreadRepository;
import com.crema.creamaspring.scraper.ForumThreadScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumThreadService {

    @Autowired
    ForumThreadRepository forumThreadRepository;

    public List<ForumThread> allForumThreads() {
        List<ForumThread> forumThreads = forumThreadRepository.findAll();
        return forumThreads;
    }

    public void addScrapedForumThread() {
        ForumThreadScraper forumThreadScraper = new ForumThreadScraper();
        forumThreadRepository.saveAll(forumThreadScraper.retrieveData());
    }
}
