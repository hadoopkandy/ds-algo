package com.kandy.algorithm.week03;

import com.kandy.algorithm.leetcode.TreeNode;

import java.util.*;

/**
 * 144. 二叉树的前序遍历
 *
 * 二叉树的前序遍历：根->左子树->右子树
 * 迭代方式：根 右 左 顺序入栈
 */
public class L01_C144二叉树的前序遍历 {

    //方法一 递归
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    public void preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);  // 注意这一句
        preorder(root.left, res);
        preorder(root.right, res);
    }

    //方法二：迭代
    //前序遍历顺序：根-左-右，入栈顺序：根-右-左
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            result.add(node.val);
            //为什么是先加入右孩子，再加入左孩子呢？因为这样出栈的时候，才是中左右的顺序
            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5,new TreeNode(4,new TreeNode(1),new TreeNode(2)),new TreeNode(6));
        L01_C144二叉树的前序遍历 sol = new L01_C144二叉树的前序遍历();
        System.out.println(sol.preorderTraversal2(root));
    }
}
