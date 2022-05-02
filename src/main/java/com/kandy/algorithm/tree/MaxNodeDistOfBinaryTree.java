package com.kandy.algorithm.tree;

/**
 * 求二叉树种节点的最大距离
 * 计算一个二叉树的最大距离有两个情况:
 * 情况A: 路径经过左子树的最深节点，通过根节点，再到右子树的最深节点。
 * 情况B: 路径不穿过根节点，而是左子树或右子树的最大距离路径，取其大者。
 * 只需要计算这两个情况的路径距离，并取其大者，就是该二叉树的最大距离
 */
public class MaxNodeDistOfBinaryTree {
    int nMaxLen = 0;

    // 寻找树中最长的两段距离
    void FindMaxLen(Node pRoot) {
        // 遍历到叶子节点，返回
        if (pRoot == null) {
            return;
        }

        // 如果左子树为空，那么该节点的左边最长距离为0
        if (pRoot.pLeft == null) {
            pRoot.nMaxLeft = 0;
        }

        // 如果右子树为空，那么该节点的右边最长距离为0
        if (pRoot.pRight == null) {
            pRoot.nMaxRight = 0;
        }

        // 如果左子树不为空，递归寻找左子树最长距离
        if (pRoot.pLeft != null) {
            FindMaxLen(pRoot.pLeft);
        }

        // 如果右子树不为空，递归寻找右子树最长距离
        if (pRoot.pRight != null) {
            FindMaxLen(pRoot.pRight);
        }

        // 计算左子树最长节点距离
        if (pRoot.pLeft != null) {
            int nTempMax = 0;
            if (pRoot.pLeft.nMaxLeft > pRoot.pLeft.nMaxRight) {
                nTempMax = pRoot.pLeft.nMaxLeft;
            } else {
                nTempMax = pRoot.pLeft.nMaxRight;
            }
            pRoot.nMaxLeft = nTempMax + 1;
        }

        // 计算右子树最长节点距离
        if (pRoot.pRight != null) {
            int nTempMax = 0;
            if (pRoot.pRight.nMaxLeft > pRoot.pRight.nMaxRight) {
                nTempMax = pRoot.pRight.nMaxLeft;
            } else {
                nTempMax = pRoot.pRight.nMaxRight;
            }
            pRoot.nMaxRight = nTempMax + 1;
        }

        // 更新最长距离
        if (pRoot.nMaxLeft + pRoot.nMaxRight > nMaxLen) {
            nMaxLen = pRoot.nMaxLeft + pRoot.nMaxRight;
        }
    }

    //数据结构定义
    class Node {
        Node pLeft;//左子树
        Node pRight;//右子树
        int nMaxLeft;//左子树中的最长距离
        int nMaxRight;//右子树种最长的距离
        char chValue;
    }
}


