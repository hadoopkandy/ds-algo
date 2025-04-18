package com.kandy.algorithm.week06;

/**
 * f(i)表示以i为结尾的最大子序列和
 * f[i] = max(f[i - 1] + nums[i], nums[i])
 */
public class L10_C53最大子数组和 {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int f[] = new int[n];//f[i]表示以i为结尾的最大子数组和
        f[0] = nums[0];
        for (int i = 1; i < n; i++)
            //f[i] = Math.max(f[i - 1],0) + nums[i];
            //f[i - 1] 如果是正数 取f[i - 1] + nums[i],f[i - 1] 如果是负数，则结果取nums[i]本身
            f[i] = Math.max(f[i - 1] + nums[i], nums[i]);
        int ans = f[0];
        for (int i = 1; i < n; i++)
            ans = Math.max(ans, f[i]);
        return ans;
    }
}
