package com.kandy.algorithm.week09;

//f[i][j]表示s[1~i]选子序列得到t[1~j]的方案数
// f[i][j] = f[i-1][j]
//如果s[i] == t[j] 有f[i][j] += f[i-1][j-1]
//初值:f[i][0] =1
//目标：f[n][m]
public class LC115不同的子序列 {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        s = " " + s;
        t = " " + t;
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++)
            f[i][0] = 1;
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++) {
                f[i][j] = f[i - 1][j];
                //f[i][j] <= Integer.MAX_VALUE - f[i - 1][j - 1]
                if (s.charAt(i) == t.charAt(j) && f[i][j] + f[i - 1][j - 1] <= Integer.MAX_VALUE)
                    f[i][j] += f[i - 1][j - 1];
            }
        return f[n][m];
    }
}
