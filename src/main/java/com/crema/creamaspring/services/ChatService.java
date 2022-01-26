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

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class ChatService {

    private final Filter filter;
    private final QuoteService quoteService;
    private Tree tree = new Tree();

    @Autowired
    public ChatService(Filter filter, QuoteService quoteService) {
        this.filter = filter;
        this.quoteService = quoteService;
    }

    public String chatQuestions(String response) {
        tree.proceed(response);

        if (tree.checkIfNull()) {
         String finalResponse = getFinalResponse();
            this.tree = new Tree();
            return finalResponse;
        }

        return tree.getCurrentNode().getData();
    }

    public void getFinalResponse() {

    }

    public String getPostsToSend() {
        //return quoteService.getContainingQuote(tree.getJournal()).getText();
        //System.out.println(tree.getJournal().toString());
        String word1;
        String word2;
        String word3;
        String word4;
        List<Quote> listOfQuotes = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        switch(tree.getJournal().size()) {
            case 2:
                word1 = tree.getJournal().get(0);
                word2 = tree.getJournal().get(1);
                listOfQuotes = quoteService.getRelevantQuotes(word1, word2);
                break;
            case 3:
                word1 = tree.getJournal().get(0);
                word2 = tree.getJournal().get(1);
                word3 = tree.getJournal().get(2);
                listOfQuotes = quoteService.getRelevantQuotes(word1, word2, word3);
                break;
            case 4:
                word1 = tree.getJournal().get(0);
                word2 = tree.getJournal().get(1);
                word3 = tree.getJournal().get(2);
                word4 = tree.getJournal().get(3);
                listOfQuotes = quoteService.getRelevantQuotes(word1, word2, word3, word4);
                break;
            default:
                break;
        }

        for (Quote quote : listOfQuotes) {
            sb.append(quote + " ");
        }

        return sb.toString();
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

    public String firstResponse(String response) {

        try {
            tree.addToJournal(filter.filterSentence(response));

        } catch (JSONException | NoSentenceException e) {
            e.printStackTrace();
        }
        return tree.getCurrentNode().getData();
    }

}
