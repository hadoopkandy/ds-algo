package com.kandy.algorithm.week03;

import com.kandy.algorithm.leetcode.TreeNode;

import java.util.*;

/**
 * 145. 二叉树的后序遍历
 * <p>
 * 二叉树的后序遍历：左子树->右子树->根
 *
 * 迭代方式：入栈顺序：根-左-右 出栈顺序：根-右-左， 最后翻转结果
 */
public class LC145二叉树的后序遍历 {

    //方法一 递归
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    public void postorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root.val); // 注意这一句
    }

    //方法二：迭代
    // 后序遍历顺序 左-右-根 入栈顺序：根-左-右 出栈顺序：根-右-左， 最后翻转结果
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.left != null){ // 相对于前序遍历，这更改一下入栈顺序
                stack.push(node.left);
            }
            if (node.right != null){
                stack.push(node.right);
            }
        }
        Collections.reverse(result); // 将结果反转之后就是左右根的顺序了
        return result;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5,new TreeNode(4,new TreeNode(1),new TreeNode(2)),new TreeNode(6));
        LC145二叉树的后序遍历 sol = new LC145二叉树的后序遍历();
        System.out.println(sol.postorderTraversal2(root));
    }
}
