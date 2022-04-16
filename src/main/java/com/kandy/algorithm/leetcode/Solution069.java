package com.kandy.algorithm.leetcode;

/**
 * 69. x的平方根
 * 给你一个非负整数x ,计算并返回x的算术平方根
 * 由于返回类型是整数,结果只保留 整数部分,小数部分将被舍去
 */
public class Solution069 {
    public int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution069 sol = new Solution069();
        System.out.println(sol.mySqrt(8));
        System.out.println(Math.sqrt(8));

    }
}
