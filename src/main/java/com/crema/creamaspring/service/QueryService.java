package com.crema.creamaspring.service;

import com.crema.creamaspring.models.ForumThread;
import com.crema.creamaspring.models.Post;
import com.crema.creamaspring.models.Quote;
import com.crema.creamaspring.repositories.ForumThreadRepository;
import com.crema.creamaspring.repositories.PostRepository;
import com.crema.creamaspring.repositories.QuoteRepository;
import com.crema.creamaspring.scraper.QuoteScraper;
import com.crema.creamaspring.scraper.TitleScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class QueryService {

    @Autowired
    QuoteRepository quoteRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ForumThreadRepository forumThreadRepository;

    public List<Quote> allQuotes() {
        List<Quote> quotes = (List<Quote>)quoteRepository.findAll();
        return quotes;
    }

    public Quote findQuote(String text) {
        List<Quote> quotes = quoteRepository.findQuotesByTextContaining(text);
        int randomNum = ThreadLocalRandom.current().nextInt(0, quotes.size());

        return quotes.get(randomNum);
    }

    public Quote addQuote (Quote quote) {
        quoteRepository.save(quote);
        return quote;
    }

    public void scrapeAndPersistQuotes() {
        QuoteScraper quoteScraper = new QuoteScraper();
        List<ForumThread> listOForumThreads = forumThreadRepository.findAll();

        for (ForumThread forumThread : listOForumThreads) {
            quoteRepository.saveAll(quoteScraper.retrieveData(forumThread,this.postRepository));
        }
    }

    public void addScrapedTitles() {
        TitleScraper titleScraper = new TitleScraper();
        forumThreadRepository.saveAll(titleScraper.retrieveData());
    }

    public List<ForumThread> allForumThreads() {
        List<ForumThread> forumThreads = forumThreadRepository.findAll();
        return forumThreads;
    }

    public List<Post> allPosts() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }
}

