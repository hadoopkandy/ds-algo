package com.kandy.algorithm.week02;

import com.kandy.algorithm.leetcode.TreeNode;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/validate-binary-search-tree/description/
 * 98. 验证二叉搜索树
 */
public class L10_C98验证二叉搜索树 {
    /**
     * 中序遍历
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        long inorder = Long.MIN_VALUE;

        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) { //指针来访问节点，访问到最底层
                stack.push(cur);//将访问的节点进栈
                cur = cur.left; //左
            } else {
                cur = stack.pop();
                // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
                if (cur.val <= inorder) {
                    return false;
                }
                inorder = cur.val;
                cur = cur.right;
            }
        }
        return true;
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root,Long.MIN_VALUE, Long.MAX_VALUE);
    }

    //递归
    public boolean isValidBST(TreeNode node,long lower ,long upper){
        //空节点是合理的二叉搜索树
        if(node ==null){
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        //左右子树都满足BST性质才可以
        return isValidBST(node.left,lower,node.val) && isValidBST(node.right,node.val,upper);
    }


}
