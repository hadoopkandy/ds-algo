package com.kandy.algorithm.week03;

import com.kandy.algorithm.leetcode.TreeNode;

public class L08_C105从前序与中序遍历序列构造二叉树 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        return build(0, preorder.length - 1, 0, inorder.length - 1);
    }
    // 用 preorder[l1..r1] 和 inorder[l2..r2] 还原二叉树
    TreeNode build(int l1, int r1, int l2, int r2) {
        if (l1 > r1) return null;
        TreeNode root = new TreeNode(preorder[l1]);
        int mid = l2; // mid是root在inorder中的位置,preorder和inorder均无重复元素
        while (inorder[mid] != root.val) mid++;
        //[l1,r1] 前序遍历区间被划分成 根l1、左子树 [l1+1,l1 + (mid - l2)]、右子树 [l1 + (mid - l2) + 1, r1]
        //[l2,r2] 中序遍历区间被划分成 左子树 [l2,mid-1]、根mid、右子树 [mid+1,r2]
        //l1 + (mid - l2) = l1 + (mid-1 -l2 + 1)
        root.left = build(l1 + 1, l1 + (mid - l2), l2, mid - 1);
        root.right = build(l1 + (mid - l2) + 1, r1, mid + 1, r2);
        return root;
    }
    int[] preorder;
    int[] inorder;
}
