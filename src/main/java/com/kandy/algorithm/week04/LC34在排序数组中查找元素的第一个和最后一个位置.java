package com.kandy.algorithm.week04;

public class LC34在排序数组中查找元素的第一个和最后一个位置 {
    public int[] searchRange(int[] nums, int target) {
        //开始位置：第一个>=target的数
        //结束位置：最后一个<=target的数
        int[] ans = new int[2];
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        ans[0] = right;

        left = -1;
        right = nums.length - 1;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        ans[1] = right;
        if (ans[0] > ans[1]) return new int[]{-1, -1};
        return ans;
    }

}
