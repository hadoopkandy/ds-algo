package com.kandy.algorithm.week09.homework;

import java.util.HashMap;
import java.util.Map;

public class LC205同构字符串 {
    public boolean isIsomorphic(String s, String t) {
        //<s,t>哈希映射
        Map<Character, Character> s2t = new HashMap<>();
        //<t,s>哈希映射
        Map<Character, Character> t2s = new HashMap<>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                return false;
            }
            s2t.put(x, y);
            t2s.put(y, x);
        }
        //遍历结束没有冲突
        return true;
    }
}
