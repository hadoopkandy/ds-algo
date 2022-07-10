package com.kandy.algorithm.week06.homework;

import java.util.Arrays;

public class LC70爬楼梯 {
    public int climbStairs(int n) {
        return climb(n);
    }

    public int climb(int n) {
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                f[i] = 1;
                continue;
            } //n=1 1种方法
            if (i == 2) {
                f[i] = 2;
                continue;
            } //n=2 2种方法
            f[i] = f[i - 1] + f[i - 2]; //状态转移方程
        }
        return f[n];
    }

    //记忆化搜索（递归）
    public int climbStairs2(int n) {
        result = new int[n + 1];
        Arrays.fill(result, -1);
        return climb2(n);
    }

    public int climb2(int n) {
        if (n <= 1) return 1;
        if (result[n] != -1) {
            return result[n];
        }

        result[n] = climb2(n - 1) + climb2(n - 2);
        return result[n];
    }

    int[] result;

}
