package com.kandy.algorithm.week02;

import com.kandy.algorithm.leetcode.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 236.二叉树最近公共的祖先
 */
public class LC236二叉树最近公共的祖先 {
    private TreeNode ans;

    //方法一:递归
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }

    //时间复杂度 O(n) 空间复杂度 O(n)
    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        //左孩子
        boolean lson = dfs(root.left, p, q);
        //右孩子
        boolean rson = dfs(root.right, p, q);
        //左子树、右子树分别包含p q 或者当前节点已经p q 那么只要求左子树或者右子树包含另一个节点
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        //子树是否包含 p q
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    //方法2：存储父节点
    //判断是否出现过或者涉及出现次数的问题，可以使用哈希表
    //测试用例包含：有公共祖先和没有公共祖先两种情况
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        //递归建立父节点
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }

    //通过dfs 维护 每个节点的父节点信息，存到哈希表
    public void dfs(TreeNode root) {
        //递归终止条件
        if (root == null) return;
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

    //每个节点的父节点指针
    Map<Integer, TreeNode> parent = new HashMap<>();
    Set<Integer> visited = new HashSet<>();
}
