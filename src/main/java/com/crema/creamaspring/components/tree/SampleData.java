package com.crema.creamaspring.components.tree;

public class SampleData {
    public static TreeNode<String> getSet1() {
        TreeNode<String> root = new TreeNode<String>("Har du slagit dig?");
        {
            TreeNode<String> node0 = root.addChild("Blöder du?");
            {
                TreeNode<String> node01 = node0.addChild(null);
                TreeNode<String> node02 = node0.addChild("Svullet?");
                {
                    TreeNode<String> node021 = node02.addChild(null);
                    TreeNode<String> node022 = node02.addChild(null);
                }
            }
            TreeNode<String> node1 = root.addChild("Feber?");
            {
                TreeNode<String> node11 = node1.addChild("Yrsel?");
                {
                    TreeNode<String> node111 = node1.addChild("Kräkningar?");
                    TreeNode<String> node112 = node1.addChild(null);
                }
                TreeNode<String> node12 = node1.addChild(null);
            }
        }

        return root;
    }

    public static BinaryTree getSet2() {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);

        return tree;
    }
}
