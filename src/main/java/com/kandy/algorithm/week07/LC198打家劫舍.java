package com.kandy.algorithm.week07;

import java.util.Arrays;

/**
 * f(i,j) 表示计划偷窃前i座房屋，第i座房屋的闯入情况为j(0 未闯入 1 闯入)时的最大收益
 */
public class LC198打家劫舍 {
    public int rob(int[] numsInput) {
        int n = numsInput.length;
        // Move nums from [0..n-1] to [1..n]
        int[] nums = new int[n + 1];
        nums[0] = 0;
        for (int i = 1; i <= n; i++) nums[i] = numsInput[i - 1];

        int[][] f = new int[n + 1][2];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i], -1000000000);
        f[0][0] = 0;

        //第一种写法：f(i,0) f(i,1)从哪里来
        for (int i = 1; i <= n; i++)
            for (int j = 0; j < 2; j++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]); //第i座房屋不偷，i-1偷或不偷都可以
                f[i][1] = f[i - 1][0] + nums[i]; //第i座房屋偷，i-1不偷
            }
        //第n坐房屋偷或不偷都可以，取max
        return Math.max(f[n][0], f[n][1]);
    }

    //第二种写法：列表法 f(i,0) f(i,1)能去哪
    public int rob2(int[] numsInput) {
        int n = numsInput.length;
        // Move nums from [0..n-1] to [1..n]
        int[] nums = new int[n + 1];
        nums[0] = 0;
        for (int i = 1; i <= n; i++) nums[i] = numsInput[i - 1];

        int[][] f = new int[n + 1][2];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i], -1000000000);
        f[0][0] = 0;

        //第二种写法：f(i,0) f(i,1) 能去哪
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    f[i + 1][0] = Math.max(f[i + 1][0], f[i][0]); //第i座房屋不偷，i+1 不偷
                    f[i + 1][1] = Math.max(f[i + 1][1], f[i][0] + nums[i + 1]); //第i座房屋不偷，i+1 偷
                }
                if (j == 1) {
                    f[i + 1][0] = Math.max(f[i + 1][0], f[i][1]);  //第i座房屋偷，i+1 不偷
                }
            }
        return Math.max(f[n][0], f[n][1]);
    }

}
