package com.kandy.algorithm.week06;

import java.util.Arrays;

/**
 * 贪心-邻项交换
 贪心策略：actual - minimum 升序排序,以此顺序完成任务
 策略说明:actual越小越往前排  minimum越大(减的越多,整体越小)越往前排，以此顺序完成任务
 */
public class L05_C1665完成所有任务的最少初始能量 {
    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> (a[0] - a[1]) - (b[0] - b[1]));
        int ans = 0;
        for (int i = tasks.length - 1; i >= 0; i--) {
            //本身门槛比较高
            ans = Math.max(tasks[i][1], ans + tasks[i][0]);
        }
        return ans;
    }
}
