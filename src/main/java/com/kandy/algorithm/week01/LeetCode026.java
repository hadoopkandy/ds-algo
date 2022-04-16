package com.kandy.algorithm.week01;

import java.util.Arrays;

/**
 * 26. 删除有序数组中的重复项
 */
public class LeetCode026 {

    public int removeDuplicates(int[] nums) {
        int n = 0;
        for(int i =0; i<nums.length; i++){
            if(i==0 || nums[i] !=nums[i-1]){
                nums[n] =nums[i];
                n++;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        LeetCode026 sol = new LeetCode026();
        int[] nums = new int[]{1, 2, 3, 4, 5};
        System.out.println(sol.removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }
}
