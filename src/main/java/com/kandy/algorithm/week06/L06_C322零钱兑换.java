package com.kandy.algorithm.week06;

import java.util.Arrays;

/**
  本题的状态：剩余金额 已用硬币枚数
  贪心：每次都选尽量大的面值  coins=[10,9,1] amount=18 贪心解：10 1 (8个1) 正解：9 9 面值成倍数时，贪心算法成立

  状态转移方程：
  opt[i]表示凑成金额i所需的最少硬币数
  opt[i] = Math.min(opt[i], opt[i - coins[j]] + 1)
 */
public class L06_C322零钱兑换 {
    //递推实现
    public int coinChange(int[] coins, int amount) {
        int INF = (int) 1e9;
        int[] opt = new int[amount + 1];
        opt[0] = 0;
        //i 阶段（线程增长）
        for (int i = 1; i <= amount; i++) {
            opt[i] = INF; //opt[i] 状态（具有最优子结构）
            for (int j = 0; j < coins.length; j++)
                if (i - coins[j] >= 0)
                    opt[i] = Math.min(opt[i], opt[i - coins[j]] + 1); //状态转移方程   opt[i - coins[j]] 重叠子问题
        }
        if (opt[amount] >= INF) opt[amount] = -1;
        return opt[amount];
    }

    //记忆化搜索(递归实现)
    public int coinChang2(int[] coins, int amount) {
        this.coins = coins;
        this.opt = new int[amount + 1];
        Arrays.fill(opt, -1);
        int ans = calc(amount);
        if (ans >= (int) 1e9) ans = -1;
        return ans;
    }

    private int calc(int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return (int) 1e9;
        }
        if (opt[amount] != -1) {
            return opt[amount]; //算过了，直接返回
        }
        opt[amount] = (int) 1e9;
        for (int coin : coins) {
            opt[amount] = Math.min(opt[amount], calc(amount - coin) + 1);
        }
        return opt[amount];
    }

    int[] coins;
    int[] opt;

    public static void main(String[] args) {
        L06_C322零钱兑换 lc = new L06_C322零钱兑换();
        int[] coins = new int[]{10, 9, 1};
        System.out.println(lc.coinChang2(coins, 18));
    }
}
