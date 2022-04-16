package com.kandy.algorithm.leetcode;

import java.util.Arrays;

/**
 * 剑指 Offer 61. 扑克牌中的顺子
 * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 * 示例：
 * 输入: [1,2,3,4,5]  输出: true
 * 输入: [0,0,1,2,5] 输出: true
 */
public class Offer061 {
    public static boolean isStraight(int[] nums) {
        int joker = 0;
        Arrays.sort(nums); // 数组排序
        for(int i = 0; i < 4; i++) {
            if(nums[i] == 0) joker++; // 统计大小王数量
            else if(nums[i] == nums[i + 1]) return false; // 若有重复，提前返回 false
        }
        return nums[4] - nums[joker] < 5; // 最大牌 - 最小牌 < 5 则可构成顺子
    }

    public static void main(String[] args) {
        int[] nums  = new int[]{1,2,3,4,5};
        System.out.println(isStraight(nums));
    }

}
