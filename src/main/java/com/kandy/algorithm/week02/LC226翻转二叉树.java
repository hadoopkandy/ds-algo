package com.kandy.algorithm.week02;

import com.kandy.algorithm.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 226. 翻转二叉树
 */
public class LC226翻转二叉树 {
    /**
     * 递归方式遍历反转
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(root ==null){
            return null;
        }
        //递归本层逻辑
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        //重复子问题
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    /**
     * 二叉树的层次遍历
     * @param root
     * @return
     */
    public TreeNode invertTreeByQueue(TreeNode root) {
        if(root ==null){
            return null;

        }
        final Deque<TreeNode> queue = new ArrayDeque<>();
        queue.push(root);
        while (!queue.isEmpty()){
            TreeNode node =  queue.poll();

            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if(node.left !=null) {
                queue.push(node.left);
            }
            if(node.right !=null){
                queue.push(node.right);
            }
        }
        return root;
    }

}
