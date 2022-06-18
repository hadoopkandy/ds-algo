package com.kandy.algorithm.week09.homework;

public class LC14最长公共前缀 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        String ans = strs[0];//初始化最长公共前缀
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < ans.length() && j < strs[i].length(); j++) {
                if (ans.charAt(j) != strs[i].charAt(j))
                    break;
            }
            ans = ans.substring(0, j);//更新最长公共前缀
            if (ans.equals(""))
                return ans;
        }
        return ans;
    }
}
