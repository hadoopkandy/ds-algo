package com.kandy.algorithm.week01;

import java.util.*;

/**
 * 239. 滑动窗口最大值
 * 单调队列题目代码套路：
 * for 每个元素
 * while (队头过期) 队头出队
 * 取队头为最佳选项 ，计算答案
 * while(队尾与新元素不满足单调性) 队尾出队
 * 新元素出队
 */
public class LC239滑动窗口最大值 {

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
                ans[i - k + 1] = q.peek()[0];
            }
        }
        return ans;
    }

/*
1 3 [-1 -3 5] 3 6 7

时间：expire_time(-1) < expire_time(-3) < expire_time(5)
值大小：-1 < -3 < 5
求max

冗余：一个下标i一个下标j，如果i<j，并且nums[i]<=nums[j]，i是冗余
去除冗余：维护下标（时间）递增、nums递减（>=）的队列
队头最优，随着下标增长，队头expire后，后面的开始逐渐变成最优
*/

    /**
     * 单调队列解法
     * 递减队列，不递减，小的就没用，从左边删除 右边进入
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        //双端队列，存下标（代表时间）
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            //保证队头合法性
            while (!q.isEmpty() && q.getFirst() <= i - k) q.removeFirst();
            //维护队列单调性，插入新的选项
            while (!q.isEmpty() && nums[q.getLast()] <= nums[i]) q.removeLast();
            q.addLast(i);
            //取队头更新答案
            if (i >= k - 1) ans[i - (k - 1)] = nums[q.getFirst()];
        }
        return ans;
    }

    public static void main(String[] args) {
        LC239滑动窗口最大值 sol = new LC239滑动窗口最大值();

        int[] nums1 = new int[]{5, 3, 4, 1};
        System.out.println(Arrays.toString(sol.maxSlidingWindow(nums1, 3)));


        int[] nums2 = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(sol.maxSlidingWindow(nums2, 3)));

        int[] nums3 = new int[]{1};
        System.out.println(Arrays.toString(sol.maxSlidingWindow(nums3, 1)));

    }

}
