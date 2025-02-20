package com.kandy.algorithm.week01;

/**
 * https://leetcode.cn/problems/move-zeroes/description/
 * 283. 移动零
 * 思路：
 * 1.过滤器思想：非零就保留
 * 2.补零
 */
public class L02_C283移动零 {
    public void moveZeroes(int[] nums) {
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            //过滤器思想：非零就保留
            if (nums[i] != 0) {
                nums[n] = nums[i];
                n++;
            }
        }
        //补零
        while (n < nums.length) {
            nums[n] = 0;
            n++;
        }
    }
}
