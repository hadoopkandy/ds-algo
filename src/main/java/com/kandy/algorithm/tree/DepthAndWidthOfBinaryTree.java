package com.kandy.algorithm.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 求二叉树的深度和宽度
 */
public class DepthAndWidthOfBinaryTree {
    /**
     * 获取最大深度
     * 使用递归，分别求出左子树的深度、右子树的深度，两个深度的较大值+1即可
     * @param root
     * @return
     */
    public static int getMaxDepth(TreeNode root) {
        if (root == null)
            return 0;
        else {
            int left = getMaxDepth(root.left);
            int right = getMaxDepth(root.right);
            return 1 + Math.max(left, right);
        }
    }

    /**
     * 获取最大宽度
     * 　使用队列，层次遍历二叉树。在上一层遍历完成后，下一层的所有节点已经放到队列中，此时队列中的元素个数就是下一层的宽度。以此类推，依次遍历下一层即可求出二叉树的最大宽度。
     * @param root
     * @return
     */
    public static int getMaxWidth(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        int maxWitdth = 1; // 最大宽度
        queue.add(root); // 入队

        while (true) {
            int len = queue.size(); // 当前层的节点个数
            if (len == 0)
                break;
            while (len > 0) {// 如果当前层，还有节点
                TreeNode t = queue.poll();
                len--;
                if (t.left != null)
                    queue.add(t.left); // 下一层节点入队
                if (t.right != null)
                    queue.add(t.right);// 下一层节点入队
            }
            maxWitdth = Math.max(maxWitdth, queue.size());
        }
        return maxWitdth;
    }

    class TreeNode {
        char val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(char _val) {
            this.val = _val;
        }
    }

}


