package com.kandy.algorithm.week09;

public class LC680验证回文字符串II {
    public boolean validPalindrome(String s) {
        return check(s, 0, s.length() - 1, 1);
    }

    //贪心 + 验证
    boolean check(String s, int l, int r, int times) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                //删左边或删右边
                return times > 0 && (check(s, l + 1, r, 0) || check(s, l, r - 1, 0));
            }
            l++;
            r--;
        }
        return true;
    }
}
