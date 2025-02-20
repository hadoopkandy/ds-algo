package com.kandy.algorithm.week01;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/first-missing-positive/description/
 */
public class LC41缺失的第一个正数 {
    /**
     * 哈希表（空间复杂度不符合要求）
     */
    public int firstMissingPositive1(int[] nums) {
        int n = nums.length;

        Set<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }

        // 从最小的正整数1开始，依次判断2、3、4直到数组的长度N是否在数组中
        for (int i = 1; i <= n ; i++) {
            if (!hashSet.contains(i)){
                return i;
            }
        }

        return n + 1;
    }

    /**
     * 二分查找（时间复杂度不符合要求）
     * 这个问题其实就是要我们查找一个元素，而查找一个元素，如果是在有序数组中查找，会快一些；
     * 因此我们可以将数组先排序，再使用二分查找法从最小的正整数1开始查找，找不到就返回这个正整数；
     * 这个思路需要先对数组排序，而排序使用的时间复杂度是O(NlogN)，是不符合这个问题的时间复杂度要求。
     */
    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        for (int i = 1; i <= n; i++) {
            int res = binarySearch(nums, i);
            if (res == -1) {
                return i;
            }
        }
        return n + 1;
    }

    private int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 将数组视为哈希表 :
     * 原地哈希（哈希函数为：f(nums[i]) = nums[i] - 1）
     * 数值为i的数映射到下标为i - 1的位置
     * 要找的数一定在[1, N + 1]左闭右闭（这里N是数组的长度）这个区间里
     * 思路：就把1这个数放到下标为0的位置，2这个数放到下标为1的位置，按照这种思路整理一遍数组。然后我们再遍历一次数组，第1个遇到的它的值不等于下标的那个数，就是我们要找的缺失的第一个正数。
     *
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // 满足在指定范围内、并且没有放在正确的位置上，才交换
                // 例如：数值 3 应该放在索引 2 的位置上
                swap(nums, nums[i] - 1, i);
            }
        }

        // [1, -1, 3, 4]
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // 都正确则返回数组长度 + 1
        return n + 1;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

}
