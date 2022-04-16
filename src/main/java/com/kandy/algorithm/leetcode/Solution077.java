package com.kandy.algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合
 */
public class Solution077 {
    LinkedList<Integer> path = new LinkedList<>();
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtracking(n, k, 1);
        return res;
    }

    public void backtracking(int n, int k, int startIndex) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= n - (k-path.size()) + 1; i++) {
            path.add(i);
            backtracking(n, k, i + 1);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        Solution077 sol = new Solution077();
        System.out.println(sol.combine(4, 4));
    }
}
