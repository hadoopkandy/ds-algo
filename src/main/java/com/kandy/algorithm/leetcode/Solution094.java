package com.kandy.algorithm.leetcode;

import java.util.*;

/**
 * 94.二叉树的中序遍历
 */
public class Solution094 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);             // 注意这一句
        inorder(root.right, list);
    }

    // 中序遍历顺序: 左-中-右 入栈顺序： 左-右
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) { //指针来访问节点，访问到最底层
                stack.push(cur);//将访问的节点进栈
                cur = cur.left; //左
            } else {
                cur = stack.pop(); //从栈里弹出的数据，就是要处理的数据（放进result数组的数据）
                result.add(cur.val); //中
                cur = cur.right; //右
            }
        }
        return result;
    }

    /**
     * 迭代法统一写法
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> st = new Stack<>();
        if (root != null) st.push(root);
        while (!st.empty()) {
            TreeNode node = st.peek();
            if (node != null) {
                st.pop(); // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                if (node.right!=null) st.push(node.right);  // 添加右节点（空节点不入栈）
                st.push(node);                          // 添加中节点
                st.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。

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
        Solution094 sol = new Solution094();
        System.out.println(sol.inorderTraversal3(root));
    }

}
