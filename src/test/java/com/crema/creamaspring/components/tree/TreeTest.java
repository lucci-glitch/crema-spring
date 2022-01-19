package com.crema.creamaspring.components.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {
    @Test
    public void test() {
        TreeNode<String> treeRoot = SampleData.getSet1();

        Tree tree = new Tree(treeRoot);

        String answear = "Nej";

        if(answear.equalsIgnoreCase("ja")) {
            tree.setCurrentNode(treeRoot.getChildren().get(0));
        }

        else{
            tree.setCurrentNode(treeRoot.getChildren().get(1));
        }

        System.out.println(tree.getCurrentNode().toString());
    }
}