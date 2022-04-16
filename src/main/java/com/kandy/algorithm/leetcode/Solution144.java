package com.kandy.algorithm.leetcode;

import java.util.*;

/**
 * 144. 二叉树的前序遍历
 *
 * 二叉树的前序遍历：根节点->左子树->右子树
 */
public class Solution144 {

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
    //前序遍历顺序：中-左-右，入栈顺序：中-右-左
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

    /**
     * 迭代法统一写法
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> st = new Stack<>();
        if (root != null) st.push(root);
        while (!st.empty()) {
            TreeNode node = st.peek();
            if (node != null) {
                st.pop(); // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                if (node.right!=null) st.push(node.right);  // 添加右节点（空节点不入栈）
                if (node.left!=null) st.push(node.left);    // 添加左节点（空节点不入栈）
                st.push(node);                          // 添加中节点
                st.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。

            } else { // 只有遇到空节点的时候，才将下一个节点放进结果集
                st.pop();           // 将空节点弹出
                node = st.peek();    // 重新取出栈中元素
                st.pop();
                result.add(node.val); // 加入到结果集
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5,new TreeNode(4,new TreeNode(1),new TreeNode(2)),new TreeNode(6));
        Solution144 sol = new Solution144();
        System.out.println(sol.preorderTraversal2(root));
    }
}
