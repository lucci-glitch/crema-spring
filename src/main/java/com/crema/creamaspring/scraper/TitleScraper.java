package com.crema.creamaspring.scraper;

import com.crema.creamaspring.models.ForumThread;
import com.crema.creamaspring.models.Quote;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TitleScraper {

    public List<ForumThread> retrieveData() {
        List<ForumThread> forumThreads = new ArrayList<>();

        try {
            Document webpage = Jsoup
                    .connect("https://www.flashback.org/f97")
                    .get();

            Elements threadTitles = webpage.getElementsByClass("td_title").select("[id^=thread_title_]");

            for (var thread: threadTitles){
                String id = thread
                        .attr("id")
                        .replaceAll("[^\\d.]", ""); //removes non numerical
                forumThreads.add(new ForumThread(id, thread.text()));
            }

        } catch (HttpStatusException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return forumThreads;
    }

}
