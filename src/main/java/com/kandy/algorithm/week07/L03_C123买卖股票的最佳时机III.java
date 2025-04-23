package com.kandy.algorithm.week07;

/**
 * 限定交易次数 k=2
 */
public class L03_C123买卖股票的最佳时机III {
    public int maxProfit(int[] prices) {
        return maxProfit(2, prices);
    }

    public int maxProfit(int c, int[] pricesInput) {
        int n = pricesInput.length;

        // Move prices from [0..n-1] to [1..n] 下标变成从1开始
        int[] prices = new int[n + 1];
        prices[0] = 0;
        for (int i = 1; i <= n; i++) prices[i] = pricesInput[i - 1];

        //0. Initialize DP array
        int[][][] f = new int[n + 1][2][c + 1];
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= 1; j++)
                for (int k = 0; k <= c; k++)
                    f[i][j][k] = -(int) 1e9;

        f[0][0][0] = 0;

        // 2.Look over all states
        for (int i = 1; i <= n; i++)
            for (int j = 0; j <= 1; j++)
                for (int k = 0; k <= c; k++) {
                    if (k > 0)
                        f[i][1][k] = Math.max(f[i][1][k], f[i - 1][0][k - 1] - prices[i]); // 买 i-1天没有股票 交易了k-1次 交钱获得股票资产 仓位增加 累加一次交易次数

                    //3. copy decisions
                    f[i][0][k] = Math.max(f[i][0][k], f[i - 1][1][k] + prices[i]);//卖次数不用变 收入增加
                    f[i][j][k] = Math.max(f[i][j][k], f[i - 1][j][k]); //啥也不干 只有时间变化
                }

        //4. Calculate the target
        int ans = -(int) 1e9;
        for (int k = 0; k <= c; k++) ans = Math.max(ans, f[n][0][k]);
        return ans;
    }
}
