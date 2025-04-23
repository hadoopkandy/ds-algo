package com.kandy.algorithm.week07;

/**
 * 限定交易次数 k=1
 */
public class L01_C121买卖股票的最佳时机 {
    // 选择j i  j这天买入 i 这天卖出
    // 使得price[i] - price[j]最大
    // for i  for j  在price[i]之前选一个最小的price[j]即为答案
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] dp = new int[n];
        dp[0] = 0;
        int[] preMin = new int[n];
        preMin[0] = prices[0];
        for (int i = 1; i < n; i++) {
            preMin[i] = Math.min(preMin[i-1], prices[i]);
        }
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], prices[i] - preMin[i]);
        }
        return dp[n - 1];
    }
}
