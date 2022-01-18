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
    List<Post> forumPosts = new ArrayList<>();

    @Override
    public List<Post> retrieveData(ForumThread forumThread) {
        List<Post> pageableForumPosts = new ArrayList<>();
        int pages = forumThread.getLastPage();

        if (pages > MAXPAGES) {
            pages = MAXPAGES;
        }

        for (int i = 1; i <= pages; i++) {
            long start = System.currentTimeMillis();
            String url = BASEURL + forumThread.getId();

            if (i >= 2) {
                url = url + "p" + i;
            }

            Document document = getWebPage(url);
            Elements postElements = getWebpageElements(document);
            parseElements(postElements, forumThread);
            pageableForumPosts.addAll(forumPosts);
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
        forumPosts.clear();
        return pageableForumPosts;
    }


    @Override
    public Document getWebPage(String url) {
        try {
            return Jsoup
                    .connect(url).get();
        } catch (HttpStatusException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Elements getWebpageElements(Document webPage) {
        return webPage.getElementsByClass("post_message");
    }


    public void parseElements(Elements postElements, ForumThread forumThread) {
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
            forumPosts.add(new Post(postId, forumThread, quotes));
        }
    }

    public EQouteCategory questionOrStatement(String quote) {
        if (quote.contains("?")) {
            return EQouteCategory.QUESTION;
        } else {
            return EQouteCategory.STATEMENT;
        }
    }
}