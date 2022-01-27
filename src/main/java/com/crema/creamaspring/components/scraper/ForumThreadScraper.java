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

/** Scrapes forum threads from flashback.org based on forum collection url.
 *
 */

@Slf4j
@Component
public class ForumThreadScraper implements IScraper<ForumThread, String> {
    //TODO: abstrakt klass med lista som attribute
    List<ForumThread> forumThreads = new ArrayList<>();

    /** Retrieves a list of post from a forum thread.
     *
     * @param source - a source (url) with is scraped.
     * @return - a list of forum threads.
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
     * @param url - url to be connect to.
     * @return - returns a HTML Document.
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
     * @param webPage - a HTML Document to be inspected.
     * @return - returns list of Elements.
     */

    @Override
    public Elements getWebpageElements(Document webPage) {
        //return webPage.getElementsByClass("td_title").select("[id^=thread_title_]");
        return webPage.getElementsByClass("td_title");
    }

    /** Parses the list of elements to a ForumThread.
     *
     * @param pageElements - a list of elements to be parsed.
     */
    public void parseElements(Elements pageElements) {
        for (var element : pageElements) {

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
