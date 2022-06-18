package com.kandy.algorithm.week09;


public class LC5最长回文子串 {
    //方法一：枚举中心，向两侧扩展 O(n^2)
    public String longestPalindrome(String s) {
        int n = s.length();
        int anslen = 0; //答案的长度
        int ansStart = 0;//答案的起始位置
        // 奇回文串
        for (int center = 0; center < n; center++) {
            int l = center - 1, r = center + 1;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            // l+1~r-1是以centre为中心的最长回文子串
            if (r - l - 1 > anslen) {
                anslen = r - l - 1;
                ansStart = l + 1;
            }
        }

        // 偶回文串
        for (int center = 1; center < n; center++) {
            int l = center - 1, r = center;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            // l+1~r-1是以center-1和center之间的空为中心的最长回文子串
            if (r - l - 1 > anslen) {
                anslen = r - l - 1;
                ansStart = l + 1;
            }
        }

        return s.substring(ansStart, ansStart + anslen);
    }

    //方法二：加入二分，Rabin-Karp优化 O(nlogn)
    public String longestPalindrome2(String s) {
        if (s.isEmpty()) return "";
        n = s.length();

        // 模板：预处理hash
        preH = new long[n + 1]; // 前缀Hash
        sufH = new long[n + 2]; // 后缀Hash 考虑当 i = n时的 i+1越界问题，所以数组长度变成n+2
        powB = new long[n + 1]; // 131次幂
        powB[0] = 1;
        //下标从1开始，对应原始字符串0~n-1
        for (int i = 1; i <= n; i++) {
            preH[i] = (preH[i - 1] * b + (s.charAt(i - 1) - 'a' + 1)) % p;
            powB[i] = powB[i - 1] * b % p;
        }

        for (int i = n; i >= 1; i--) {
            sufH[i] = (sufH[i + 1] * b + (s.charAt(i - 1) - 'a' + 1)) % p;
        }

        int anslen = 0;
        int ansstart = 0;
        // 中心是一个字符，比如aba
        for (int center = 0; center < n; center++) {
            //二分求从center往两侧可以扩展多少个字符
            int left = 0, right = Math.min(center, n - 1 - center);
            while (left < right) {
                int mid = (left + right + 1) / 2;
                //回文里找最长
                if (calcHash(center - mid, center + mid) == calcReverseHash(center - mid, center + mid)) left = mid;
                else right = mid - 1;
            }
            //center-right ~ center + right以center为中心的最长回文子串
            // 两侧最多扩展lenL，再加一个中心
            if (2 * right + 1 > anslen) {
                anslen = 2 * right + 1;
                ansstart = center - right;
            }
        }
        // 中心是两个字符（一个空档），比如abba
        for (int center = 1; center < n; center++) {
            //二分求从center-1和center之间空往两侧可以扩展多少个字符
            int left = -1, right = Math.min(center - 1, n - 1 - center);
            while (left < right) {
                int mid = (left + right + 1) / 2;
                if (calcHash(center - 1 - mid, center + mid) == calcReverseHash(center - 1 - mid, center + mid))
                    left = mid;
                else right = mid - 1;
            }
            //center-1-right ~ center + right以center-和center之间的空为中心的最长回文子串
            if (2 * right + 2 > anslen) {
                anslen = 2 * right + 2;
                ansstart = center - 1 - right;
            }
        }
        return s.substring(ansstart, ansstart + anslen);
    }

    // 模板：O(1)得到子串[l..r]的Hash值
    long calcHash(int l, int r) {
        //foobar
        //-fo000
        //=oba
        //[l+1,r+1] l不要 l+1要
        return ((preH[r + 1] - preH[l] * powB[r - l + 1]) % p + p) % p;
    }

    // O(1)得到子串[l..r]反着读的Hash值
    //s= "foobar"
    //rabo
    //-r000
    //=abo  =oba反着读的hash值
    //[l+1,r+1] r+1要 r+2不要
    long calcReverseHash(int l, int r) {
        return ((sufH[l + 1] - sufH[r + 2] * powB[r - l + 1]) % p + p) % p;
    }

    long[] preH;
    long[] sufH;
    long[] powB;
    int b = 131;
    int p = (int) 1e9 + 7;
    int n;
}
