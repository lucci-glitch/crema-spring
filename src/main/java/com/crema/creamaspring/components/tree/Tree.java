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
    private List<String> journal;
    private Map<String, String> map;

    public Tree() {
        this.currentNode = SampleData.buildTree();
        this.journal = new ArrayList<>();
        this.map = SampleData.createTreeMap();
    }

    public void proceed(String answer) {

        if(answer.equalsIgnoreCase("ja")) {
            this.currentNode = this.currentNode.getChildren().get(0);
            addMappingToJournal();
        }
        else {
            this.currentNode = this.currentNode.getChildren().get(1);
        }
    }


    public void addMappingToJournal() {
        if (map.containsKey(this.currentNode.getName())) {
            String value = map.get(this.currentNode.getName());
            if (value != null) {
                addToJournal(value);
            }
        }
    }

    public void addToJournal(String value) {
        journal.add(value);
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
