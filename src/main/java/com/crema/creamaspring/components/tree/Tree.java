package com.crema.creamaspring.components.tree;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Tree {
    private TreeNode<String> currentNode;
    // private List<String> journal;
    private Map<String, Boolean> journal;
    private Map<String, String> map;

    public Tree() {
        this.currentNode = SampleData.buildTree();
        this.journal = new HashMap<>();
        this.map = SampleData.createTreeMap();
    }

    // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
    public void proceed(String answer) {

        if(answer.equalsIgnoreCase("ja")) {
            this.currentNode = this.currentNode.getChildren().get(0);
            addMappingToJournal(true);
        }
        else {
            this.currentNode = this.currentNode.getChildren().get(1);
            addMappingToJournal(false);
        }
    }


    public void addMappingToJournal(Boolean value) {
        if (map.containsKey(this.currentNode.getName())) {
            String key = map.get(this.currentNode.getName());
            if (key != null) {
                addToJournal(key, value);
            }
        }
    }

    public void addToJournal(String key, Boolean value) {
        journal.put(key, value);
    }

    public boolean checkIfNull() {
        if(this.currentNode.isLeaf()) {
            System.out.println("Is on leaf");
            System.out.println("Sending list: ");
            return true;
        }
        System.out.println("Return to client:");
        System.out.println(this.currentNode.toString());
        return false;
    }

}
