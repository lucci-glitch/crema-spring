package com.crema.creamaspring.services;

import com.crema.creamaspring.models.Quote;
import com.crema.creamaspring.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Quote getMatchingQuote(String inputNoun) {

            return getRandomQuote(inputNoun);


    }

    public Quote getRandomQuote(String text) {
        List<Quote> quotes = quoteRepository.findQuotesByTextContaining(text);
        int randomNum = ThreadLocalRandom.current().nextInt(0, quotes.size());
        return quotes.get(randomNum);
    }

    public Quote getDefaultQuote() {
        List<Quote> defaultQuotes = new ArrayList<>();
        defaultQuotes.add(new Quote("Ursäkta?", "question"));
        defaultQuotes.add(new Quote("Jag förstår inte.", "statement"));

        int randomNum = ThreadLocalRandom.current().nextInt(0, defaultQuotes.size());
        return defaultQuotes.get(randomNum);
    }




}

