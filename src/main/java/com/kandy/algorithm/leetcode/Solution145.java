package com.kandy.algorithm.leetcode;

import java.util.*;

/**
 * 145. 二叉树的后序遍历
 * <p>
 * 二叉树的后序遍历：左子树->右子树->根节点
 */
public class Solution145 {

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
    // 后序遍历顺序 左-右-中 入栈顺序：中-左-右 出栈顺序：中-右-左， 最后翻转结果
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
        Collections.reverse(result); // 将结果反转之后就是左右中的顺序了
        return result;
    }

    /**
     * 迭代法统一写法
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> st = new Stack<>();
        if (root != null) st.push(root);
        while (!st.empty()) {
            TreeNode node = st.peek();
            if (node != null) {
                st.pop(); // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                st.push(node);                          // 添加中节点
                st.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。
                if (node.right!=null) st.push(node.right);  // 添加右节点（空节点不入栈）
                if (node.left!=null) st.push(node.left);    // 添加左节点（空节点不入栈）

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
        Solution145 sol = new Solution145();
        System.out.println(sol.postorderTraversal2(root));
    }
}
