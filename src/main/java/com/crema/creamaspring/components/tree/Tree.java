package com.crema.creamaspring.components.tree;

import lombok.Data;

import java.util.ArrayList;
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

    /**
     * Advances down in proper sequence the Binary Tree based on answer.
     *
     * @param answer - a answer to be evaluate.
     */
    public void proceed(String answer) {

        if (answer.equalsIgnoreCase("ja")) {
            this.currentNode = this.currentNode.getChildren().get(0);
            addMappingToJournal();
        } else {
            this.currentNode = this.currentNode.getChildren().get(1);
        }
    }

    /**
     * Adds the current node name to the journal if the map contains its key.
     */
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

    /**
     * Checks if the current node in the Tree is leaf or still on a branch.
     *
     * @return a True if the current node is leaf else False.
     */
    public boolean checkIfNull() {
        return this.currentNode.isLeaf();
    }

}
