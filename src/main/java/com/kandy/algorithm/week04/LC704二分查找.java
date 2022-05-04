package com.kandy.algorithm.week04;

/**
 * 二分查找的前提：
 * 有序
 * 上下界
 * 随机访问
 */
public class LC704二分查找 {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
