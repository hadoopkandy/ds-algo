package com.kandy.algorithm.week04.homework;

import java.util.TreeMap;

/**
 * 实现思路和优选队列差不多，主要是将PriorityQueue换成TreeMap、TreeSet均可
 */
public class LC239滑动窗口最大值 {
    public int[] maxSlidingWindow1(int[] nums, int k) {
        //<关键码,下标>
        TreeMap<Integer, Integer> treeMap = new TreeMap<>((o1, o2) -> Integer.compare(o2, o1));
        int n = nums.length;
        // [关键码，下标]
        int[] ans = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            //pair<值，下标>
            treeMap.put(nums[i], i);
            //[0,k-1] 当i>=k-1时需要更新答案
            if (i >= k - 1) {
                // [i-k+1, i] 这一段的max
                //懒惰删除 延迟到当未删除的值会影响答案时再进行，检查堆顶下标是否在窗口内
                while (treeMap.firstEntry().getValue() <= i - k) treeMap.pollFirstEntry();
                ans[i - k + 1] = treeMap.firstKey();
            }
        }
        return ans;
    }
}
