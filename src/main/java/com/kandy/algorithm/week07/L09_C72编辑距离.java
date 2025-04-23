package com.kandy.algorithm.week07;

/**
 * f(i,j)表示word1前i个字符 变成word2前j个字符最少需要多少次
 * horse 变成 ros
 * hor 变成 ro (h替换为r,删除r)
 * se 变成s（删除e）
 *
 * f(i,j) = min( f(i,j-1)+1 插入
 * f(i-1,j) + 1   删除
 * f(i-1,j-1) + eq 替换or不变  如果word1[i] == word2[j] 则eq=0 否则eq=1
 */
public class L09_C72编辑距离 {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        word1 = " " + word1; //下标变成从1开始
        word2 = " " + word2;//下标变成从1开始

        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) f[i][0] = i; //长度为i的字符串，变成空串，删i次
        for (int j = 0; j <= m; j++) f[0][j] = j;//把空串变成长度为j的串，插入j次

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++) {
                //插入：word1前i个变成word2前j-1个，再插入word2[j]这个字符,例如:ea变成 ebc -> 先把ea 变成 eb,然后插入 c
                //删除：word1前i-1个变成word2前j个，删掉word1[i]这个字符,例如:hor 变成 ro -> 先ho 变成 ro,删除r
                f[i][j] = Math.min(f[i][j - 1] + 1, f[i - 1][j] + 1);
                //替换：把word1 前i-1个字符变成word2 前j-1个字符，如果word1[i] == word2[j]不用操作
                if (word1.charAt(i) == word2.charAt(j))
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1]);
                else
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1] + 1);
            }
        return f[n][m];
    }
}
