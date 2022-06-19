package com.kandy.algorithm.week09;

public class LC53最大子数组和_前缀和做法 {
    //解法一：前缀和+前缀最小值
    //求出前缀和数组s,枚举右端点i，需要找到一个i之前的一个j使得s[i]-s[j]最大
    //也就是让s[j]最小，在维护一个s的前缀最小值即可
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
    //解法二：贪心
    //只要"和"是正的，就不断向右扩展
    //一旦发现"和"是负的，立即舍弃
    //代码参照week06
}
