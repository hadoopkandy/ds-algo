package com.kandy.algorithm.week01;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组
 */
public class LC88合并两个有序数组 {

    //逆向双指针
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        for (int k = m + n - 1; k >= 0; k--) {
            //什么时候要nums1[i] ? 或者j出界了，或者i j都没出界，要大的
            if (j < 0 || (i >= 0 && nums1[i] >= nums2[j])) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
        }
    }

    public static void main(String[] args) {
        LC88合并两个有序数组 sol = new LC88合并两个有序数组();
        int[] nums1 = new int[]{1, 2, 3, 4, 0, 0, 0};
        int[] nums2 = new int[]{5, 6, 7};
        sol.merge(nums1, 4, nums2, 3);
        System.out.println(Arrays.toString(nums1));

    }
}
