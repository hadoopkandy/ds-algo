package com.kandy.algorithm.week03;

import com.kandy.algorithm.leetcode.TreeNode;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 236.二叉树最近公共的祖先
 * 先求出父节点，然后用向上标记法
 * p向上直到root标红色
 * q向上，第一次遇到红色时，就找到了LCA
 */
public class LC236二叉树最近公共的祖先LCA {
    private TreeNode ans;

    //方法一:递归
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }

    //时间复杂度 O(n) 空间复杂度 O(n)
    //<有没有p,有没有q>
    private Pair<Boolean,Boolean> dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return new Pair<>(false,false);
        //dfs遍历左子树
        Pair<Boolean,Boolean> leftResult = dfs(root.left,p,q);
        //dfs遍历右子树
        Pair<Boolean,Boolean> rightResult = dfs(root.right,p,q);
        //左侧有p 右侧有p 或自身就是p
        boolean first = leftResult.getKey() || rightResult.getKey() || root.val == p.val;
        //左侧有q 右侧有q 或自身就是q
        boolean second = leftResult.getValue() || rightResult.getValue() || root.val == q.val;
        //DFS最深的一次且答案是空的
        if(first && second && ans ==null) ans =root;
        return new Pair<>(first,second);
    }

    //方法2：存储父节点
    //判断是否出现过或者涉及出现次数的问题，可以使用哈希表
    //测试用例包含：有公共祖先和没有公共祖先两种情况
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        //递归建立父节点
        father = new HashMap<Integer, TreeNode>();
        calcFather(root);
        redNodes = new HashSet<Integer>();
        redNodes.add(root.val);
        while (p.val != root.val) {
            redNodes.add(p.val);
            p = father.get(p.val);
        }
        while (!redNodes.contains(q.val)) {
            q = father.get(q.val);
        }
        return q;
    }

    //通过dfs 维护 每个节点的父节点信息，存到哈希表
    private void calcFather(TreeNode root) {
        if (root == null) return;
        if (root.left != null) {
            father.put(root.left.val, root);
            calcFather(root.left);
        }
        if (root.right != null) {
            father.put(root.right.val, root);
            calcFather(root.right);
        }
    }

    //每个节点的父节点指针
    private Map<Integer, TreeNode> father;
    private Set<Integer> redNodes;
}
