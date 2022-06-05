package com.kandy.algorithm.week07;

/**
 * f(l,r,i) 表示把l-r合成i堆的最低成本
 */
public class LC1000合并石头的最低成本 {
    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        int[][][] f = new int[n][n][k + 1];
        for (int len = 0; len < n; len++)
            for (int l = 0; l < n; l++)
                for (int i = 0; i <= k; i++)
                    f[len][l][i] = 1000000000;
        for (int l = 0; l < n; l++) f[l][l][1] = 0; //长度为1的区间

        for (int len = 2; len <= n; len++)
            for (int l = 0; l < n - len + 1; l++) {
                int r = l + len - 1;
                for (int i = 2; i <= k; i++) {
                    for (int p = l; p < r; p++)
                        f[l][r][i] = Math.min(f[l][r][i], f[l][p][1] + f[p + 1][r][i - 1]);
                }
                int sum = 0;
                for (int p = l; p <= r; p++) sum += stones[p];
                f[l][r][1] = Math.min(f[l][r][1], f[l][r][k] + sum);
            }
        //0到n-1合成1堆
        return f[0][n - 1][1] == 1000000000 ? -1 : f[0][n - 1][1];
    }
}
