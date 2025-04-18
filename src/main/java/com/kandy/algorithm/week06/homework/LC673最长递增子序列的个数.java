package com.kandy.algorithm.week06.homework;


/**
 * https://leetcode.cn/problems/number-of-longest-increasing-subsequence/
 */
public class LC673最长递增子序列的个数 {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int f[] = new int[n]; // 0~n-1 f[i] 表示以i 结尾的LIS的长度
        int cnt[] = new int[n];//0~n-1 f[i] 表示以i 结尾的LIS的个数
        for (int i = 0; i < n; i++) {
            f[i] = 1; //每个数本身作为一个序列，长度是1
            cnt[i] = 1; //每个数本身作为一个序列，个数是1
        }
        for (int i = 1; i < n; i++)
            for (int j = 0; j < i; j++)  //在i 之前选一个j 满足递增关系
                if (nums[j] < nums[i]) {
                    if (f[j] + 1 > f[i]) {
                        f[i] = f[j] + 1;
                        cnt[i] = cnt[j]; // 重置计数
                    } else if (f[j] + 1 == f[i]) {
                        cnt[i] += cnt[j];
                    }
                }

        int max = 0;
        int ans = 0;
        for (int i = 0; i < n; i++)
            max = Math.max(max, f[i]); //计算最长长度
        for (int i = 0; i < n; i++)
            if (f[i] == max) ans += cnt[i]; //计算最长长度的次数
        return ans;
    }
}
