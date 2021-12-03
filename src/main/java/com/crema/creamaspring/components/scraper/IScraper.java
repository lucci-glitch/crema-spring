package com.crema.creamaspring.components.scraper;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

public interface IScraper<T, S> {

    List<T> retrieveData(S source);

    Document getWebPage(String url);

    Elements getWebpageElements(Document webPage);

    //TODO: kanske en generic parseElements()



}
