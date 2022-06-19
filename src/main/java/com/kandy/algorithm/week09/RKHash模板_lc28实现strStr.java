package com.kandy.algorithm.week09;


/**
 * int x = 23124613 -> (x/100)%1000
 * (a*b)%p =(a%p)*(b%p)%p 成立    示例：(8*4)%4
 * (a/b)%p = (a%p)/(b%p) 不成立 示例:（8/4）%4
 * <p>
 * Rabin-Karp算法 O(n)预处理+O(1)求出任意子串哈希值的特性
 * 1.把字符串看成数值，进制数
 * a=1,b=2,z=26
 * <p>
 * 2.空间时间平衡
 * 选用的Hash函数：
 * 把字符串看作一个b进制数(一个多项式)，计算它（在十进制下）对p取模的值
 * H[i]=Hash(s[0...i-1])=(H[i-1] * b + s[i-1])mod p (乘进制加末尾)
 * <p>
 * 3.取一段，用四则运算
 * <p>
 * 解决问题：t是不是s的子串，t在s中第一次出现的位置
 */
public class RKHash模板_lc28实现strStr {

    //Rabin-Karp O(n+m) haystack文本串 needle模式串
    //使用Rabin-Karp解决字符串匹配问题的思路：
    //1.计算长度为m的模式串t的hash值 has_pattern,O(m)
    //2.计算长度为n的文本串s中每个长度为m的子串的hash值，共需要计算n-m+1次
    //3.比较每个子串和模式串的hash值，如果hash值不同，必然不匹配
    //4.如果hash值相同，还需要使用朴素算法再次判断
    //在hash较好的情况下，可以做到O(n+m)
    public int strStr(String haystack, String needle) {
        int b = 131, p = (int) 1e9 + 7;// 10^9+7 是一个质数
        int n = haystack.length();
        int m = needle.length();

        // 模板：预处理前缀Hash
        long[] H = new long[n + 1];

        //a=1,b=2,z=26
        for (int i = 1; i <= n; i++) {
            //乘进制加末尾
            H[i] = (H[i - 1] * b + (haystack.charAt(i - 1) - 'a' + 1)) % p;
        }
        long Hneedle = 0; //模式串的hash值
        long powBM = 1; //b的m次方 也可以用数组存
        for (char ch : needle.toCharArray()) {
            Hneedle = (Hneedle * b + (ch - 'a' + 1)) % p;
            powBM = powBM * b % p;
        }

        //n-m+1个模式串
        for (int l = 1; l <= n - m + 1; l++) {
            int r = l + m - 1;
            //s[l..r]的Hash值==needle的Hash值， 做了一次+p %p保证结果非负
            if (((H[r] - H[l - 1] * powBM) % p + p) % p == Hneedle
                    && haystack.substring(l - 1, l - 1 + m).equals(needle)) {
                //C++ haystack.substr(l - 1, m) == needle
                return l - 1;
            }
        }
        return -1;
    }

    //KMP O(n+m)
    //next[i]表示"t中以i结尾的非前缀子串"与"t的前缀"能够匹配的最长长度
    //next告诉我们下一个应该比较什么t
    public int strStr2(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        //分别读取文本串和模式串的长度
        int n = haystack.length(),m = needle.length();
        // 文本串和模式串前面都加空格，使其下标从 1 开始
        haystack = " " + haystack;
        needle = " " + needle;

        char[] s = haystack.toCharArray();
        char[] t = needle.toCharArray();

        // 构建 next 数组，数组长度为模式串的长度（next 数组是和模式串相关的）
        int[] next = new int[m+1];
        next[1] =0;
        // 构造过程 i = 2，j = 0 开始，i 小于等于模式串长度 【构造 i 从 2 开始】
        for (int i = 2, j = 0; i <= m; i++) {
            // 匹配不成功的话，j = next(j)
            while (j > 0 && t[i] != t[j + 1]) j = next[j];
            // 匹配成功的话，先让 j++
            if (t[i] == t[j + 1]) j++;
            // 更新 next[i]，结束本次循环，i++
            next[i] = j;
        }
        // 匹配过程，i = 1，j = 0 开始，i 小于等于文本串长度 【匹配 i 从 1 开始】
        for (int i = 1, j = 0; i <= n; i++) {
            // 匹配不成功 j = next(j)
            while (j > 0 && s[i] != t[j + 1]) j = next[j];
            // 匹配成功的话，先让 j++，结束本次循环后 i++
            if (s[i] == t[j + 1]) j++;
            // 整一段匹配成功，直接返回下标
            if (j == m) return i - m;
        }
        return -1;
    }
    //朴素解法
    public int strStr3(String ss, String pp) {
        int n = ss.length(), m = pp.length();
        char[] s = ss.toCharArray(), p = pp.toCharArray();
        // 枚举原串的「发起点」
        for (int i = 0; i <= n - m; i++) {
            // 从原串的「发起点」和匹配串的「首位」开始，尝试匹配
            int a = i, b = 0;
            while (b < m && s[a] == p[b]) {
                a++;
                b++;
            }
            // 如果能够完全匹配，返回原串的「发起点」下标
            if (b == m) return i;
        }
        return -1;
    }
}
