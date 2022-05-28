package com.kandy.algorithm.week06.homework;

public class LC70爬楼梯 {
    public int climbStairs(int n) {
        return climb(n);
    }

    public int climb(int n) {
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i == 1) { f[i] = 1; continue; } //n=1 1种方法
            if (i == 2) { f[i] = 2; continue; } //n=2 2种方法
            f[i] = f[i - 1] + f[i - 2]; //状态转移方法
        }
        return f[n];
    }
}
