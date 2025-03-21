package com.kandy.algorithm.week04;

/**
 * https://leetcode.cn/problems/search-in-rotated-sorted-array/description/
 */
public class LC33搜索旋转排序数组 {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            //如果首元素大于mid元素,就说明[left,right]区间后半部分有序,前半部分是循环有序数组
            if (nums[left] > nums[mid]) {
                if (nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
            } else {
                //如果首元素小于mid元素,就说明[left,right]区间前半部分有序,后半部分是循环有序数组
                if (nums[left] <= target && target < nums[mid]) right = mid - 1;
                else left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LC33搜索旋转排序数组 solution = new LC33搜索旋转排序数组();
        int[] nums = new int[]{4,5,6,7,0,1,2};
        System.out.println(solution.search(nums,0));
    }
}
