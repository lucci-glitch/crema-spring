package com.crema.creamaspring.scraper;

import com.crema.creamaspring.models.ForumThread;
import com.crema.creamaspring.models.Post;
import com.crema.creamaspring.models.Quote;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuoteScraper {

    public List<Quote> retrieveData(ForumThread forumThread) {
        List<Quote> quotesFromForumThread = new ArrayList<>();

        try {
            Document webpage = Jsoup
                    .connect("https://www.flashback.org/t" + forumThread.getId())
                    .get();

            Elements postElements = webpage.getElementsByClass("post_message");

             for (Element element : postElements) {
                 String[] quotes = element.ownText().split("(?<=[.!?])\\s*");
                 String id = element
                         .attr("id")
                         .replaceAll("[^\\d.]", ""); //removes non numerical

                 Post post = new Post(id, forumThread);


                 for (String quote : quotes){

                     if(quote.length()>= 3 && !quote.contains("\"\" ")){
                        quotesFromForumThread.add(new Quote(quote, post));
                     }

                 }

             }

            System.out.println(quotesFromForumThread);
        }

        catch (HttpStatusException e) {

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return quotesFromForumThread;
    }
}
