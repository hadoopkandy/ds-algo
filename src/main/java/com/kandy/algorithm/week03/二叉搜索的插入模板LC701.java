package com.kandy.algorithm.week03;

import com.kandy.algorithm.leetcode.TreeNode;

/**
 * 插入val与检索val的过程类似：
 * 1.若检索发现存在，则放弃插入（或者把val对应节点计数+1，视要求而定）
 * 2.若检索发现不存在（子树为空），直接在对应位置新建关键码为val的结点
 */
public class 二叉搜索的插入模板LC701 {
    // 返回插入以后的新树根
    // （如果root本来就存在，还是返回root；如果root是空，返回新建的点）
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }
}
