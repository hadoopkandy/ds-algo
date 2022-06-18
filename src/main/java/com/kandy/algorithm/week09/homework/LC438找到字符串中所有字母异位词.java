package com.kandy.algorithm.week09.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC438找到字符串中所有字母异位词 {
    //方法一：暴力解法 2s
    public List<Integer> findAnagrams(String s, String p) {
        //如何在异位字符串下进行匹配
        char[] chars = p.toCharArray();
        Arrays.sort(chars);
        List<Integer> ans = new ArrayList<>();

        int n = s.length();
        int m = p.length();
        for (int i = 0; i < n - m + 1; i++) {
            String temp = s.substring(i, i + m);
            char[] chars1 = temp.toCharArray();
            Arrays.sort(chars1);

            if (Arrays.equals(chars, chars1)) {
                ans.add(i);
            }
        }
        return ans;
    }

    //方法一：滑动窗口+数组计数 7ms
    public List<Integer> findAnagrams2(String s, String p) {
        int n = s.length(), m = p.length();
        List<Integer> ans = new ArrayList<>();
        //当字符串s的长度小于字符串p的长度时，字符串s中一定不存在字符串p的异位词
        if (n < m) return ans;
        //由于字符串中的字符全是小写字母，所以可以用长度为26的数组记录字母出现的次数
        int[] pCnt = new int[26];
        int[] sCnt = new int[26];
        //i循环到m-1结束
        for (int i = 0; i < m; i++) {
            pCnt[p.charAt(i) - 'a']++;
            sCnt[s.charAt(i) - 'a']++;
        }
        //s字符串前m个字母频次和p字符串字母频次相等，则找到第一个异位词索引0
        if (Arrays.equals(sCnt, pCnt)) {
            ans.add(0);
        }
        //i从m开始遍历，到n-1结束[m,n)
        for (int i = m; i < n; i++) {
            //去掉一个旧字母
            sCnt[s.charAt(i - m) - 'a']--;
            //加一个新字母，当前窗口[i-m+1,i] 共m个字母
            sCnt[s.charAt(i) - 'a']++;
            if (Arrays.equals(sCnt, pCnt)) {
                ans.add(i - m + 1);
            }
        }
        return ans;
    }

}
