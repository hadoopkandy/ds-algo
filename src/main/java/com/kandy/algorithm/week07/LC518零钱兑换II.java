package com.kandy.algorithm.week07;

import java.util.Arrays;

/**
 * 零钱兑换其实就是完全背包模型
 * 物品：零钱  体积：面值   价值=1 求min
 */
public class LC518零钱兑换II {
    public int change(int amount, int[] coinsInput) {
        int n = coinsInput.length;
        // Move to 1~n
        int[] coins = new int[n + 1];
        for (int i = 1; i <= n; i++) coins[i] = coinsInput[i - 1];

        int[] f = new int[amount + 1];
        Arrays.fill(f, 0); //计数不合法是0
        f[0] = 1;//初始1
        for (int i = 1; i <=n; i++) { //遍历物品 coins
            for (int j = coins[i]; j <= amount; j++) //正序遍历体积amount
                f[j] += f[j - coins[i]]; //方案数+=  可行性|| 最优解max min
        }
        return f[amount];
    }
}
