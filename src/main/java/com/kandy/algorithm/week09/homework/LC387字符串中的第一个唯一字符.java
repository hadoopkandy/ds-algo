package com.kandy.algorithm.week09.homework;

import java.util.HashMap;
import java.util.Map;

public class LC387字符串中的第一个唯一字符 {
    public int firstUniqChar(String s) {
        //第一次遍历，统计每个字符的次数
        Map<Character, Integer> frequency = new HashMap<Character, Integer>();
        for (char ch : s.toCharArray()) {
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        //遍历每个字符，如果它的次数等于1，则返回发的索引
        for (int i = 0; i < s.length(); ++i) {
            if (frequency.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
}
