package com.kandy.algorithm.week09.homework;

public class LC686重复叠加字符串匹配 {
    public int repeatedStringMatch(String a, String b) {
        boolean[] exist = new boolean[26];
        for (char ch : a.toCharArray()) {
            exist[ch - 'a'] = true;
        }

        for (char ch : b.toCharArray()) {
            //b出现了a中没有的字符,返回-1
            if (!exist[ch - 'a']) {
                return -1;
            }
        }
        //至少将 a 复制长度大于等于 b 的长度才可能成为子串
        int ret = b.length() / a.length();
        StringBuilder sb = new StringBuilder(repeat(a, ret));
        for (int i = 0; i < 3; i++) {
            //contains等价于strStr(sb.toString(), b) != -1
            if (sb.toString().contains(b)) {
                return ret + i;
            }
            sb.append(a);
        }
        return -1;
    }

    private String repeat(String str, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

}
