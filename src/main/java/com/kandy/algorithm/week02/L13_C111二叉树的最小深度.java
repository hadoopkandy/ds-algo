package com.kandy.algorithm.week02;

import com.kandy.algorithm.leetcode.TreeNode;

/**
 * https://leetcode.cn/problems/minimum-depth-of-binary-tree/description/
 * 111. 二叉树的最小深度
 */
public class L13_C111二叉树的最小深度 {
    public int minDepth(TreeNode root) {
        // 不同于maxDepth的另一种写法
        // 也可以采用maxDepth的写法
        recur(root);
        return ans;
    }

    void recur(TreeNode node) {
        if (node == null) {
            ans = Math.min(ans, depth);
            return;
        }
        if (node.left == null && node.right == null) {
            ans = Math.min(ans, depth + 1);
            return;
        }
        depth++;
        if (node.left != null) recur(node.left);
        if (node.right != null) recur(node.right);
        depth--;
    }

    int depth = 0;
    int ans = 1 << 30;
}
