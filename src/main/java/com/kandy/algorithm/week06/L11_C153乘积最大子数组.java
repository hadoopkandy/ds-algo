package com.kandy.algorithm.week06;

/**
 * fmax[i] fmin[i]表示以i结尾的乘积最大、最小子数组
 * fmax[i] = max(fmax[i - 1] * nums[i], fmin[i - 1] * nums[i], nums[i])
 * fmin[i] = min(fmax[i - 1] * nums[i], fmin[i - 1] * nums[i], nums[i])
 * fmin[i - 1] 越小 *  nums[i] 可能成为最大乘积
 */
public class L11_C153乘积最大子数组 {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        //max 和min一起作为代表，才能满足最优子结构
        //以i 结尾的乘积最大、最小子数组
        int fmax[] = new int[n];
        int fmin[] = new int[n];
        fmax[0] = nums[0];
        fmin[0] = nums[0];
        for (int i = 1; i < n; i++) {
            fmax[i] = Math.max(Math.max(fmax[i - 1] * nums[i], fmin[i - 1] * nums[i]), nums[i]);
            fmin[i] = Math.min(Math.min(fmax[i - 1] * nums[i], fmin[i - 1] * nums[i]), nums[i]);
        }
        int ans = fmax[0];
        for (int i = 1; i < n; i++)
            ans = Math.max(ans, fmax[i]);
        return ans;
    }
}
