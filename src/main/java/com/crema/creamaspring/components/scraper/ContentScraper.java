package com.crema.creamaspring.components.scraper;

import com.crema.creamaspring.models.EQouteCategory;
import com.crema.creamaspring.models.ForumThread;
import com.crema.creamaspring.models.Post;
import com.crema.creamaspring.models.Quote;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ContentScraper implements IScraper<Post, ForumThread> {
    private static final int MAXPAGES = 2;
    private static final long MINTIME = 650;
    private static final String BASEURL = "https://www.flashback.org/t";
//    List<Post> forumPosts = new ArrayList<>();


    /** Scrapes qoutes and posts from a forum thread on flashback.org.
     *
     * @param forumThread Takes a forum thread as a parameter.
     * @return a list of posts.
     */

    @Override
    public List<Post> retrieveData(ForumThread forumThread) {
        List<Post> pageableForumPosts = new ArrayList<>();
        int pages = forumThread.getLastPage();

        if (pages > MAXPAGES) {
            pages = MAXPAGES;
        }

        for (int i = 1; i <= pages; i++) {
//            forumPosts.clear();
            long start = System.currentTimeMillis();
            String url = BASEURL + forumThread.getId();

            if (i >= 2) {
                url = url + "p" + i;
            }

            Document document = getWebPage(url);
            log.info("content scraper url: " + url);
            Elements postElements = getWebpageElements(document);
            pageableForumPosts.addAll(parseElements(postElements, forumThread));
            long end = System.currentTimeMillis();
            long time = end - start;
            log.info("Execution lasted: " + time + " ms");

            if (MINTIME > time) {
                long sleepTime = MINTIME - time;
                log.info("Sleep in: " + sleepTime + " ms");
                try {
                    Thread.sleep(MINTIME - time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return pageableForumPosts;
    }

    /** Connects to a webpage based on String (URL).
     *
     * @param url Takes a String (Url) as a parameter.
     * @return returns a HTML Document.
     */

    @Override
    public Document getWebPage(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (HttpStatusException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Finds node elements by class name "post_message" in a HTML Document (Webpage)
     *
     * @param webPage Takes a Document (Webpage) as a parameter.
     * @return returns List of Elements.
     */

    @Override
    public Elements getWebpageElements(Document webPage) {
        return webPage.getElementsByClass("post_message");
    }

    /** Parses the list of elements to a Post.
     *
     * @param postElements Takes a list of elements as the first parameter.
     * @param forumThread Takes a forumThread as the second.
     * @return returns a List of post after the elements been parsed.
     */

    public List<Post> parseElements(Elements postElements, ForumThread forumThread) {
        List<Post> posts = new ArrayList<>();

        for (Element element : postElements) {
            String[] sentences = element.ownText().split("(?<=[.!?])\\s*");
            List<Quote> quotes = new ArrayList<>();

            String postId = element
                    .attr("id")
                    .replaceAll("[^\\d.]", ""); //removes non numerical

            for (String sentence : sentences) {
                if (sentence.length() >= 3 && !sentence.contains("\"\" ")) {
                    quotes.add(new Quote(sentence, questionOrStatement(sentence)));
                }
            }
            posts.add(new Post(postId, forumThread, quotes));
        }
        return posts;
    }

    /** Determine if a Qoute is a question or a statement.
     *
     * @param quote Takes a String (qoute) as the paramenter.
     * @return Returns a Enum (EQouteCategory) based of the ending of the String.
     */
    public EQouteCategory questionOrStatement(String quote) {
        if (quote.contains("?")) {
            return EQouteCategory.QUESTION;
        } else {
            return EQouteCategory.STATEMENT;
        }
    }
}