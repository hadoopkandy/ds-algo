package com.kandy.algorithm.week07;

import java.util.Arrays;

/**
 * 思路一：先戳哪个气球？ 戳了一个气球p以后，子问题[l,p-1] 和[p+1,r]两端相邻的气球发生了变化，nums数组在变化
 * 思路二：最后一个戳的是哪个气球? 先戳完[l,p-1]和[p+1,r],最后戳p
 */
public class LC312戳气球 {
    //区间动归
    public int maxCoins(int[] nums) {
        int n = nums.length;
        // 1 [1 .. n] 1
        int[] a = new int[n + 2];
        a[0] = a[n + 1] = 1;
        for (int i = 1; i <= n; i++) a[i] = nums[i - 1];
        // f[0..n+1][0..n+1]
        int[][] f = new int[n + 2][n + 2];
        for (int i = 0; i <= n + 1; i++) Arrays.fill(f[i], 0);
        // 区间DP 先枚举区间长度
        for (int len = 1; len <= n; len++) //区间动态规划,最外层循环区间长度
            for (int l = 1; l <= n - len + 1; l++) {//然后循环左端点
                int r = l + len - 1; // 计算出右端点 闭区间 len = r-l+1
                for (int p = l; p <= r; p++)
                    f[l][r] = Math.max(f[l][r], f[l][p - 1] + f[p + 1][r] + a[l - 1] * a[p] * a[r + 1]);
            }
        return f[1][n];
    }

    //记忆化搜索 先递归p左右两半，最后p
    public int maxCoins2(int[] nums) {
        int n = nums.length;
        nums1 = new int[n + 2];
        //下标从1开始
        for (int i = 1; i <= n; i++) nums1[i] = nums[i - 1];
        nums1[0] = 1;  //1的左侧1
        nums1[n + 1] = 1;//n的右侧1

        f = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                f[i][j] = -1; //记忆化搜索，没算过赋值-1

        return calc(1, n);
    }

    private int calc(int l, int r) {
        if (l > r) return 0;//空区间返回0
        if (f[l][r] != -1) return f[l][r];
        for (int p = l; p <= r; p++)
            //先戳左边，再右边，然后p
            f[l][r] = Math.max(f[l][r], calc(l, p - 1) + calc(p + 1, r) + nums1[p] * nums1[l - 1] * nums1[r + 1]);
        return f[l][r];
    }

    int[] nums1;
    int[][] f;
}
