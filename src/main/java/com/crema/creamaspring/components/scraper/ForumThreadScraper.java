package com.crema.creamaspring.components.scraper;

import com.crema.creamaspring.models.ForumThread;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ForumThreadScraper implements IScraper<ForumThread, String> {
    //TODO: abstrakt klass med lista som attribute
    List<ForumThread> forumThreads = new ArrayList<>();

    /** Scrapes forum threads from flashback.org based on forum collection url.
     *
     * @param source Takes a forum collection url ex. f97 as a parameter.
     * @return a list of forum threads.
     */
    @Override
    public List<ForumThread> retrieveData(String source) {
        Document document = getWebPage("https://www.flashback.org/" + source);
        Elements elements = getWebpageElements(document);
        parseElements(elements);
        return forumThreads;
    }

    /** Connects to a webpage based on String (URL).
     *
     * @param url Takes a String (Url) as a parameter.
     * @return returns a HTML Document.
     */

    @Override
    public Document getWebPage(String url) {
        try {
            return Jsoup
                    .connect(url)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Finds node elements by class name "td_title" in a HTML Document (Webpage)
     *
     * @param webPage Takes a Document (Webpage) as a parameter.
     * @return returns List of Elements.
     */

    @Override
    public Elements getWebpageElements(Document webPage) {
        //return webPage.getElementsByClass("td_title").select("[id^=thread_title_]");
        return webPage.getElementsByClass("td_title");
    }

    /** Parses the list of elements to a ForumThread.
     *
     * @param postElements Takes a list of elements as the first parameter.
     */
    public void parseElements(Elements postElements) {
        for (var element : postElements) {

            String id = element
                    .select("[id^=thread_title_]")
                    .attr("id")
                    .replaceAll("[^\\d.]", ""); //removes non numerical

            String text = element
                    .select("[id^=thread_title_]")
                    .text();

            String lastPageText = element
                    .getElementsByClass("thread-pagenav-lastpage hidden-xs")
                    .text()
                    .replaceAll("[()]","");

            if (lastPageText.isEmpty()) {
                lastPageText = "1";
            }

            int lastPage = Integer.parseInt(lastPageText);

            forumThreads.add(new ForumThread(id, text, lastPage));
        }
    }

}
