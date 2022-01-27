package com.crema.creamaspring.components.tree;

import com.crema.creamaspring.services.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    private Tree tree;
    private Map<String, String> map;

    @Autowired
    private ChatService chatService;

    public TreeTest() {
        tree = new Tree();
        map = SampleData.createTreeMap();
    }

    /*@Test
    public void testGetFinalResult() {
        tree.proceed("nej");
        tree.proceed("ja");
        tree.proceed("ja");
        System.out.println(tree.getJournal());

        switch(tree.getJournal().size()) {
            case 1:
                System.out.println("Här tilldelas sträng 1: " + tree.getJournal().get(0));
                System.out.println("Skickar till relevant quoteService-metod.");
                System.out.println("--------------------------------------");
                break;
            case 2:
                System.out.println("Här tilldelas sträng 1: " + tree.getJournal().get(0));
                System.out.println("Här tilldelas sträng 2: " + tree.getJournal().get(1));
                System.out.println("Skickar till relevant quoteService-metod.");
                System.out.println("--------------------------------------");
                break;
            case 3:
                System.out.println("String word1 = " + tree.getJournal().get(0));
                System.out.println("String word2 = " + tree.getJournal().get(1));
                System.out.println("String word3 = " + tree.getJournal().get(2));
                System.out.println("Skickar till relevant quoteService-metod.");
                System.out.println("--------------------------------------");
                break;
            default:
                break;
        }
    }

    @Test
    public void testProceedToChild() {
        tree.proceed("ja");
        tree.proceed("nej");
        System.out.println(tree.getCurrentNode().toString());

        assertFalse(tree.getCurrentNode().isRoot());
    }

    @Test
    public void testIfLeaf() {
        tree.proceed("ja");
        tree.proceed("ja");

        assertTrue(tree.getCurrentNode().isLeaf());
    }

    @Test
    public void testProcess() {
        System.out.println("Har du slagit dig?");
        // GET method kallas från frontend
        String response = "ja";

        System.out.println("Svar: ja");
        tree.proceed(response);
        tree.checkIfNull();
        System.out.println("Svar: ja");
        tree.proceed(response);

        if (tree.checkIfNull()) {
            System.out.println(tree.getJournal());
        }
    }

    @Test
    public void testCreateMap() {
        map.forEach((k,v) -> System.out.println("Key= " + k + " Value= " + v));
    }

    @Test
    public void testAddOneStringToJournal() {
        tree.proceed("ja");
        assertEquals(1, tree.getJournal().size());
    }

    @Test
    public void testAddingToJournal() {
        tree.proceed("nej");
        assertEquals(1, tree.getJournal().size());
    }

    @Test
    public void testClimbDownTreeAddToJournal() {
        tree.proceed("ja");
        assertTrue(tree.getJournal().contains("slag"));
    }

    @Test
    public void testGrowJournal() {
        List<String> expected = new ArrayList<>();
        expected.add("slag");
        expected.add("svullet");
        expected.add("skada");

        tree.proceed("ja");
        tree.proceed("nej");
        tree.proceed("ja");

        System.out.println(tree.getJournal());

        assertTrue(tree.getJournal().contains("slag"));
    }*/

}