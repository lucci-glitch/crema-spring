package com.crema.creamaspring.components.tree;

import lombok.Data;

@Data
public class Tree {
 private TreeNode<String> currentNode;

    public Tree(TreeNode<String> currentNode) {
        this.currentNode = currentNode;
    }


    /*
    1. Gå nedåt i trädet
    2. om ThreeNode är null -> skriv något
    3. forsätt med TDD
     */
}
