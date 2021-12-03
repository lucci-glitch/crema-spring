//package com.crema.creamaspring.scraper;
//
//import com.crema.creamaspring.models.ForumThread;
//import com.crema.creamaspring.models.Post;
//import com.crema.creamaspring.models.Quote;
//import com.crema.creamaspring.repositories.PostRepository;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class QuoteScraperImplementation implements IScraper<Quote> {
//    List<Quote> quotesFromForumThread = new ArrayList<>();
//    final PostRepository postRepository;
//
//    @Autowired
//    public QuoteScraperImplementation(PostRepository postRepository) {
//        this.postRepository = postRepository;
//    }
//
//
//    @Override
//    public List<Quote> retrieveData(ForumThread forumThread) {
//
//        Document document = getWebPage("https://www.flashback.org/t3374121");
//        Elements postElements = getWebpageElements(document);
//        parseElements(postElements, forumThread);
//        return quotesFromForumThread;
//    }
//
//    @Override
//    public Document getWebPage(String url) {
//        try {
//            return Jsoup
//                    .connect(url).get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public Elements getWebpageElements(Document webPage) {
//        return webPage.getElementsByClass("post_message");
//    }
//
//    @Override
//    public void parseElements(Elements postElements, ForumThread forumThread) {
//
//        for (Element element : postElements) {
//            String[] quotes = element.ownText().split("(?<=[.!?])\\s*");
//            String id = element
//                    .attr("id")
//                    .replaceAll("[^\\d.]", ""); //removes non numerical
//
//            Post post = new Post(id, forumThread, "test");
//            postRepository.save(post);
//            for (String quote : quotes) {
//
//                if (quote.length() >= 3 && !quote.contains("\"\" ")) {
//                    quotesFromForumThread.add(new Quote(quote,questionOrStatement(quote)));
//                }
//
//            }
//
//        }
//
//    }
//
//    public String questionOrStatement(String quote) {
//        if (quote.contains("?")) {
//            return "question";
//        }
//        return "Statement";
//    }
//
//
//}
