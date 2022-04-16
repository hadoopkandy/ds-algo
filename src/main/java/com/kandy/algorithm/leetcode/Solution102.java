package com.kandy.algorithm.leetcode;

import java.util.*;

/**
 * 102.二叉树的层序遍历
 */
public class Solution102 {
    public List<List<Integer>> resList = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        //checkFun01(root,0);
        checkFun02(root);
        return resList;
    }

    //DFS--递归方式
    public void checkFun01(TreeNode node, Integer deep) {
        if (node == null) return;
        deep++;

        if (resList.size() < deep) {
            //当层级增加时，list的Item也增加，利用list的索引值进行层级界定
            List<Integer> item = new ArrayList<Integer>();
            resList.add(item);
        }
        resList.get(deep - 1).add(node.val);

        checkFun01(node.left, deep);
        checkFun01(node.right, deep);
    }

    //BFS--迭代方式--借助队列
    public void checkFun02(TreeNode node) {
        if (node == null) return;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(node);

        while (!que.isEmpty()) {
            List<Integer> itemList = new ArrayList<>();
            int len = que.size();

            //这里一定要使用固定大小size，不要使用que.size()，因为que.size是不断变化的
            while (len > 0) {
                TreeNode tmpNode = que.poll();
                itemList.add(tmpNode.val);

                if (tmpNode.left != null) que.offer(tmpNode.left);
                if (tmpNode.right != null) que.offer(tmpNode.right);
                len--;
            }
            resList.add(itemList);
        }

    }
    public static void main(String[] args) {
        Solution102 sol = new Solution102();
        TreeNode node4 = new TreeNode(4,new TreeNode(1),new TreeNode(3));
        TreeNode node7 = new TreeNode(7,new TreeNode(5),new TreeNode(8));
        TreeNode root = new TreeNode(6,node4,node7);
        System.out.println(sol.levelOrder(root));
    }
}
