package com.kandy.algorithm.week03;

import com.kandy.algorithm.leetcode.TreeNode;

/**
 * 插入val与检索val的过程类似：
 * 1.若检索发现存在，则放弃插入（或者把val对应节点计数+1，视要求而定）
 * 2.若检索发现不存在（子树为空），直接在对应位置新建关键码为val的结点
 */
public class L15_C701二叉搜索的插入模板 {
    // 返回插入以后的新树根
    // （如果root本来就存在，还是返回root；如果root是空，返回新建的点）
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        // 本题中，新值和原始二叉搜索树中的任意节点值都不同。
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }

        TreeNode curr = root;
        while (curr != null) {
            if (val < curr.val) {
                if (curr.left == null) {
                    curr.left = new TreeNode(val);
                    return root;
                }
                curr = curr.left;
            } else {
                if (curr.right == null) {
                    curr.right = new TreeNode(val);
                    return root;
                }
                curr = curr.right;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        L15_C701二叉搜索的插入模板 solution = new L15_C701二叉搜索的插入模板();
        TreeNode five = new TreeNode(5);
        TreeNode two = new TreeNode(2);
        TreeNode nine = new TreeNode(9);
        TreeNode int_min = new TreeNode(Integer.MIN_VALUE);
        TreeNode six = new TreeNode(6);
        TreeNode int_max = new TreeNode(Integer.MAX_VALUE);
        TreeNode one = new TreeNode(1);
        five.left = two;
        five.right = nine;
        two.left = int_min;
        nine.left = six;
        nine.right = int_max;
        int_min.right = one;
        /*
          insertIntoBST(5,3)
          5.left = insertIntoBST(2,3) = 2 =>  5.left = 2
          2.right = insertIntoBST(2,3) = 3  return 2
          insertIntoBST(null,3)  return 3
         */
        System.out.println(solution.insertIntoBST(five, 2).val);
    }
}
