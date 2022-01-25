package com.crema.creamaspring.unittests;

import com.crema.creamaspring.models.ForumThread;
import com.crema.creamaspring.models.Post;
import com.crema.creamaspring.models.Quote;
import com.crema.creamaspring.repositories.PostRepository;
import com.crema.creamaspring.repositories.QuoteRepository;
import com.crema.creamaspring.services.PostService;
import com.crema.creamaspring.services.QuoteService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class TestQuoteService {

    /* @InjectMocks also creates the mock implementation,
    additionally injects the dependent mocks that are
    marked with the annotations @Mock into it.*/
    @InjectMocks
    QuoteService quoteService;
    //@Mock annotation creates a mock implementation for the class it is annotated with
   /* @Mock
    QuoteRepository quoteRepository;



    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllPosts() {

        List<Post> postList = new ArrayList<>();
        List<Quote> quoteList = new ArrayList<>();

        quoteList.add(new Quote("someid1", "statement1"));
        quoteList.add(new Quote("someid2", "statement2"));
        quoteList.add(new Quote("someid3", "statement3"));


        //mocking the behavior of the MOCK
        when(quoteRepository.findAll()).thenReturn(quoteList);

        //the actual test
        List<Quote> serviceListOfPosts = quoteService.getAll();


        //classic assertEquals
        assertEquals(3, serviceListOfPosts.size());

        //Verify numbers of interaction with the mocK
        verify(quoteRepository, times(1)).findAll();
    }

    @Test
    public void getRandomQuoteTest(){


        when(quoteRepository.findQuotesByTextContaining("testText"))
                .thenReturn((List<Quote>) Arrays.asList(new Quote("testText","TestText")));

        Quote quoteServiceTest = quoteService.getRandomMatchingQuote("testText");

        assertNotNull(quoteServiceTest);
    }*/
}

