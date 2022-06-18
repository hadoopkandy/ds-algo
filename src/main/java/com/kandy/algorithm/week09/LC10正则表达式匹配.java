package com.kandy.algorithm.week09;

public class LC10正则表达式匹配 {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        s = " " + s; //下标从1开始
        p = " " + p; //下标从1开始
        boolean[][] f = new boolean[n + 1][m + 1];
        f[0][0] = true;
        //f[0][j]=true （j之前一直是c*a*b*.*）
        for (int j = 2; j <= m && p.charAt(j) == '*'; j += 2) f[0][j] = true;

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++) {
                if (p.charAt(j) >= 'a' && p.charAt(j) <= 'z') {
                    f[i][j] = f[i - 1][j - 1] && (s.charAt(i) == p.charAt(j));
                } else if (p.charAt(j) == '.') {
                    f[i][j] = f[i - 1][j - 1]; //.可以配任意字符
                } else {
                    f[i][j] = f[i][j - 2]; //停，不配了
                    //*前面的字符与s[i]相等  或者.*
                    if (s.charAt(i) == p.charAt(j - 1) || p.charAt(j - 1) == '.')
                        f[i][j] = f[i][j] || f[i - 1][j];
                }
            }
        return f[n][m];
    }
/*
12345
caaab
ca*b  *号配3个a
bool dfs(int i,int j){
   //....
}

dfs(5,4) <- dfs(4,3) <- dfs(3,1) 配1个a
                     <- dfs(2,1) 配2个a
                     <- dfs(1,1) 配3个a  <- dfs(0,0)

f[i][j] 表示s的前i个字符，p的前j个字符，能否匹配
如果p[j]是a~z  f[i][j] = f[i-1][j-1] && s[i]==p[j]
如果p[j]是.    f[i][j] = f[i-1][j-1]   .可以配任意字符
如果p[j]是*
      (1) 继续配s[i]被*包含 f[i][j] = f[i-1][j] && (s[i] ==p[j-1] || p[j-1]='.')
      (2) 停，不配了 *的使命完成了  f[i][j]<- f[i][j-2]

*的另外做法，枚举配几个字符

caaa
ca*
3步继续配 1步停
f[4][3] <- f[3][3] <- f[2][3] <- f[1][3] <- f[1][1]

初值: f[0][0] =true f[0][j]=true （j之前一直是c*a*b*.*）
目标:f[n][m]


caaa
ca*ab  *号配2个a
 */
}
