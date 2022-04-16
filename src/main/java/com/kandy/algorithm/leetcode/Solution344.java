package com.kandy.algorithm.leetcode;

/**
 * 344. 反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 */
public class Solution344 {

    public static void reverseString(char[] s) {
        int n = s.length;
        for (int left = 0, right = n - 1; left < right; ++left, --right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
    }

    public static void main(String[] args) {
        String s = "hello";
        char[] ss = s.toCharArray();
        reverseString(ss);
        System.out.println(ss);
        System.out.println(s.toCharArray());
    }
}

