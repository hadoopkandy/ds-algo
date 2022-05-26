package com.kandy.algorithm.week06;

/**
 * 状态：
 * i chosen （朴素搜索-超出内存限制）
 * 选了几个数，结尾是哪个
 *
 * f(i) 表示前i个数构成的以a[i]为结尾的最长上升子序列的长度
 *  f[i] = Math.max(f[i], f[j] + 1);
 */
public class LC300最长递增子序列 {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int f[] = new int[n]; // 0~n-1 f[i] 表示以i 结尾的最长长度
        for (int i = 0; i < n; i++) f[i] = 1; //每个数本身作为一个序列，长度是1
        for (int i = 1; i < n; i++)
            for (int j = 0; j < i; j++)  //在i 之前选一个j 满足递增关系
                if (nums[j] < nums[i])
                    f[i] = Math.max(f[i], f[j] + 1);
        int ans = 0;
        for (int i = 0; i < n; i++)
            ans = Math.max(ans, f[i]);
        return ans;
    }

    //打印路径
    public int lengthOfLI2(int[] nums) {
        int n = nums.length;
        int f[] = new int[n]; // 0~n-1 f[i] 表示以i 结尾的最长长度
        int pre[] = new int[n]; //记录每个f[i]从哪来的 转移路径

        for (int i = 0; i < n; i++) {
            f[i] = 1; //每个数本身作为一个序列，长度是1
            pre[i] = -1; //没有来源
        }
        for (int i = 1; i < n; i++)
            for (int j = 0; j < i; j++)  //在i 之前选一个j 满足递增关系
                if (nums[j] < nums[i] && f[i] < f[j] + 1) {
                    f[i] = f[j] + 1;
                    pre[i] = j;
                }

        int ans = 0;
        int end = -1;
        for (int i = 0; i < n; i++)
            if (f[i] > ans) {
                ans = f[i];
                end = i;
            }
        print(nums, pre, end);
        return ans;
    }

    void print(int[] nums, int[] pre, int i) {
        //print i 之前 先 print j
        if (pre[i] != -1) print(nums, pre, pre[i]);
        System.out.println(nums[i]);
    }
}
