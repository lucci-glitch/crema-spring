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

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return children.size() == 0;
    }

    private List<TreeNode<T>> elementsIndex;


    public TreeNode<T> addChild(String name, T child) {
        TreeNode<T> childNode = new TreeNode<T>(name, child);
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
