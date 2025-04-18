package com.kandy.algorithm.week06;

/**
 * 贪心-决策范围扩展
 */
public class L03_C122买卖股票的最佳时机II {
    public int maxProfit(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++)
            //如果prices[i] > prices[i - 1] 就累加答案
            ans += Math.max(prices[i] - prices[i - 1], 0);
        return ans;
    }
}
