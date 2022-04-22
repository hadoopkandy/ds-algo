package com.kandy.algorithm.week02;

import com.kandy.algorithm.leetcode.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 98. 验证二叉搜索树
 */
public class LC98验证二叉搜索树 {
    LinkedList<TreeNode> stack = new LinkedList<>();
    LinkedList<Integer> lowers = new LinkedList<>();
    LinkedList<Integer> uppers = new LinkedList<>();

    public void update(TreeNode root,Integer lower, Integer upper){
        stack.add(root);
        lowers.add(lower);
        uppers.add(upper);
    }
    public boolean isValidBST3(TreeNode root){
        Integer lower =null,upper =null,val;
        update(root,lower,upper);
        while(!stack.isEmpty()){
            root = stack.poll();
            lower = lowers.poll();
            upper = uppers.poll();

            if(root == null) continue;
            val = root.val;
            if(lower !=null && val <= lower) return false;
            if(upper !=null  && val >= upper) return false;
            update(root.right,val,upper);
            update(root.left,lower,val);
        }
        return true;
    }

    /**
     * 中序遍历
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root,Long.MIN_VALUE, Long.MAX_VALUE);
    }

    //递归
    public boolean isValidBST(TreeNode node,Long lower ,Long upper){
        //空节点是合理的二叉搜索树
        if(node ==null){
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left,lower,(long)node.val) && isValidBST(node.right,(long)node.val,upper);
    }


}
