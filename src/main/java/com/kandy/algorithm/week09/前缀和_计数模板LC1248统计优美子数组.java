package com.kandy.algorithm.week09;

import java.util.HashMap;

public class 前缀和_计数模板LC1248统计优美子数组 {
    //原问题：子段的奇数数量
    //新问题:奇数看作1，偶数看作0,（每个数mod2），统计字段和是k的子段数量
    //sum(l,r)==k  S[r]-S[l-1]==k (l<=r)  ->s[i]-s[j] ==k
    //两数之差：一个是s[r] 一个是s[l-1]
    //需要用一个计数数组或hash map维护S中每个值的个数
    //枚举右端点i，看一下等于s[i]-k的值有几个就行了
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] s = new int[n + 1]; // s[0~n]前缀和数组
        int[] count = new int[n + 1]; //前缀和的取值范围0-n 统计每个前缀和的次数
        // s[0] = 0;
        count[s[0]]++;
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + nums[i - 1] % 2;
            count[s[i]]++;
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            // s[i] - s[j] = k, 求j的数量
            // s[j] = s[i] - k
            if (s[i] - k >= 0) {
                ans += count[s[i] - k];
            }
        }
        return ans;
    }

    //hashmap
    public int numberOfSubarrays2(int[] nums, int k) {
        int n = nums.length;
        int[] s = new int[n + 1]; // s[0~n]前缀和数组

        HashMap<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + nums[i - 1] % 2;
            mp.put(s[i], mp.getOrDefault(s[i], 0) + 1);
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            // s[i] - s[j] = k, 求j的数量
            // s[j] = s[i] - k
            if (mp.containsKey(s[i] - k))
                ans += mp.get(s[i] - k);
        }
        return ans;
    }
}
