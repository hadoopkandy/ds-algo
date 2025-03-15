package com.kandy.algorithm.week03;


import com.kandy.algorithm.leetcode.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class L05_C589N叉树的前序遍历 {
    public List<Integer> preorder(Node root) {
        seq = new ArrayList<>();
        if (root == null) return seq;
        dfs(root);
        return seq;
    }

    public void dfs(Node root) {
        if (root == null) return;
        seq.add(root.val);
        for (Node node : root.children) {
            dfs(node);
        }
    }

    public List<Integer> preorder2(Node root) {
        seq = new ArrayList<>();
        if (root == null) return seq;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            seq.add(node.val);
            //从右往左放栈,保证取的时候是从左往右
            for (int i = node.children.size() - 1; i >= 0; i--) {
                stack.push(node.children.get(i));
            }
        }
        return seq;
    }

    List<Integer> seq;
}
