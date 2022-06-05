package com.kandy.algorithm.week07.homework;

/**
 * f(l,r) 表示[l,r]中最长回文子序列长度
 */
public class LC516最长回文子序列 {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        s = " " + s;
        int[][] f = new int[n + 1][n + 1];
        f[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            f[i][i] = 1; //长度为1字符串的回文字序列长度为1
        }

        for (int len = 2; len <= n; len++) //区间动态规划,最外层循环区间长度
            for (int l = 1; l <= n - len + 1; l++) {//然后循环左端点
                int r = l + len - 1; // 根据len、l计算出右端点r  闭区间len=r-l+1 -> r=l+len-1
                if (s.charAt(l) == s.charAt(r)) {
                    f[l][r] = f[l + 1][r - 1] + 2;
                } else {
                    f[l][r] = Math.max(f[l + 1][r], f[l][r - 1]);
                }
            }
        return f[1][n];
    }
}
