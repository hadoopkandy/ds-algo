package com.kandy.algorithm.week03;

/**
 * 剑指 Offer33 ⼆叉搜索树的后续遍历序列
 * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/description/
 * p从起始位置i开始遍历走到j，找到左右子树的分界点m  [i,m-1]都小于j   [m,j-1] 都大于j
 */
public class Offer33二叉搜索树的后续遍历 {
    public boolean verifyPostorder(int[] postorder) {
        //定义递归辅助函数，设定起始位置和终止位置
        return recur(postorder, 0, postorder.length - 1);
    }

    /*
      1.划分左右子树： 遍历后序遍历的 [i,j] 区间元素，寻找第一个大于根节点的节点，索引记为m。
      此时，可划分出左子树区间 [i,m−1] 、右子树区间 [m,j−1] 、根节点索引 j。
      2.判断是否为二叉搜索树
       左子树区间 [i,m−1] 内的所有节点都应 < postorder[j] 。而第 1.划分左右子树 步骤已经保证左子树区间的正确性，因此只需要判断右子树区间即可。
       右子树区间 [m,j−1] 内的所有节点都应 > postorder[j] 。实现方式为遍历，当遇到 ≤postorder[j] 的节点则跳出；则可通过 p=j 判断是否为二叉搜索树。
     */
    boolean recur(int[] postorder, int i, int j) {
        if (i >= j) return true;
        //设置p为起始位置i，找所有小于根节点j的位置m
        int p = i;
        while (postorder[p] < postorder[j]) p++;
        //找到后，这应该属于右子树的范畴，遍历看看右边全大于j
        int m = p;
        while (postorder[p] > postorder[j]) p++;
        //p=j 判断此树是否正确,满足这个特性后，再递归判断左子树[i,m-1]和右子树[m,j-1]
        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }

    public static void main(String[] args) {
        Offer33二叉搜索树的后续遍历 o = new Offer33二叉搜索树的后续遍历();
        //true
        System.out.println(o.verifyPostorder(new int[]{1, 2, 3, 4, 5}));
        //true
        System.out.println(o.verifyPostorder(new int[]{5, 4, 3, 2, 1}));
        //true
        System.out.println(o.verifyPostorder(new int[]{1, 3, 2, 4, 5}));
        //false
        System.out.println(o.verifyPostorder(new int[]{1, 2, 5, 3, 4}));
    }
}
