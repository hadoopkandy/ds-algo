package com.kandy.algorithm.week06;

import java.util.Arrays;


/**
 * 贪心-邻项交换
 * [5,8] [1,7]
 */
public class LC1665完成所有任务的最少初始能量 {
    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> (a[0] - a[1]) - (b[0] - b[1]));
        int ans = 0;
        for (int i = tasks.length - 1; i >= 0; i--) {
            ans = Math.max(tasks[i][1], ans + tasks[i][0]);
        }
        return ans;
    }
}
