package com.crema.creamaspring.components.tree;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;


class TreeNodeTest {

   /* @Test
    public void testIsRoot() {
        TreeNode<String> treeRoot = SampleData.buildTree();
        assertTrue(treeRoot.isRoot());
    }

    @Test
    public void testIsNotLeaf() {
        TreeNode<String> treeRoot = SampleData.buildTree();
        assertFalse(treeRoot.isLeaf());
    }

    @Test
    public void testIsChildrenLeafs() {
        TreeNode<String> treeRoot = SampleData.buildTree();
        for ( TreeNode<String> node: treeRoot.getChildren()) {
            System.out.println(node.isLeaf());
            if(!node.getChildren().isEmpty()) {
                for (TreeNode<String> miniNode: node.getChildren()) {
                    System.out.println(miniNode.getClass().getName());

                }
            }
        }
    }*/
}