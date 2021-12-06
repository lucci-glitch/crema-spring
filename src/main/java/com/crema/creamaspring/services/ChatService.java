package com.crema.creamaspring.services;

import com.crema.creamaspring.components.filter.Filter;
import com.crema.creamaspring.models.Quote;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final Filter filter;
    private final QuoteService quoteService;

    @Autowired
    public ChatService(Filter filter, QuoteService quoteService) {
        this.filter = filter;
        this.quoteService = quoteService;
    }

    public Quote getChatResponse(String inputMessage) {
        try {
            return quoteService.getMatchingQuote(filter.filterSentence(inputMessage));
        } catch (JSONException e) {
            e.printStackTrace();
            return quoteService.getDefaultQuote();
        }
    }
//TODO: Kolla att det sköts av JSONTagger genom JSONException

//    public Boolean inputIsValid(String text) {
//        if (text.length() > 2 && text.matches("[a-zA-Z]+")) {
//            return true;
//        } else
//            return false;
//    }

}
