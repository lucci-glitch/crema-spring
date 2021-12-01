package com.crema.creamaspring.scraper;

import com.crema.creamaspring.models.ForumThread;
import com.crema.creamaspring.models.Post;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PostScraper implements IScraper<Post> {
    List<Post> forumPosts = new ArrayList<>();

    @Override
    public List<Post> retrieveData(ForumThread forumThread) {
        Document document = getWebPage("https://www.flashback.org/t" + forumThread.getId());
        Elements postElements = getWebpageElements(document);
        getTextFromElements(postElements, forumThread);
        return forumPosts;
    }

    @Override
    public Document getWebPage(String url) {
        try {
            Document webpage = Jsoup
                    .connect(url).get();
            return webpage;
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

    @Override
    public void getTextFromElements(Elements postElements, ForumThread forumThread) {
        for (Element element : postElements) {
            String postId = element
                    .attr("id")
                    .replaceAll("[^\\d.]", ""); //removes non numerical

            forumPosts.add(new Post(postId, forumThread));
        }


    }
}