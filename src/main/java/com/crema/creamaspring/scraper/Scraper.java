package com.crema.creamaspring.scraper;

import com.crema.creamaspring.models.Quote;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scraper {

    public List<Quote> retrieveData() {
        List<Quote> quotes = new ArrayList<>();

        try {
            var webpage = Jsoup
                    .connect("https://www.flashback.org/t3369257")
                    .get();

            var message = webpage.getElementsByClass("post_message");

            System.out.println(message);
        }

        catch (HttpStatusException e) {

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return quotes;
    }
}
