package com.kandy.algorithm.week03;

import com.kandy.algorithm.leetcode.TreeNode;

/**
 * BST 检索过程：
 * 检索关键码val是否存在
 * 从根开始递归查找
 * 1.若当前节点的关键码等于val，则已经找到
 * 2.若关键码大于val，递归检索左子树（或不存在）
 * 3.若关键码小于val,递归检索右子树（或不存在）
 */
public class 二叉搜索的查找模板LC700 {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (val == root.val) {
            return root;
        }
        if (val < root.val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }

    public TreeNode searchBST2(TreeNode root, int val) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val == val) {
                return curr;
            } else if (val < curr.val) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return curr;
    }
}
