package com.kandy.algorithm.week09.homework;


public class LC44通配符匹配 {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        s = " " + s; //下标从1开始
        p = " " + p; //下标从1开始
        boolean[][] f = new boolean[n + 1][m + 1];
        f[0][0] = true;
        //f[0][j]=true
        for (int j = 1; j <= m && p.charAt(j) == '*'; j++) f[0][j] = true;

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++) {
                //如果是小写字母
                if (p.charAt(j) >= 'a' && p.charAt(j) <= 'z') {
                    f[i][j] = f[i - 1][j - 1] && (s.charAt(i) == p.charAt(j));
                    //'?' 可以匹配任何单个字符
                } else if (p.charAt(j) == '?') {
                    f[i][j] = f[i - 1][j - 1];
                }else if (p.charAt(j) == '*') {
                    f[i][j] = f[i][j - 1] || f[i - 1][j];
                }
            }
        return f[n][m];
    }
}
/*
f[i][j] 表示s的前i个字符(以i结尾)，p的前j个字符(以j结尾)，能否匹配
如果p[j]是a~z  f[i][j] = f[i-1][j-1] && s[i]==p[j]  （s的第i个字符与p的第j个字符是否相同）
如果p[j]是?    f[i][j] = f[i-1][j-1]    ? 可以匹配任何单个字符
如果p[j]是*   f[i][j] = f[i][j - 1] || f[i - 1][j]   *可以匹配任意字符串（包括空字符串）
可匹配任意长度的字符,可匹配0个字符，匹配1个字符、匹配2个字符
当匹配0个：f[i][j]=f[i,j-1]
当匹配1个：f[i][j]=f[i-1,j-1]
当匹配1个：f[i][j]=f[i-2,j-1]
...
当匹配k个：f[i][j]=f[i-k,j-1]
此时对于p[j]='*'的情况，想要f[i,j]=true，只需要其中一种情况为true即可
 */
