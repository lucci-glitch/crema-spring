package com.crema.creamaspring.components.tree;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class TreeNode<T> {
    public T data;
    public TreeNode<T> parent;
    public List<TreeNode<T>> children;
    private String name;
    private List<TreeNode<T>> elementsIndex;

    public TreeNode(String name, T data) {
        this.name = name;
        this.data = data;
        this.children = new LinkedList<>();
    }

    /**
     * Check if the TreeNode is the root of the tree by checking if it has a parent.
     *
     * @return True if the Tree node is root node else False.
     */
    public boolean isRoot() {
        return parent == null;
    }

    /**
     * Check if the TreeNode is the leaf of the tree by checking if it has any childs.
     *
     * @return True if the Tree node is leaf node else False.
     */
    public boolean isLeaf() {
        return children.size() == 0;
    }

    /**
     * Adds a child TreeNode to a parent ThreeNode.
     *
     * @param name Takes a String as the name for the TreeNode.
     * @param data Takes a generic Type as the data for the node.
     * @return a TreeNode as a child to a parent Node.
     */
    public TreeNode<T> addChild(String name, T data) {
        TreeNode<T> childNode = new TreeNode<>(name, data);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }

    @Override
    public String toString() {
        return data != null ? data.toString() : "[data null]";
    }
}
