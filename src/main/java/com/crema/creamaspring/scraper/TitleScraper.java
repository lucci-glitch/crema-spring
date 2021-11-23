package com.crema.creamaspring.scraper;

import com.crema.creamaspring.models.Quote;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TitleScraper {

    public List<Quote> retrieveData() {
        List<Quote> quotes = new ArrayList<>();

        try {
            Document webpage = Jsoup
                    .connect("https://www.flashback.org/f97")
                    .get();

            Elements titles = webpage.getElementsByClass("td_title");

            Elements threadTitles = titles.select("[id^=thread_title_]");
            String text = threadTitles.text();
            String id = threadTitles.attr("id");
            System.out.println(threadTitles);
            System.out.println(text);
            System.out.println(id);

            /*System.out.println(titles);*/

            for (var element: titles){
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
