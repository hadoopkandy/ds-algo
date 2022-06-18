package com.kandy.algorithm.week09;

public class LC125验证回文串 {
    //方法一：双指针 O(n)时间 O(n)空间
    public boolean isPalindrome(String s) {
        StringBuilder t = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z')) t.append(ch);
            else if (ch >= 'A' && ch <= 'Z') t.append((char) (ch - 'A' + 'a')); //大小变小写
        }
        int l = 0, r = t.length() - 1;
        while (l < r) {
            if (t.charAt(l) != t.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }

    //方法二：双指针 O(n)时间 O(1)空间
    public boolean isPalindrome2(String s) {
        int l = getNext(s, 0), r = getPre(s, s.length() - 1);
        while (l < r) {
            if (!equalsIgnoreCase(s.charAt(l), s.charAt(r))) return false;
            l = getNext(s, l + 1);
            r = getPre(s, r - 1);
        }
        return true;
    }

    //是否是数字或字母
    boolean isDigitOrLetter(char ch) {
        return ch >= '0' && ch <= '9' || ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z';
    }

    //返回字母或数字的位置 从前往后遍历
    int getNext(String s, int i) {
        while (i < s.length() && !isDigitOrLetter(s.charAt(i))) i++;
        return i;
    }

    //返回字母或数字的位置  从后往前遍历
    int getPre(String s, int i) {
        while (i >= 0 && !isDigitOrLetter(s.charAt(i))) i--;
        return i;
    }

    boolean equalsIgnoreCase(char ch1, char ch2) {
        if (ch1 >= 'A' && ch1 <= 'Z') ch1 = (char) (ch1 - 'A' + 'a');
        if (ch2 >= 'A' && ch2 <= 'Z') ch2 = (char) (ch2 - 'A' + 'a');
        return ch1 == ch2;
    }


}
