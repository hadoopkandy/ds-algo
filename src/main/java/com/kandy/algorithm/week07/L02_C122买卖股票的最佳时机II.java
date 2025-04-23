package com.kandy.algorithm.week07;

import java.util.Arrays;

/**
 DP需要维护的状态：i:天数 j:持仓（0或1）有没有股票  k:交易次数 l:是否在冷冻期（0或1）

 f(i,j) 表示第i天结束时，持有j股股票(0或1)的最大收益
 决策：买、卖、啥也不干
 f[i][1] = max(f[i][1], f[i - 1][0] - prices[i]) 买 i-1天没股票，交出prices[i]的现金买股票，获得1的仓位
 f[i][0] = max(f[i][0], f[i - 1][1] + prices[i]) 卖 i-1天有股票，兑现获得prices[i]的现金，仓位变成0
 f[i][j] = max(f[i][j], f[i - 1][j])  啥也不干，只有时间推移


 本题：交易次数无限制 k = +infinity
 f[i][0]的来源：1.f[i - 1][1] + prices[i] 大约是 f[i - 1][0] - prices[i-1] + prices[i]  2.f[i - 1][0]
 所以贪心的做法是只比较  prices[i] - prices[i-1] 是否大于0 如果大于0,也就是prices[i] > prices[i - 1] 就累加答案
 */
public class L02_C122买卖股票的最佳时机II {
    public int maxProfit(int[] pricesInput) {
        int n = pricesInput.length;

        // 0.Move index to 1 based 下标变成从1开始
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
        f[0][0] = 0; //第0天 没有股票 收益是0



        //2.Look over all states
        for (int i = 1; i <= n; i++) {
            f[i][1] = Math.max(f[i][1], f[i - 1][0] - prices[i]); //买 i-1天没股票，交出prices[i]的现金买股票，获得1的仓位
            f[i][0] = Math.max(f[i][0], f[i - 1][1] + prices[i]);//卖 i-1天有股票，兑现获得prices[i]的现金，仓位变成0
            for (int j = 0; j < 2; j++){
                //3. copy decisions
                f[i][j] = Math.max(f[i][j], f[i - 1][j]); //啥也不干，只有时间推移
            }
        }
        //4. return target
        return f[n][0];
    }

    //滚动数组
    public int maxProfit2(int[] pricesInput) {
        int n = pricesInput.length;

        // 0.Move index to 1 based
        int[] prices = new int[n + 1];
        prices[0] = 0;
        for (int i = 1; i <= n; i++) prices[i] = pricesInput[i - 1];

        //1.Define f initialize -oo
        int[][] f = new int[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j <= 1; j++) {
                f[i][j] = -(int) 1e9;
            }
        }
        f[0][0] = 0;

        //2.Look over all states
        for (int i = 1; i <= n; i++) {
            f[i & 1][0] = f[i & 1][1] = -(int) 1e9;
            f[i & 1][1] = Math.max(f[i & 1][1], f[i - 1 & 1][0] - prices[i]); //买 i-1天没股票，交出prices[i]的现金买股票，获得1的仓位
            f[i & 1][0] = Math.max(f[i & 1][0], f[i - 1 & 1][1] + prices[i]);//卖 i-1天有股票，兑现获得prices[i]的现金，仓位变成0
            for (int j = 0; j < 2; j++)
                //3. copy decisions
                f[i & 1][j] = Math.max(f[i & 1][j], f[i - 1 & 1][j]); //啥也不干，只有时间推移

            print(f,i);
        }
        //4. return target
        return f[n & 1][0];
    }

    public static void main(String[] args) {
        int[] prices  = new int []{7,1,5,3,6,4};
        L02_C122买卖股票的最佳时机II lc = new L02_C122买卖股票的最佳时机II();
        System.out.println(lc.maxProfit2(prices));
    }
    public  void print(int[][] f,int i){
        System.out.println("i=" + i);
        for(int[] ff : f ){
            System.out.println(Arrays.toString(ff));
        }
    }
}
