package com.kandy.algorithm.week09.homework;


public class LC709转换成小写字母 {
    public String toLowerCase(String s) {
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') sb.append((char) (ch - 'A' + 'a'));
            else sb.append(ch);
        }
        return sb.toString();
    }

}
