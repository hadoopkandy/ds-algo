package com.kandy.algorithm.week02;

import java.util.*;

/**
 * https://leetcode.cn/problems/majority-element/description/
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素
 */
public class LC169多数元素 {

    //哈希表
    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }
        return majorityEntry.getKey();

        //分治
        //return getMajority(nums, 0, nums.length - 1);
    }
    //1.划分 每次将数组拆分为左右两个区间，直至拆成最小规模的问题，每个区间只有一个数
    //2.求解 在最小的区间里，每个区间只有一个数，那该区间的众数该数
    //3.合并 第一种：左右两个区间的众数相同，那直接返回这个众数  第二种：左右两个区间的众数不同，这时就将两个区间合并，在合并后的区间种计算出这两个众数出现的次数，将数值大的众数返回
    private int getMajority(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        //递归划分左右区间
        int mid = left + (right - left) / 2; //写法等价于 (left + right )/2  (left + right) >> 1
        int maxLeft = getMajority(nums, left, mid);
        int maxRight = getMajority(nums, mid + 1, right);

        //如果左边众数 = 右边的众数
        if (maxLeft == maxRight) {
            return maxLeft;
        }

        //如果左右众数不相等 合并区间找众数
        int leftCnt = getCnt(nums, maxLeft, left, right);
        int rightCnt = getCnt(nums, maxRight, left, right);

        return leftCnt >= rightCnt ? maxLeft : maxRight;
    }
    private int getCnt(int[] nums, int num, int left, int right) {
        int cnt = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] == num) {
                cnt++;
            }
        }
        return cnt;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 3};
        System.out.println(majorityElement(nums));
    }
}
