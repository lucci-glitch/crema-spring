package com.crema.creamaspring;

import com.crema.creamaspring.models.EQouteCategory;
import com.crema.creamaspring.models.ForumThread;
import com.crema.creamaspring.models.Post;
import com.crema.creamaspring.models.Quote;
import com.crema.creamaspring.repositories.ForumThreadRepository;
import com.crema.creamaspring.repositories.PostRepository;
import com.crema.creamaspring.repositories.QuoteRepository;
import com.crema.creamaspring.services.ChatService;
import com.crema.creamaspring.services.QuoteNotFoundException;
import com.crema.creamaspring.services.QuoteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.shaded.com.google.common.base.Verify;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//NONE will only create spring beans and not mock the servlet environment.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DbIntegrationTest {

    @Autowired
    private ForumThreadRepository forumThreadRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private ChatService chatService;

    @Autowired
    private QuoteService quoteService;


    //Starting a container with specified image
    private static  MySQLContainer container = (MySQLContainer) new MySQLContainer("mysql:8.0.26")
            .withInitScript("db_crema.sql")
            .withReuse(true);


    @BeforeAll
    public static void setUp(){
        container.start();
    }

    //lets us override the database Property(S) in application.properties
    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url",container::getJdbcUrl);
        registry.add("spring.datasource.username",container::getUsername);
        registry.add("spring.datasource.password",container::getPassword);
    }

    @Test
    void checkInitForumThreadRepository(){

        List<ForumThread> testList = forumThreadRepository.findAll();
        for (ForumThread thread:testList) {
            System.out.println(thread.toString());
        }
    }

    @Test
    @Transactional
    void checkInitPostRepository(){

        List<Post> testList = postRepository.findAll();
        for (Post post:testList) {
            System.out.println(post);
        }
    }

    @Test
    void checkInitQuoteRepository(){

        List<Quote> testList = quoteRepository.findAll();
        for (Quote qoute:testList) {
            System.out.println(qoute.toString());
        }
    }

    @Test
    void testResult1() throws QuoteNotFoundException {

        Map<String, Boolean> inData = new HashMap<>();
        inData.put("ben",true);
        inData.put("blöder",true);
        inData.put("svullen",true);

//
        System.out.println("getMatchingQuote:");
        System.out.println(quoteService.getMatchingQuote(EQouteCategory.STATEMENT, "huvud"));

        System.out.println("native query:");
        List<Quote> finalQuotes = quoteService.getRelevantQuotes(inData.keySet().toArray()[0].toString(),
                                                          inData.keySet().toArray()[1].toString(),
                                                          inData.keySet().toArray()[2].toString());
        StringBuilder sb = new StringBuilder();

        for (Quote q : finalQuotes) {
            sb.append(q.getText());
        }

        String finalPost = sb.toString();

        System.out.println(finalPost);


//        inData.keySet().toArray()[0].toString(), inData.keySet().toArray()[1].toString(), inData.keySet().toArray()[2].toString()
    }


    /*@Test
    @Transactional
    void checkForEmptyDB(){
        List<ForumThread> threads = forumThreadRepository.findAll();
        Assertions.assertEquals(0,threads.size());
    }

    @Test
    void addPostToDataBaseCheck(){
        String id = "somId";
        String text ="sometext";
        forumThreadRepository.save(new ForumThread(id,text));
        Assertions.assertNotNull(forumThreadRepository.findAll());

    }*/
}
