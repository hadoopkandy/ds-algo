package com.kandy.algorithm.week07;

import java.util.Deque;
import java.util.LinkedList;

public class LC918环形子数组的最大和 {
    //max(数组的最大子序和,total_sum-数组的最小子序和)
    public int maxSubarraySumCircular(int[] numsInput) {
        int n = numsInput.length;
        //将下标变成从1开始
        int[] nums = new int[n + 1];
        nums[0] = 0;
        for (int i = 1; i <= n; i++) nums[i] = numsInput[i - 1];


        // 前缀和
        int[] sum = new int[n + 1];
        sum[0] = 0;
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + nums[i];

        // 最大子段和 ans
        int temp = (int) 1e9;
        int ans = -(int) 1e9;
        for (int i = 1; i <= n; i++) {
            temp = Math.min(temp, sum[i - 1]);
            ans = Math.max(ans, sum[i] - temp);
        }

        // 最小子段和 不能全取，如果全取 头尾就为空
        temp = -(int) 1e9;
        int ansMin = (int) 1e9;

        // 不能把1~n全扣掉（不能用sum[n]-sum[0]）
        for (int i = 2; i <= n; i++) {
            temp = Math.max(temp, sum[i - 1]);
            ansMin = Math.min(ansMin, sum[i] - temp);
        }

        // 最小子段和不能全取1~n，所以sum[n]只能减掉sum[1..n-1]
        for (int i = 1; i < n; i++) ansMin = Math.min(ansMin, sum[i]);

        // 总和 - 最小子段和 = 头尾各取一段最大
        ans = Math.max(ans, sum[n] - ansMin);
        return ans;
    }

    //把数组复制一倍，接在后面
    public int maxSubarraySumCircular2(int[] numsInput) {
        int n = numsInput.length;

        //将下标变成从1开始
        int[] nums = new int[n + 1];
        nums[0] = 0;
        for (int i = 1; i <= n; i++) nums[i] = numsInput[i - 1];

        int[] ss = new int[2 * n + 1];
        ss[0] = 0;//这句话不写也可以
        //前一半前缀和
        for (int i = 1; i <= n; i++)
            ss[i] = ss[i - 1] + nums[i];
        //后一半前缀和
        for (int i = n + 1; i <= 2 * n; i++)
            ss[i] = ss[i - 1] + nums[i - n];
        //单调队列
        Deque<Integer> q = new LinkedList<>();
        int ans = -(int) 1e9;
        //i-n<=j<=i-1
        for (int i = 1; i <= 2 * n; i++) {
            //维护队头 队列不空，队头出界 就移除队头
            while (!q.isEmpty() && q.peekFirst() < i - n) q.pollFirst();
            //取队头为最优解
            if (!q.isEmpty()) ans = Math.max(ans, ss[i] - ss[q.peekFirst()]);
            //维护单调性 维护递增队列
            while (!q.isEmpty() && ss[q.peekLast()] >= ss[i]) q.pollLast();
            q.addLast(i);
        }
        return ans;
    }
}
