package com.kandy.algorithm.week03;

import com.kandy.algorithm.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC297二叉树的序列化与反序列化 {
    public class Codec {

        // Encodes a tree to a single string.
        // 1,2,null,null,3,4,null,null,5,null,null
        public String serialize(TreeNode root) {
            List<String> seq = new ArrayList<String>();
            dfs(seq, root);
            return String.join(",", seq);
        }

        void dfs(List<String> seq, TreeNode root) {
            if (root == null) {
                seq.add("null");
                return;
            }
            seq.add(String.valueOf(root.val));
            dfs(seq, root.left);
            dfs(seq, root.right);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            seq = data.split(",");
            curr = 0;
            return restore();
        }

        TreeNode restore() {
            if (seq[curr].equals("null")) {
                curr++;
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(seq[curr]));
            curr++;
            root.left = restore();
            root.right = restore();
            return root;
        }

        String[] seq;
        int curr;
    }
}
