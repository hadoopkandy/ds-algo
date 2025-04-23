package com.kandy.algorithm.week07;

/**
 * 每次交易含手续费
 * 在买的时候交手续费
 */
public class L05_C714买卖股票的最佳时期含手续费 {
    public int maxProfit(int[] pricesInput, int fee) {
        int n = pricesInput.length;

        // 0.Move index to 1 based
        int[] prices = new int[n + 1];
        prices[0] = 0;
        for (int i = 1; i <= n; i++) prices[i] = pricesInput[i - 1];

        //1.Define f initialize -oo
        int[][] f = new int[n + 1][2];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= 1; j++) {
                f[i][j] = -(int) 1e9;
            }
        }
        f[0][0] = 0;

        //2.Look over all states
        for (int i = 1; i <= n; i++) {
            f[i][1] = Math.max(f[i][1], f[i - 1][0] - prices[i] - fee); //买 i-1天没股票，交出prices[i]的现金买股票，获得1的仓位
            f[i][0] = Math.max(f[i][0], f[i - 1][1] + prices[i]);//卖 i-1天有股票，兑现获得prices[i]的现金，仓位变成0
            for (int j = 0; j < 2; j++)
                //3. copy decisions
                f[i][j] = Math.max(f[i][j], f[i - 1][j]); //啥也不干，只有时间推移
        }
        //4. return target
        return f[n][0];
    }
}
