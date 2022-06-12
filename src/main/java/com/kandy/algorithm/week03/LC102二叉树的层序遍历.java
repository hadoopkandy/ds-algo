package com.kandy.algorithm.week03;

import com.kandy.algorithm.leetcode.TreeNode;

import java.util.*;

/**
 * 102.二叉树的层序遍历
 */
public class LC102二叉树的层序遍历 {
    public List<List<Integer>> resList = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        //checkFun01(root,0);
        checkFun02(root);
        return resList;
    }

    //DFS--递归方式
    public void checkFun01(TreeNode node, Integer deep) {
        if (node == null) return;

        if (deep >= resList.size()) {
            //当层级增加时，list的Item也增加，利用list的索引值进行层级界定
            resList.add(new ArrayList<>());
        }
        resList.get(deep).add(node.val);

        checkFun01(node.left, deep + 1);
        checkFun01(node.right, deep + 1);
    }

    //BFS--迭代方式--借助队列
    public void checkFun02(TreeNode node) {
        if (node == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(node);

        // 从上到下遍历二叉树的每一层
        while (!q.isEmpty()) {

            //这里一定要使用固定大小size，不要使用que.size()，因为que.size是不断变化的
            int len = q.size();

            List<Integer> itemList = new ArrayList<>();

            //从左到右遍历每一层的每个节点
            for (int i = 0; i < len; i++) {
                TreeNode tmpNode = q.poll();
                itemList.add(tmpNode.val);

                //将下一层节点放入队列
                if (tmpNode.left != null) q.offer(tmpNode.left);
                if (tmpNode.right != null) q.offer(tmpNode.right);
            }
            resList.add(itemList);
        }
    }

    public static void main(String[] args) {
        LC102二叉树的层序遍历 sol = new LC102二叉树的层序遍历();
        TreeNode node4 = new TreeNode(4, new TreeNode(1), new TreeNode(3));
        TreeNode node7 = new TreeNode(7, new TreeNode(5), new TreeNode(8));
        TreeNode root = new TreeNode(6, node4, node7);
        System.out.println(sol.levelOrder(root));
    }
}
