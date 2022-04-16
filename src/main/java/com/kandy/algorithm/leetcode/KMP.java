package com.kandy.algorithm.leetcode;

import java.util.Arrays;

public class KMP {
    /**
     * 求前缀表
     * next数组的不同实现方式：右移、减1
     * 例如  0    1  0  1  2  0
     * 右移  -1   0  1  0  1  2
     * 减1  -1    0 -1  0  1  -1
     * 遇见冲突向前回退
     *
     * @param s 模式串
     * @return
     */
    public static int[] getNext(char[] s) {
        //初始化
        int[] next = new int[s.length];
        int j = 0;//j指向前缀末尾位置
        next[0] = 0;

        //i指向后缀末尾位置
        for (int i = 1; i < s.length; i++) {
            //前后缀不相同
            while (j > 0 && s[i] != s[j])
                j = next[j - 1];

            //前后缀相同
            if (s[i] == s[j]) j++;

            //更新next
            next[i] = j;
        }
        return next;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getNext("aabaaf".toCharArray())));
    }
}
