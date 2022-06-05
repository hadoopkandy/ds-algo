package com.kandy.algorithm.week07;

import java.util.Arrays;

/**
 * 0/1背包
 * 有一个容积为M的背包，要求选择一些物品放入背包，使得物品体积不超过M的前提下，物品价值总和最大
 * f(i,j) 表示从前i个物品中选出了总体积为j的物品放入背包，物品的最大价值和
 * 决策：第i个物品 选或不选
 */
public class LC01背包问题 {
    public static void main(String[] args) {
        LC01背包问题 lc = new LC01背包问题();
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagsize = 4;
        System.out.println(lc.testweightbagproblem(weight, value, bagsize));
    }

    //n个物品 第i个物品的体积为Vi 价值Wi 总体积不超过m
    public int testweightbagproblem(int[] volume, int[] value, int m) {
        int n = volume.length;

        // 0.Move index to 1 based 物品数组
        int[] v = new int[n + 1];
        v[0] = 0;
        for (int i = 1; i <= n; i++) v[i] = volume[i - 1];

        //价值数组
        int[] w = new int[n + 1];
        w[0] = 0;
        for (int i = 1; i <= n; i++) w[i] = value[i - 1];

        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = -(int) 1e9;
            }
        }
        f[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++)
                //从f[i-1]拷贝到f[i]
                f[i][j] = f[i - 1][j];//不选第i个物品：前i-1个物品，选体积为j  i不要
            for (int j = v[i]; j <= m; j++)  // 或者合并到一个j循环里，要加一个条件j>=v[i]
                //选第i个物品：前i-1个物品，选出j-vi的体积  选i物品 体积变成j 价值增加wi  j>=vi
                f[i][j] = Math.max(f[i][j], f[i - 1][j - v[i]] + w[i]);
        }
        int ans = 0;
        for (int j = 0; j <= m; j++) ans = Math.max(ans, f[n][j]); //选择体积不超过m的j
        return ans;
    }

    //空间优化版 j必须倒序循环 j-v[i]更新j  j更新j+v[i]
    public int testweightbagproblem2(int[] volume, int[] value, int m) {
        int n = volume.length;

        // 0.Move index to 1 based 物品数组
        int[] v = new int[n + 1];
        v[0] = 0;
        for (int i = 1; i <= n; i++) v[i] = volume[i - 1];

        //价值数组
        int[] w = new int[n + 1];
        w[0] = 0;
        for (int i = 1; i <= n; i++) w[i] = value[i - 1];

        int[] f = new int[m + 1];
        Arrays.fill(f, -(int) 1e9);
        f[0] = 0;

        for (int i = 1; i <= n; i++) {
            //j必须倒序循环 后面是i阶段 前面是i-1阶段
            for (int j = m; j >= v[i]; j--) {
                    //选第i个物品：前i-1个物品，选出j-vi的体积  选i物品 体积变成j 价值增加wi  j>=vi
                    f[j] = Math.max(f[j], f[j - v[i]] + w[i]);
            }
        }
        int ans = 0;
        for (int j = 0; j <= m; j++) ans = Math.max(ans, f[j]); //选择体积不超过m的j
        return ans;
    }
    //完全背包 物品可以选多次
    public int testweightbagproblem3(int[] volume, int[] value, int m) {
        int n = volume.length;

        // 0.Move index to 1 based 物品数组
        int[] v = new int[n + 1];
        v[0] = 0;
        for (int i = 1; i <= n; i++) v[i] = volume[i - 1];

        //价值数组
        int[] w = new int[n + 1];
        w[0] = 0;
        for (int i = 1; i <= n; i++) w[i] = value[i - 1];

        int[] f = new int[m + 1];
        Arrays.fill(f, -(int) 1e9);
        f[0] = 0;

        for (int i = 1; i <= n; i++) {
            //正序遍历
            for (int j = v[i]; j <= m; j++) {
                f[j] = Math.max(f[j], f[j - v[i]] + w[i]);
            }
        }
        int ans = 0;
        for (int j = 0; j <= m; j++) ans = Math.max(ans, f[j]);
        return ans;
    }

}
