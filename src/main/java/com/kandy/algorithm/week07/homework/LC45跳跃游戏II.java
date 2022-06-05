package com.kandy.algorithm.week07.homework;

import java.util.Arrays;

public class LC45跳跃游戏II {
    //动态规划
    //f[i]表示：到达位置i的最小步数
    public int jump(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];

        Arrays.fill(f, (int) 1e9);
        f[0] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) {
                    //在i前面找一个可以跳跃到i步数最少的j
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
        }
        return f[n - 1];
    }
}
