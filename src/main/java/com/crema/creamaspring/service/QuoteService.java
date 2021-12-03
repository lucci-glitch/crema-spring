package com.crema.creamaspring.service;

import com.crema.creamaspring.models.ForumThread;
import com.crema.creamaspring.models.Quote;
import com.crema.creamaspring.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QuoteService {

    final QuoteRepository quoteRepository;
    final ForumThreadService forumThreadService;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository, ForumThreadService forumThreadService) {
        this.quoteRepository = quoteRepository;
        this.forumThreadService = forumThreadService;
    }

    public List<Quote> getAll() {
        return quoteRepository.findAll();
    }

    public Quote findQuote(String text) {
        List<Quote> quotes = quoteRepository.findQuotesByTextContaining(text);
        int randomNum = ThreadLocalRandom.current().nextInt(0, quotes.size());

        return quotes.get(randomNum);
    }

    public void addQuote (Quote quote) {
        quoteRepository.save(quote);
    }

//    public void scrapeAndPersistQuotes() {
//
//        List<ForumThread> listOForumThreads = forumThreadService.getAll();
//
//        for (ForumThread forumThread : listOForumThreads) {
//            quoteRepository.saveAll(quoteScraper.retrieveData(forumThread));
//        }
//    }

}

