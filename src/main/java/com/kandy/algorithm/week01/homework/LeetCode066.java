package com.kandy.algorithm.week01.homework;

/**
 * 66. 加一
 */
public class LeetCode066 {
    public int[] plusOne(int[] digits) {
        if(digits ==null || digits.length ==0) return digits;
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            digits[i] = (digits[i] + 1) % 10;
            //从后往前，加一之后判断结果是否为0，不为0直接返回结果，为0需要往前进1位，前1位继续加1判断
            if (digits[i] != 0) {
                return digits;
            }
        }
        //99..9
        int[] ans = new int[n + 1];
        ans[0] = 1;
        return ans;
    }
}
