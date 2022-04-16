package com.kandy.algorithm.leetcode;

/**
 * 41. 缺失的第一个正数
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案
 */
public class Solution041 {
    /**
     * 对于一个长度为 N 的数组，其中没有出现的最小正整数只能在 [1, N+1]中。
     * 这是因为如果 [1, N][1,N] 都出现了，那么答案是 N+1N+1，
     * 否则答案是 [1, N]中没有出现的最小正整数
     * @return
     */
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        //1.将负数变为n+1  [3, 4, 7, 1, 9, 7]
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        //2.将<=6的元素对应位置变为负数 [-3, 4, -7, -1, 9, 7]
        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        //3.返回第一个大于零的元素下标 +1  返回2
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public static void main(String[] args) {
        int[] nums  = new int[]{3,4,-1,1,9,-5};
        System.out.println(firstMissingPositive(nums));
    }
}
