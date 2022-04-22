package com.kandy.algorithm.week02;

import com.kandy.algorithm.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 104. 二叉树的最大深度
 */
public class LC104二叉树的最大深度 {

    /**
     * 广度优先搜索 BFS
     * 在二叉树中，一层一层的来遍历二叉树，记录一下遍历的层数就是二叉树的深度
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }

    /**
     * DFS
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if (root == null)
            return 0;
        int max = 0;
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> levelStack = new Stack<>();
        nodeStack.push(root);
        levelStack.push(1);
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int level = levelStack.pop();
            max = Math.max(max, level);
            if (node.left != null) {
                nodeStack.push(node.left);
                levelStack.push(level + 1);
            }

            if (node.right != null) {
                nodeStack.push(node.right);
                levelStack.push(level + 1);
            }
        }
        return max;
    }

    //递归 自底向上
    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            //子问题
            int leftHeight = maxDepth3(root.left);
            int rightHeight = maxDepth3(root.right);
            //本层问题
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    //自顶向下
    public int maxDepth4(TreeNode root) {
        ans = 0;
        depth =1;
        calc(root);
        return ans;
    }
    void calc(TreeNode root){
        if(root ==null) return;
        ans = Math.max(ans,depth);
        //往下走
        depth++;
        calc(root.left);
        calc(root.right);
        //往上走
        depth--;
    }
    int depth;
    int ans;

}
