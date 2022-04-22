package com.kandy.algorithm.week01;

/**
 * 283. 移动零
 */
public class LC283移动零 {
    public void moveZeroes(int[] nums) {
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[n] = nums[i];
                n++;
            }
        }
        while (n < nums.length) {
            nums[n] = 0;
            n++;
        }
    }
}
