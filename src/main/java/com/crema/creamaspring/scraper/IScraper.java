package com.crema.creamaspring.scraper;
import com.crema.creamaspring.models.ForumThread;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.util.List;

public interface IScraper<T> {
    List<T> retrieveData(ForumThread forumThread);

    Document getWebPage(String url);

    Elements getWebpageElements(Document webPage);

    void parseElements(Elements postElements, ForumThread forumThread);



}
