package com.kandy.algorithm.week03.homework;

import com.kandy.algorithm.leetcode.TreeNode;

public class LC106从中序与后序遍历序列构造二叉树 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        return build(0, inorder.length - 1, 0, postorder.length - 1);
    }

    // 用 inorder[l1..r1] 和 postorder[l2..r2] 还原二叉树
    TreeNode build(int l1, int r1, int l2, int r2) {
        if (l1 > r1) return null;
        TreeNode root = new TreeNode(postorder[r2]);
        int mid = l1; // mid是root在inorder中的位置,inorder和postorder均无重复元素
        while (inorder[mid] != root.val) mid++;
        //[l1,r1] 中序遍历区间被划分成 左子树 [l1,mid-1] 根mid 右子树 [mid +1, r1]
        //[l2,r2] 后序遍历区间被划分成 左子树 [l2,l2+ (mid -l1) -1] 右子树 [l2+ (mid -l1),r2-1] r2根
        //l2 + (mid - l1) -1 = l2 + (mid-1 -l1 + 1) -1
        root.left = build(l1, mid - 1, l2, l2 + (mid - l1) -1 );
        root.right = build(mid + 1, r1, l2 + (mid - l1) , r2 - 1);
        return root;
    }

    int[] inorder;
    int[] postorder;
}
