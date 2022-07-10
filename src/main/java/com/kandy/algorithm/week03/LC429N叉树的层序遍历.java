package com.kandy.algorithm.week03;


import com.kandy.algorithm.leetcode.Node;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 429.N叉树的层序遍历
 */
public class LC429N叉树的层序遍历 {

    //queue = [<1,0>,<3,1>,<2,1>,<4,1>,<5,2>,<6,2>]
    public List<List<Integer>> levelOrder(Node root) {
        //<节点，深度从0开始>
        Queue<Pair<Node, Integer>> q = new LinkedList<Pair<Node, Integer>>();
        List<List<Integer>> seq = new ArrayList<List<Integer>>();
        if (root == null) return seq;
        //放入根节点 <点，层数>
        q.add(new Pair<Node, Integer>(root, 0));
        while (!q.isEmpty()) {
            Node node = q.peek().getKey();
            Integer depth = q.poll().getValue();
            //depth从0开始
            if (depth >= seq.size()) seq.add(new ArrayList<Integer>());
            seq.get(depth).add(node.val);
            for (Node child : node.children) {
                q.add(new Pair<Node, Integer>(child, depth + 1));
            }
        }
        return seq;
    }
}
