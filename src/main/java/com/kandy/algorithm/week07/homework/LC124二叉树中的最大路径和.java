package com.kandy.algorithm.week07.homework;

import com.kandy.algorithm.leetcode.TreeNode;

public class LC124二叉树中的最大路径和 {
    //本地节点val的取值范围[-1000,1000]，所以max时可初始化成-1001
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
        //比较路径：左中、右中、当前节点
        int max = Math.max(Math.max(l + root.val, r + root.val), root.val);
        //比较路径：左中右
        MAX = Math.max(MAX, Math.max(l + root.val + r, max));
        return max;
    }
}
