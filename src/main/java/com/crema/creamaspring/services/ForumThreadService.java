package com.crema.creamaspring.services;

import com.crema.creamaspring.models.ForumThread;
import com.crema.creamaspring.repositories.ForumThreadRepository;
import com.crema.creamaspring.components.scraper.ForumThreadScraper;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ForumThreadService {

    final ForumThreadRepository forumThreadRepository;
    final ForumThreadScraper forumThreadScraper;

    public ForumThreadService(ForumThreadRepository forumThreadRepository, ForumThreadScraper forumThreadScraper) {
        this.forumThreadRepository = forumThreadRepository;
        this.forumThreadScraper = forumThreadScraper;
    }

    public List<ForumThread> getAll() {
        return forumThreadRepository.findAll();
    }

    public void scrapeAndPersistForumThreads() {
        forumThreadRepository.saveAll(forumThreadScraper.retrieveData("f97"));
    }

}
