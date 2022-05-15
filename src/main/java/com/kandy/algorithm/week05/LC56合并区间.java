package com.kandy.algorithm.week05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC56合并区间 {
    //双关键字排序贪心解法
    public int[][] merge(int[][] intervals) {
        // 双关键字排序
        Arrays.sort(intervals,
                (a, b) -> { // 两个长度为2的数组比较
                    if (a[0] != b[0]) return a[0] - b[0];
                    return a[1] - b[1];
                });

        // 合并
        // 维护当前覆盖的最远端
        // 判断一个区间是延续？还是新成立一段？
        // 看它的起点是在最远端之后还是之前
        /*[1, 5]  [2, 6] [3, 4] [6, 10]  [11 12]
        [1  5] 当前覆盖的最远端：5
        [2  6] 当前覆盖的最远端：6
        [3  4] 当前覆盖的最远端：6
        [6 10] 当前覆盖的最远端：10

        [11 12]*/
        List<int[]> ans = new ArrayList<>();

        int start = -1; //当前start
        int farthest = -1; //当前start 可以到的最远的end点
        for (int[] interval : intervals) {
            int left = interval[0];
            int right = interval[1];
            if (left <= farthest) { // 延续
                farthest = Math.max(farthest, right);
            } else { // 另起一段
                // 旧的段放进答案
                if (farthest != -1) ans.add(new int[]{start, farthest});
                // 新开一段（只有[start,end]）
                start = left;
                farthest = right;
            }
        }
        if (farthest != -1) {
            ans.add(new int[]{start, farthest});
        }
        return ans.toArray(new int[0][]);
    }

    //关键事件差分解法
    public int[][] merge2(int[][] intervals) {
        /*[1, 5]  [2, 6] [3, 4] [6, 10]  [11 12]
           1 2 3 4 5 6 7 8 9 10 11 12
           1 1 1 1 1
             1 1 1 1 1
               1 1
                     1 1 1 1  1
                                  1 1

           +1        -1
             +1        -1
               +1  -1
                     +1        -1
                               +1 -1
         count: 0
        把从1覆盖到5这个区间，看作2个事件：
        (a) 在1处，有一个事件：开始覆盖（次数+1）
        (b) 在5+1处，有一个事件：结束覆盖（次数-1）
        */
        // 产生2n个事件
        // 时间位置，时间情况(+1/-1)
        List<int[]> events = new ArrayList<>();
        for (int[] interval : intervals) {
            // 差分
            events.add(new int[]{interval[0], 1});
            events.add(new int[]{interval[1] + 1, -1});
        }
        //第一元相同时，用第二元比较，保证了第二元的 -1事件要在+1事件前面
        events.sort((a, b) -> (a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]));
        int count = 0;
        int start = 0;
        List<int[]> ans = new ArrayList<>();
        for (int[] event : events) {
            if (count == 0) // 加之前是0，加之后是非0
                start = event[0];  // 一个段的产生
            count += event[1];
            if (count == 0) // 非零变零，一个段的结束
                ans.add(new int[]{start, event[0] - 1}); //注意差分的时候 +1 这里要 -1减回去
        }
        return ans.toArray(new int[0][]);
    }
/*
把[l,r]区间转化为"l处+1","r+1处-1"两个事件
[[1,3],[4,5]]
1 +1
4 -1
4 +1
5 -1
注意：-1 事件要在 +1事件前面

把[l,r]区间转化为"l处+1","r处-1"两个事件
[[1,4],[4,5]]
1 +1
4 -1
4 +1
5 -1
注意：+1 事件要在 -1事件前面
 */
}
