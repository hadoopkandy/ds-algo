package com.kandy.algorithm.week03;

import com.kandy.algorithm.leetcode.TreeNode;

/**
 * 前驱：BST中小于val的最大结点
 * 后继：BST中大于val的最小结点
 * 求前驱和后继也是基于检索的，先检索val
 * 以后继为例：
 * 1.如果检索到了val,并且val存在右子树，则在右子树中，一直往左走到底
 * 2.否则说明没找到val或者val没有右子树，此时后继就在检索过程经过的结点中
 *
 * 前驱：
 *  左子树一路向右
 *  在检索过程经过的结点中
 *
 */
public class 二叉搜索树后继模板面试题0406 {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        return getNext(root, p.val);
    }

    private TreeNode getNext(TreeNode root, int val) {
        TreeNode curr = root;
        TreeNode ans = null;
        while (curr != null) {
            if (curr.val == val) {
                // case1：检索到val且右子树存在，右子树一路向左
                if (curr.right != null) {
                    curr = curr.right;
                    while (curr.left != null) curr = curr.left;
                    return curr;
                }
                break;
            }
            // case2：当后继存在于经过的点中（找到一个>val的最小点）
            if (val < curr.val){
                // 含义：ans=min(ans, curr.val);
                if (ans == null || ans.val > curr.val) ans = curr;
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return ans;
    }
}
