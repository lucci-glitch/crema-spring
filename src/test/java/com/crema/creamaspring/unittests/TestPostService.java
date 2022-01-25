package com.crema.creamaspring.unittests;

import com.crema.creamaspring.models.ForumThread;
import com.crema.creamaspring.models.Post;
import com.crema.creamaspring.models.Quote;
import com.crema.creamaspring.repositories.PostRepository;
import com.crema.creamaspring.services.PostService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class TestPostService {
    /* @InjectMocks also creates the mock implementation,
        additionally injects the dependent mocks that are
        marked with the annotations @Mock into it.*/
    @InjectMocks
    PostService postService;
    //@Mock annotation creates a mock implementation for the class it is annotated with
    @Mock
    PostRepository postRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);


    }

    @Test
    public void getAllPosts() {

        List<Post> postList = new ArrayList<>();
        List<Quote> quoteList = new ArrayList<>();

        quoteList.add(new Quote("someid1", "statement1"));
        quoteList.add(new Quote("someid2", "statement2"));

        postList.add(new Post("id1", new ForumThread("id1", "sample text1"), quoteList));
        postList.add(new Post("id2", new ForumThread("id2", "sample text2"), quoteList));
        postList.add(new Post("id3", new ForumThread("id3", "sample text3"), quoteList));

        //mocking the behavior of the MOCK
        when(postRepository.findAll()).thenReturn(postList);

        //the actual test
        List<Post> serviceListOfPosts = postService.getAll();


        //classic assertEquals
        assertEquals(3, serviceListOfPosts.size());

        //Verify numbers of interaction with the mocK
        verify(postRepository, times(1)).findAll();
    }

    @Test
    public void savePostTest() {

        List<Quote> quoteList = new ArrayList<>();
        quoteList.add(new Quote("someid1", "statement1"));

        Post mockPost = new Post("id1", new ForumThread("id1", "sample text1"), quoteList);

        postService.add(mockPost);

        verify(postRepository, times(1)).save(mockPost);


    }


}
