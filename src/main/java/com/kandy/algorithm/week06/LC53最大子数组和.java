package com.kandy.algorithm.week06;

public class LC53最大子数组和 {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int f[] = new int[n];//f[i]表示以i为结尾的最大子数组和
        f[0] = nums[0];
        for (int i = 1; i < n; i++)
            f[i] = Math.max(f[i - 1] + nums[i], nums[i]);
        int ans = f[0];
        for (int i = 1; i < n; i++)
            ans = Math.max(ans, f[i]);
        return ans;
    }
}
