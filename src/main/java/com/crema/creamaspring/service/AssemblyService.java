package com.crema.creamaspring.service;

import com.crema.creamaspring.models.ForumThread;
import com.crema.creamaspring.models.Post;
import com.crema.creamaspring.models.Quote;
import com.crema.creamaspring.scraper.PostScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssemblyService {
    final ForumThreadService forumThreadService;
    final PostService postService;
    final QuoteService quoteService;
    final PostScraper postScraper;
    @Autowired
    public AssemblyService(ForumThreadService forumThreadService, PostService postService, QuoteService quoteService, PostScraper postScraper) {
        this.forumThreadService = forumThreadService;
        this.postService = postService;
        this.quoteService = quoteService;
        this.postScraper = postScraper;
    }

    public void assemblePosts() {
        List<ForumThread> forumThreads = forumThreadService.getAll();

        for (ForumThread forumThread : forumThreads) {

            System.out.println("---------------------------------");
            System.out.println("forumThreads size: " + forumThreads.size());
            System.out.println("---------------------------------");

            List<Post> posts = postScraper.retrieveData(forumThread);

            int count = 0;
            for (Post post : posts) {
                System.out.println("---------------------------------");
                System.out.println("posts size: " + posts.size());
                System.out.println("---------------------------------");

                postService.add(post);

                count += 1;
                System.out.println("---------------------------------");
                System.out.println("inner loop count: " + count);
                System.out.println("---------------------------------");
//                List<Quote> quotes = post.getQuotes();
//
//                for (Quote quote : quotes) {
//                    quoteService.addQuote(quote);
//                }
            }
            posts.clear();
        }
    }
}
