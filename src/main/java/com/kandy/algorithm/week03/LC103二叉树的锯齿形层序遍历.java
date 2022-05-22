package com.kandy.algorithm.week03;

import com.kandy.algorithm.leetcode.TreeNode;

import java.util.*;

public class LC103二叉树的锯齿形层序遍历 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean isOrderLeft = true; //第一层 从左往右

        while (!q.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<>();//双端队列是一个可以在队列任意一端插入元素的队列
            int size = q.size();//获得当前队列的长度size
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = q.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val); //从左往右 插入队列末尾
                } else {
                    levelList.offerFirst(curNode.val); //从右往左 插入队列头部
                }
                if (curNode.left != null) {
                    q.offer(curNode.left);
                }
                if (curNode.right != null) {
                    q.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<>(levelList));
            isOrderLeft = !isOrderLeft; //boolean值反转，层数是奇数时true 偶数 false
        }
        return ans;
    }

}
