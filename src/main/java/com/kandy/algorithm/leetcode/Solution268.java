package com.kandy.algorithm.leetcode;

import java.util.Arrays;

/**
 * 268. 丢失的数字
 * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 */
public class Solution268 {
    public static int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        int[] nums  = new int[]{3,0,1};
        System.out.println(missingNumber(nums));
    }
}
