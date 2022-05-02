package com.kandy.algorithm.week03;

import com.kandy.algorithm.leetcode.TreeNode;

import java.util.*;

/**
 * 94.二叉树的中序遍历
 * 迭代方式：一路到左 弹栈 右子树
 */
public class LC94二叉树的中序遍历 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> seq = new ArrayList<>();
        dfs(root, seq);
        return seq;
    }

    //方法一 递归
    void dfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        dfs(root.left, list);
        list.add(root.val);             // 注意这一句
        dfs(root.right, list);
    }

    // 方法二 迭代方式
    // 中序遍历顺序: 左-根-右 入栈顺序：  一路到到左 -> 弹栈 -> 右子树
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) { //指针来访问节点，访问到最底层
                stack.push(cur);//将访问的节点进栈
                cur = cur.left; //左
            } else {
                cur = stack.pop(); //从栈里弹出的数据，就是要处理的数据（放进result数组的数据）
                result.add(cur.val); //根
                cur = cur.right; //右
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5,new TreeNode(4,new TreeNode(1),new TreeNode(2)),new TreeNode(6));
        LC94二叉树的中序遍历 sol = new LC94二叉树的中序遍历();
        System.out.println(sol.inorderTraversal(root));
    }

}
