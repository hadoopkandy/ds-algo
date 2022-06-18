package com.kandy.algorithm.week09.homework;

import java.util.Arrays;

/**
 * 若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词
 */
public class LC242有效的字母异位词 {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }
}
