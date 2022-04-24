package com.kandy.algorithm.week02;

/**
 * 167. 两数之和 II - 输入有序数组
 */
public class LC167两数之和II输入有序数组 {
    //双指针
    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] == target) {
                return new int[]{i + 1, j + 1};
            } else if (numbers[i] + numbers[j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return new int[0];
    }
}
