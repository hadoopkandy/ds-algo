package com.kandy.algorithm.week07;

import com.kandy.algorithm.leetcode.TreeNode;

import java.util.HashMap;

/**
 * f(x,0) 表示以x为根的子树，在不打劫x的情况下，能够盗取的最高金额
 * f(x,1) 表示以x为根的子树，在打劫x的情况下，能够盗取的最高金额
 */
public class LC337打家劫舍III {
    public int rob(TreeNode root) {
        f = new HashMap<TreeNode, int[]>();
        return dfs(root);
    }

    int dfs(TreeNode x) {
        if (x == null) return 0;
        int[] vals = new int[2];
        f.put(x, vals);
        if (x.left != null) {
            vals[0] += dfs(x.left);
            vals[1] += f.get(x.left)[0];
        }
        if (x.right != null) {
            vals[0] += dfs(x.right);
            vals[1] += f.get(x.right)[0];
        }
        vals[1] += x.val;
        return Math.max(vals[0], vals[1]);
    }

    public int rob2(TreeNode root) {
        f = new HashMap<TreeNode, int[]>();
        f.put(null, new int[]{0, 0});
        dfs2(root);
        return Math.max(f.get(root)[0], f.get(root)[1]);
    }

    void dfs2(TreeNode root) {
        if (root == null) return; //边界
        f.put(root, new int[2]);
        dfs2(root.left);
        dfs2(root.right);
        //当前节点不偷，则左右孩子节点偷或不偷都可以
        f.get(root)[0] = Math.max(f.get(root.left)[0], f.get(root.left)[1]) +
                Math.max(f.get(root.right)[0], f.get(root.right)[1]);
        //当前节点偷，则左右孩子不能偷
        f.get(root)[1] = f.get(root.left)[0] + f.get(root.right)[0] + root.val;
    }

    //数组长度不知道，就开一个HashMap<树节点，0或1>
    HashMap<TreeNode, int[]> f;
}
