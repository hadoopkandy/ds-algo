package com.kandy.algorithm.week03;

import java.util.PriorityQueue;

/**
 * 239. 滑动窗口最大值
 * 优先队列解法
 */
public class L13_C239滑动窗口最大值 {

    public int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        // [关键码，下标]
        PriorityQueue<int[]> q = new PriorityQueue<>(n, (a, b) -> b[0] - a[0]);
        int[] ans = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            //pair<值，下标>
            q.add(new int[]{nums[i], i});
            //[0,k-1] 当i>=k-1时需要更新答案
            if (i >= k - 1) {
                // [i-k+1, i] 这一段的max
                //懒惰删除 延迟到当未删除的值会影响答案时再进行，检查堆顶下标是否在窗口内
                while (q.peek()[1] <= i - k) q.poll();
                //注意这里下标映射 i=k-1时 i-k+1=0  i=n-1 时 i-k+1=n-k
                ans[i - k + 1] = q.peek()[0];
            }
        }
        return ans;
    }

}
