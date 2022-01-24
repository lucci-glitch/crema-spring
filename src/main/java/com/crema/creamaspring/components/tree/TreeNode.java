package com.crema.creamaspring.components.tree;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Data
public class TreeNode<T> {
    private String name;
    public T data;
    public TreeNode<T> parent;
    public List<TreeNode<T>> children;

    public TreeNode(String name, T data) {
        this.name = name;
        this.data = data;
        this.children = new LinkedList<TreeNode<T>>();
        this.elementsIndex = new LinkedList<TreeNode<T>>();
        this.elementsIndex.add(this);
    }

    /** Check if the TreeNode is the root of the tree by checking if it has a parent.
     *
     * @return True if the Tree node is root node else False.
     */

    public boolean isRoot() {
        return parent == null;
    }

    /** Check if the TreeNode is the leaf of the tree by checking if it has any childs.
     *
     * @return True if the Tree node is leaf node else False.
     */

    public boolean isLeaf() {
        return children.size() == 0;
    }

    private List<TreeNode<T>> elementsIndex;

    /** Adds a child TreeNode to a parent ThreeNode.
     *
     * @param name Takes a String as the name for the TreeNode.
     * @param data Takes a generic Type as the data for the node.
     * @return a TreeNode as a child to a parent Node.
     */
    public TreeNode<T> addChild(String name, T data) {
        TreeNode<T> childNode = new TreeNode<T>(name, data);
        childNode.parent = this;
        this.children.add(childNode);
        // this.registerChildForSearch(childNode);
        return childNode;
    }

    @Override
    public String toString() {
        return data != null ? data.toString() : "[data null]";
    }

    /*
    @Override
    public Iterator<TreeNode<T>> iterator() {
        TreeNodeIter<T> iter = new TreeNodeIter<T>(this);
        return iter;
    }
     */
}
