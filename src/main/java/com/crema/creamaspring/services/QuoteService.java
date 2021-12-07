package com.crema.creamaspring.services;

import com.crema.creamaspring.models.Quote;
import com.crema.creamaspring.repositories.QuoteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QuoteService {

    final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public List<Quote> getAll() {
        return quoteRepository.findAll();
    }

    public Quote getRandomMatchingQuote(String text) {
        if(text == null) {
        }
        //TODO: validate text
        List<Quote> quotes = quoteRepository.findQuotesByTextContaining(text);
        int randomNum = ThreadLocalRandom.current().nextInt(0, quotes.size());
        //TODO: validate quote
        return quotes.get(randomNum);
    }


}

