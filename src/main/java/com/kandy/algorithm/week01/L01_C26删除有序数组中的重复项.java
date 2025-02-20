package com.kandy.algorithm.week01;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description/
 * 26. 删除有序数组中的重复项
 * 思路：
 * 1.保序操作：过滤器思想
 * 2.检查边界条件
 */
public class L01_C26删除有序数组中的重复项 {

    public int removeDuplicates(int[] nums) {
        int n = 0;
        for(int i =0; i<nums.length; i++){
            // 过滤器条件：不重复就要
            if(i==0 || nums[i] !=nums[i-1]){
                nums[n] =nums[i];
                n++;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        L01_C26删除有序数组中的重复项 sol = new L01_C26删除有序数组中的重复项();
        int[] nums = new int[]{1, 2, 3, 4, 5};
        System.out.println(sol.removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }
}
