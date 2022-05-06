package com.kandy.algorithm.week04;

/**
 * 满足nums[i] <= nums[n-1] 条件时较小的index 后继型
 */
public class LC153寻找旋转排序数组中的最小值 {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= nums[right]) {  // 条件满足（true，即1）
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[right];
    }
}
/**
 3 4 5 1 2 让每个数尝试跟结尾比较 nums[i] <= nums[n-1]
 0 0 0 1 1

 4 5 6 7 0 1 2
 0 0 0 0 1 1 1

 */
