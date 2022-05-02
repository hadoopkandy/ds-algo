package com.kandy.algorithm.week03;

import com.kandy.algorithm.leetcode.TreeNode;

/**
 * 从BST中删除关键码为val的结点，可以基于检索+求后继实现
 * 首先检索val
 * 1.如果val只有一棵树，直接删除val，把子树和父结点相连就行了
 * 2.如果有两棵子树，需要找到后继，先删除后继，再用后继结点，代替val的位置
 * （因为后继的右子树一直往左走到底，所以它最多只会有一棵子树，容易删，且不会破坏BST性质）
 */
public class 二叉搜索树删除模板LC450 {
    // 在以root为根的子树中删除key，返回新的根
    public TreeNode deleteNode(TreeNode root, int key) {
        //找不到，就不管了
        if (root == null) return null;
        //找到，就执行删除
        if (root.val == key) {
            if (root.left == null) return root.right; // 没有左子树,让right代替root的位置
            if (root.right == null) return root.left; // 没有右子树,让left代替root的位置
            TreeNode next = root.right;
            while (next.left != null) next = next.left; // 找后继：右子树一路向左
            //在右子树中删后继，后继肯定没有左孩子，直接让右孩子代替后继
            root.right = deleteNode(root.right, next.val);
            //后继的值代替root,root是待删除节点
            root.val = next.val;
            return root;
        }
        //小于，往左
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }
}
