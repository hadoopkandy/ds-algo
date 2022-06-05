package com.kandy.algorithm.week07.homework;

import com.kandy.algorithm.leetcode.TreeNode;

public class LC124二叉树中的最大路径和 {
    private int MAX = -1001;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return MAX;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = dfs(root.left);
        int r = dfs(root.right);
        int max = Math.max(Math.max(l + root.val, r + root.val), root.val);
        MAX = Math.max(MAX, Math.max(l + root.val + r, max));
        return max;
    }
}
