package com.kandy.algorithm.week09;

/**
 * 167. 两数之和 II - 输入有序数组
 */
public class 双指针模板_LC167两数之和输入数组有序 {
    //双指针
    public int[] twoSum(int[] numbers, int target) {
        int j = numbers.length - 1;
        for (int i = 0; i < numbers.length; i++) {
            while (i < j && numbers[i] + numbers[j] > target) j--;
            if (i < j && numbers[i] + numbers[j] == target) {
                return new int[]{i + 1, j + 1};
            }
        }
        return null;
    }
}
