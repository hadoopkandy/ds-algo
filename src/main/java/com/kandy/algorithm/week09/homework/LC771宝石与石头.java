package com.kandy.algorithm.week09.homework;

import java.util.HashSet;
import java.util.Set;

public class LC771宝石与石头 {
    public int numJewelsInStones(String jewels, String stones) {
        int ans = 0;
        //宝石
        Set<Character> jewelsSet = new HashSet();
        for (char ch : jewels.toCharArray()) {
            jewelsSet.add(ch);
        }
        //判断每个石头字符，是否在宝石set中出现过
        for (char ch : stones.toCharArray()) {
            if (jewelsSet.contains(ch)) {
                ans++;
            }
        }
        return ans;
    }
}
