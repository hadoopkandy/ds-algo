package com.kandy.algorithm.week04.homework;

//[3,3,1,3]
public class LC154寻找旋转排序数组中的最小值II {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < nums[right]) {  // 条件满足（true，即1）
                right = mid; //mid可能是最小值
            } else if (nums[mid] == nums[right]) {
                right = right - 1; //缩小查找范围，对结果没影响
            } else {
                left = mid + 1; //mid不可能是最小值
            }
        }
        return nums[right];
    }
}
