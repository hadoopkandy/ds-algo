package com.kandy.algorithm.week03.homework;

import com.kandy.algorithm.leetcode.TreeNode;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/convert-bst-to-greater-tree/
 */
public class LC538把二叉搜索树转换为累加树 {
    //中序遍历迭代写法，方向相反，先一路向右，再往左
    public TreeNode convertBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null; //保存逆序的前一个节点
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) { //指针来访问节点，访问到最底层
                stack.push(cur);//将访问的节点进栈
                cur = cur.right; //右
            } else {
                cur = stack.pop();
                if (pre != null) {
                    cur.val += pre.val;
                    pre = cur;
                } else {
                    pre = cur;
                }
                cur = cur.left;
            }
        }
        return root;
    }

    //递归写法
    public TreeNode convertBST2(TreeNode root) {
        if (root != null) {
            convertBST2(root.right);
            sum += root.val;
            root.val = sum;
            convertBST2(root.left);
        }
        return root;
    }

    int sum = 0;

}
