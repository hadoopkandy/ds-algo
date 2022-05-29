package com.kandy.algorithm.week06;

/**
 * f[i] 为考虑前 ii 个字符的解码方案数
 * 只能由位置 i 的单独作为一个 item，设为 a
 * 只能由位置 i 的与前一位置（i-1）共同作为一个 item，设为 b
 * f[i]=f[i−1],1⩽ a ≤9  位置i单独作为一个item
 * f[i]=f[i−2],10⩽ b ⩽26  只能与前一个位置共同作为一个item
 * f[i]=f[i−1]+f[i−2],1⩽ a ≤9,10⩽ b ⩽26  位置i既能作为独立item 也能与上一位置形成 item
 */
public class LC91解码方法 {
    public int numDecodings(String s) {
        int n = s.length();
        s = " " + s;
        char[] cs = s.toCharArray();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            // a : 代表「当前位置」单独形成 item
            // b : 代表「当前位置」与「前一位置」共同形成 item
            int a = cs[i] - '0', b = (cs[i - 1] - '0') * 10 + (cs[i] - '0');
            // 如果 a 属于有效值，那么 f[i] 可以由 f[i - 1] 转移过来
            if (1 <= a && a <= 9) f[i] = f[i - 1];
            // 如果 b 属于有效值，那么 f[i] 可以由 f[i - 2] 或者 f[i - 1] & f[i - 2] 转移过来
            if (10 <= b && b <= 26) f[i] += f[i - 2];
        }
        return f[n];
    }

    //滚动数组
    public int numDecodings2(String s) {
        int n = s.length();
        s = " " + s;
        char[] cs = s.toCharArray();
        int[] f = new int[3];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            f[i % 3] = 0;
            int a = cs[i] - '0', b = (cs[i - 1] - '0') * 10 + (cs[i] - '0');
            if (1 <= a && a <= 9) f[i % 3] = f[(i - 1) % 3];
            if (10 <= b && b <= 26) f[i % 3] += f[(i - 2) % 3];
        }
        return f[n % 3];
    }
}
