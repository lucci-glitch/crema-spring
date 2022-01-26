package com.crema.creamaspring.services;

import com.crema.creamaspring.models.EQouteCategory;
import com.crema.creamaspring.models.Quote;
import com.crema.creamaspring.repositories.QuoteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Log4j2
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

    public Quote getMatchingQuote(EQouteCategory category, String inputNoun) throws QuoteNotFoundException {
        return getRandomQuote(category, inputNoun);
    }

    public Quote getRandomQuote(EQouteCategory category, String text) throws QuoteNotFoundException {

        //List<Quote> quotes = quoteRepository.findQuotesByTextContaining(text);
        List<Quote> quotes = quoteRepository.findQuotesByCategoryEqualsAndTextContaining(category, text);

        if (quotes.isEmpty()) {
            //Alternativt throw new QuoteNotFoundException och try/catch i getMatchingQuote()
            //return getDefaultQuote();
            throw new QuoteNotFoundException();
        } else {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, quotes.size());
            return quotes.get(randomIndex);
        }
    }

    public Quote getDefaultQuote() {
        List<Quote> defaultQuotes = new ArrayList<>();
        defaultQuotes.add(new Quote("Ursäkta?", EQouteCategory.QUESTION));
        defaultQuotes.add(new Quote("Jag förstår inte.", EQouteCategory.STATEMENT));

        int randomNum = ThreadLocalRandom.current().nextInt(0, defaultQuotes.size());
        return defaultQuotes.get(randomNum);
    }

    public Quote getContainingQuote(List<String> list) {
        int randomNum = ThreadLocalRandom.current().nextInt(0, list.size());
        String string = list.get(randomNum);
        List<Quote> quotes = quoteRepository.findQuotesByTextContaining(string);
        int randomQoute = ThreadLocalRandom.current().nextInt(0, quotes.size());
        return quotes.get(randomQoute);
    }
    public List<String> getRelevantQuotes(String searchWord1){

        List<Quote> quotes = quoteRepository.relevantQuote(searchWord1);
        List<String> post= new ArrayList<>();

        extractPost(post, quotes);

        return post;
    }

    public List<String> getRelevantQuotes(String searchWord1, String searchWord2){

        List<Quote> quotes = quoteRepository.relevantQuote(searchWord1, searchWord2);
        List<String> post= new ArrayList<>();

        extractPost(post, quotes);

        return post;
    }

    public List<String> getRelevantQuotes(String searchWord1, String searchWord2, String searchWord3){

        List<Quote> quotes = quoteRepository.relevantQuote(searchWord1, searchWord2, searchWord3);
        List<String> post= new ArrayList<>();

        extractPost(post, quotes);

        return post;
    }

    public List<String> getRelevantQuotes(String searchWord1, String searchWord2, String searchWord3, String searchWord4){

        List<Quote> quotes = quoteRepository.relevantQuote(searchWord1, searchWord2, searchWord3, searchWord4);
        List<String> post= new ArrayList<>();

        extractPost(post, quotes);

        return post;
    }

    private void extractPost(List<String> post, List<Quote> quotes) {
        for(int i = 0; i < quotes.size(); i++) {

            List<Quote> siblings = quoteRepository.findSiblingQuotesById(quotes.get(i).getId());
            StringBuilder builder = new StringBuilder();

            for (Quote qoute : siblings) {
                builder.append(qoute.getText() + " ");
            }

            post.add(builder.toString());
            post.add(getForumThreadPage(quotes.get(i).getId()));
        }
    }


    public String getForumThreadPage(int quote_id) {
        String forumthread_id = quoteRepository.findForumThread(quote_id);
        String url = "https://www.flashback.org/t" + forumthread_id;
        return url;
    }

}

