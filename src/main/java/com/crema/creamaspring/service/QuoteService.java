package com.crema.creamaspring.service;

import com.crema.creamaspring.models.ForumThread;
import com.crema.creamaspring.models.Quote;
import com.crema.creamaspring.repositories.QuoteRepository;
import com.crema.creamaspring.scraper.QuoteScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QuoteService {

    final QuoteRepository quoteRepository;
    final ForumThreadService forumThreadService;
    final QuoteScraper quoteScraper;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository, ForumThreadService forumThreadService, QuoteScraper quoteScraper) {
        this.quoteRepository = quoteRepository;
        this.forumThreadService = forumThreadService;
        this.quoteScraper = quoteScraper;
    }

    public List<Quote> allQuotes() {
        return quoteRepository.findAll();
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

        List<ForumThread> listOForumThreads = forumThreadService.allForumThreads();

        for (ForumThread forumThread : listOForumThreads) {
            quoteRepository.saveAll(quoteScraper.retrieveData(forumThread));
        }
    }

}

