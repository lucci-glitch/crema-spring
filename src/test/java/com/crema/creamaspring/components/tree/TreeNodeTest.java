package com.crema.creamaspring.components.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class TreeNodeTest {

    @Test
    public void testIsRoot() {
        TreeNode<String> treeRoot = SampleData.getSet1();
        assertTrue(treeRoot.isRoot());
    }

    @Test
    public void testIsNotLeaf() {
        TreeNode<String> treeRoot = SampleData.getSet1();
        assertFalse(treeRoot.isLeaf());
    }

    @Test
    public void testIsChildrenLeafs() {
        TreeNode<String> treeRoot = SampleData.getSet1();
        for ( TreeNode<String> node: treeRoot.getChildren()) {
            System.out.println(node.isLeaf());
            if(!node.getChildren().isEmpty()) {
                for (TreeNode<String> miniNode: node.getChildren()) {
                    System.out.println(miniNode.toString());
                }
            }
        }
    }
}