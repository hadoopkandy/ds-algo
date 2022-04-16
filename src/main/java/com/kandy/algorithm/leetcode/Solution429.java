package com.kandy.algorithm.leetcode;

import java.util.*;

/**
 * 429.N叉树的层序遍历
 */
public class Solution429 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> list = new ArrayList<>();
        Queue<Node> que = new LinkedList<>();

        if (root == null) {
            return list;
        }

        que.offer(root);
        while (!que.isEmpty()) {
            int levelSize = que.size();
            List<Integer> levelList = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                Node poll = que.poll();

                levelList.add(poll.val);

                List<Node> children = poll.children;
                if (children == null || children.size() == 0) {
                    continue;
                }
                for (Node child : children) {
                    if (child != null) {
                        que.offer(child);
                    }
                }
            }
            list.add(levelList);
        }

        return list;
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    public static void main(String[] args) {
        Node node3 = new Node(3, Arrays.asList(new Node(5), new Node(6)));
        Node root = new Node(1, Arrays.asList(node3, new Node(2), new Node(4)));
        Solution429 sol = new Solution429();
        System.out.println(sol.levelOrder(root));
    }
}
