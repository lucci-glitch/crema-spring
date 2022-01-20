package com.crema.creamaspring.services;

import com.crema.creamaspring.components.filter.Filter;
import com.crema.creamaspring.components.filter.NoSentenceException;
import com.crema.creamaspring.components.tree.Tree;
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
    private final Tree tree;

    @Autowired
    public ChatService(Filter filter, QuoteService quoteService, Tree tree) {
        this.filter = filter;
        this.quoteService = quoteService;
        this.tree = tree;
    }

    public String chatQuestions(String response) {
        tree.proceed(response);

        if (tree.checkIfNull()) {
            return getFinalResponse();
        }

        return tree.getCurrentNode().getName();
    }

    public String getFinalResponse() {
        return "(Quote)";
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
