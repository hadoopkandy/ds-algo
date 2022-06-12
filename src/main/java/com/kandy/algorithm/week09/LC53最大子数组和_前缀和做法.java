package com.kandy.algorithm.week09;

public class LC53最大子数组和_前缀和做法 {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] s = new int[n + 1];// 0-n
        int[] preMin = new int[n + 1]; //0-n

        s[0] = 0;
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];

        preMin[0] = 0;
        for (int i = 1; i <= n; i++) preMin[i] = Math.min(preMin[i - 1], s[i]);

        int ans = -(int) 1e9;
        for (int i = 1; i <= n; i++) {
            //在i之前(0~i-1里面)找一个j，使得s[i]-s[j]最大
            ans = Math.max(ans, s[i] - preMin[i - 1]);
        }
        return ans;
    }
}
