package com.crema.creamaspring.services;

import com.crema.creamaspring.components.filter.Filter;
import com.crema.creamaspring.components.filter.NoSentenceException;
import com.crema.creamaspring.models.EQouteCategory;
import com.crema.creamaspring.models.Quote;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ChatService {

    private final Filter filter;
    private final QuoteService quoteService;

    @Autowired
    public ChatService(Filter filter, QuoteService quoteService) {
        this.filter = filter;
        this.quoteService = quoteService;
    }

    public Quote getChatResponse(String category, String inputMessage) {

        EQouteCategory qouteCategory = EQouteCategory.valueOf(category.toUpperCase());

        try {
            return quoteService.getMatchingQuote(qouteCategory, filter.filterSentence(inputMessage));
        } catch (JSONException | QuoteNotFoundException | NoSentenceException e) {
            e.printStackTrace();
            return quoteService.getDefaultQuote();
        }
    }

}
