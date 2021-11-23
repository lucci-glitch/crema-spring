package com.crema.creamaspring.scraper;

import com.crema.creamaspring.models.Quote;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuoteScraper {

    public List<Quote> retrieveData() {
        List<Quote> quotes = new ArrayList<>();

        try {
            Document webpage = Jsoup
                    .connect("https://www.flashback.org/t3369257")
                    .get();

            Elements messages = webpage.getElementsByClass("post_message");

             for (var element: messages){
                 String[] citatisar = element.ownText().split("(?<=[.!?])\\s*");

                 for (String citat:citatisar){

                     if(citat.length()>= 3 && !citat.contains("\"\" ")){

                    quotes.add(new Quote(citat));
                     }

                 }

             }

            System.out.println(quotes);
        }

        catch (HttpStatusException e) {

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return quotes;
    }
}
