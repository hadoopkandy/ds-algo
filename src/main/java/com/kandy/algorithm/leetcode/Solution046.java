package com.kandy.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案
 */
public class Solution046 {
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[len];
        dfs(nums, len, 0, path, used, res);
        return res;
    }

    /**
     *
     * @param nums
     * @param len
     * @param depth 递归到第几层
     * @param path 已经选了哪些数
     * @param used 布尔数组
     * @param res
     */
    private void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        //递归终止条件
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, len, depth + 1, path, used, res);
            path.removeLast();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        Solution046 sol = new Solution046();
        System.out.println(sol.permute(new int[]{1, 2, 3}));

    }
}
