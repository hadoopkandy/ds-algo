package com.kandy.algorithm.week07;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 设置j<i
 * 拆绝对值
 * 分离ij算式 (分离状态变量和决策变量)
 * 找冗余计算
 * 省掉一层循环扫描(时间复杂度变成O(n))
 */
public class LC1499满足不等式的最大值 {
    public int findMaxValueOfEquation(int[][] points, int k) {
        int ans = -1000000000;
        Deque<Integer> q = new LinkedList<>(); // 双端队列存下标
        for (int i = 0; i < points.length; i++) {
            // 求上界：j<=i-1，下界：x[j]>=x[i]-k
            // 这个范围中 y[j]-x[j] 的最大值
            // 考虑两个选项 j1<j2
            // 写出 j1 比 j2 优的条件
            // y[j1]-x[j1] > y[j2]-x[j2]
            // 1. 队头合法性
            // x[j]: points[q.front()][0]  while x[j] < x[i] -k 删除过期的j
            while (!q.isEmpty() && points[q.peekFirst()][0] < points[i][0] - k) q.pollFirst();
            // 2. 取队头为最优解
            // y[i]: points[i][1]
            // x[i]: points[i][0]
            // ans = max(ans,y[i] + x[i] + y[j] - x[j])更新答案
            //取到y[j] - x[j]最大值的j就是q.front
            if (!q.isEmpty())
                ans = Math.max(ans, points[i][1] + points[i][0] + points[q.peekFirst()][1] - points[q.peekFirst()][0]);
            // 3. 维护队列单调性 y[j]-x[j]递减的队列，队尾插入新选项i
            while (!q.isEmpty() && points[q.peekLast()][1] - points[q.peekLast()][0] <= points[i][1] - points[i][0])
                q.pollLast();
            q.addLast(i);
        }
        return ans;
    }
}
