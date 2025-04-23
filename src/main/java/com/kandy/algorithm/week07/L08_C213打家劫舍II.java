package com.kandy.algorithm.week07;

import java.util.Arrays;

/**
 * 环形DP 1 n不能同时偷
 * 第一次：不偷1，可以偷n  f(1,1) = -oo
 * 第二次：不偷n,可以偷1  f(1,1) = nums[1]
 */
public class L08_C213打家劫舍II {
    public int rob(int[] numsInput) {
        int n = numsInput.length;
        // Move nums from [0..n-1] to [1..n]
        int[] nums = new int[n + 1];
        nums[0] = 0;
        for (int i = 1; i <= n; i++) nums[i] = numsInput[i - 1];
        //只有一间房屋
        if (n == 1) return nums[1];

        int[][] f = new int[n + 1][2];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i], -1000000000);
        f[1][0] = 0;

        // DP - the first time 不偷 1 注意i 变量这里从 2开始到 n
        for (int i = 2; i <= n; i++)
            for (int j = 0; j < 2; j++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
                f[i][1] = f[i - 1][0] + nums[i];
            }

        int ans = Math.max(f[n][0], f[n][1]);

        f[1][0] = 0;
        f[1][1] = nums[1];

        // DP - the second time 不偷n 可以偷1
        for (int i = 2; i <= n; i++)
            for (int j = 0; j < 2; j++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
                f[i][1] = f[i - 1][0] + nums[i];
            }

        return Math.max(ans, f[n][0]);//不偷n
    }
}
