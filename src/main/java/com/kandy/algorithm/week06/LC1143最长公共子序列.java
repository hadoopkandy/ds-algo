package com.kandy.algorithm.week06;

/**
 * 状态：取字符串的边界
 * 最优子结构：最长长度
 */
public class LC1143最长公共子序列 {
    //动归边界处理技巧1: 认为字符串下标从1开始，f[i,0]=0与f[0,j]均作为边界，目标f[m,n]
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        text1 = " " + text1;
        text2 = " " + text2;
        //f[i][0]  f[0][j]初始化为0
        int f[][] = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                }
            }
        }
        return f[m][n];
    }

    //动归边界处理技巧2 f[0,0]=0,然后递推时用if语句判断，目标f[m-1,n-1]
    public int longestCommonSubsequenc2(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int f[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    if (i == 0 || j == 0) f[i][j] = 1;
                    else f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    if (i == 0 & j == 0) f[i][j] = 0;
                    else if (i == 0) f[i][j] = f[i][j - 1];
                    else if (j == 0) f[i][j] = f[i - 1][j];
                    else f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                }
            }
        }
        return f[m - 1][n - 1];
    }

    //打印方案
    public int longestCommonSubsequence3(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        text1 = " " + text1;
        text2 = " " + text2;
        //f[i][0]  f[0][j]初始化为0
        int f[][] = new int[m + 1][n + 1];
        int preType[][] = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                    preType[i][j] = 2;
                } else {
                    //f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                    if (f[i - 1][j] > f[i][j - 1]) {
                        f[i][j] = f[i - 1][j];
                        preType[i][j] = 0;
                    } else {
                        f[i][j] = f[i][j - 1];
                        preType[i][j] = 1;
                    }
                }
            }
        }
        print(text1, text2, preType, m, n);
        return f[m][n];
    }

    void print(String text1, String text2, int[][] preType, int i, int j) {
        if (i == 0 || j == 0) return;
        if (preType[i][j] == 0) print(text1, text2, preType, i - 1, j);
        else if (preType[i][j] == 1) print(text1, text2, preType, i, j - 1);
        else {
            print(text1, text2, preType, i - 1, j - 1);
            System.out.print(text1.charAt(i));
        }
    }
}
