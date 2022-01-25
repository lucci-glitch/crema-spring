package com.crema.creamaspring.components.tree;

import java.util.HashMap;
import java.util.Map;

public class SampleData {

    public static TreeNode<String> buildTree() {
        TreeNode<String> root = new TreeNode<String>("root","Har du slagit dig?");
        {
            TreeNode<String> node0 = root.addChild("node0", "Blöder du?");
            {
                TreeNode<String> node00 = node0.addChild("node00",null);
                TreeNode<String> node01 = node0.addChild("node01","Är det svullet?");
                {
                    TreeNode<String> node010 = node01.addChild("node010", null);
                    TreeNode<String> node011 = node01.addChild("node011", null);
                }
            }
            TreeNode<String> node1 = root.addChild("node1", "Har du feber?");
            {
                TreeNode<String> node10 = node1.addChild("node10","Känner du yrsel?");
                {
                    TreeNode<String> node100 = node10.addChild("node100", "Kräkningar?");
                    {
                        TreeNode<String> node1000 = node100.addChild("node1000", null);
                        TreeNode<String> node1001 = node100.addChild("node1001", null);
                    }
                    TreeNode<String> node101 = node10.addChild("node101",null);
                }
                TreeNode<String> node11 = node1.addChild("node11", null);
            }
        }

        return root;
    }

    public static Map<String, String> createTreeMap() {
        Map<String, String> map = new HashMap<>();

        // om det andra argumentet vore en lista, vad händer då senare
        //testar
        map.put("node0", "slag");
        map.put("node1", "slag");
        map.put("node00", "blod");
        map.put("node01", "blod");
        map.put("node10", "feber");
        map.put("node11", "feber");
        map.put("node010", "svullet");
        map.put("node011", "svullet");
        map.put("node100", "yrsel");
        map.put("node101", "yrsel");
        map.put("node1000", "kräkningar");
        map.put("node1001", "kräkningar");

        return map;
    }

}
