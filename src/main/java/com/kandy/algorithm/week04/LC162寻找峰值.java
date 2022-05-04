package com.kandy.algorithm.week04;

public class LC162寻找峰值 {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int lmid = (left + right) / 2;
            int rmid = lmid + 1;
            if (nums[lmid] <= nums[rmid]) left = lmid + 1;//放弃lmid左边部分
            else right = rmid - 1; //放弃rmid右侧部分
        }
        return right;
    }
}
