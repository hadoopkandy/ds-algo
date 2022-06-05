package com.kandy.algorithm.week07.homework;

import java.util.Arrays;

/**
 * 物品：完全平方数 体积：n 价值：1
 */
public class LC279完全平方数 {
    public int numSquares(int n) {
        int[] f = new int[n + 1];
        Arrays.fill(f, (int) 1e9);
        f[0] = 0;

        //遍历物品 (完全平方数)
        for (int i = 1; i * i <= n; i++) {
            //正序遍历体积 v[i] = i*i
            for (int j = i * i; j <= n; j++) {
                f[j] = Math.min(f[j], f[j - i * i] + 1);
            }
        }
        return f[n];
    }
}
