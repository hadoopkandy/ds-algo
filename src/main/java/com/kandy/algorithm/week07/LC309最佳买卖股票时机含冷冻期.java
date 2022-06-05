package com.kandy.algorithm.week07;

/**
 * 含有交易冷冻期
 * 思考1：与贪心的对比
 * 思考2：状态与决策的平衡选择
 * 思考3：状态转移方程的两种写法 "谁能走到我" "我能走到谁"
 * 思考4：空间的优化 "滚动数组"
 * 思考5：d天冷冻期
 * 思考6：最多持仓t股股票
 */
public class LC309最佳买卖股票时机含冷冻期 {
    public int maxProfit(int[] pricesInput) {
        int n = pricesInput.length;

        // 0.Move index to 1 based
        int[] prices = new int[n + 1];
        prices[0] = 0;
        for (int i = 1; i <= n; i++) prices[i] = pricesInput[i - 1];

        //1.Define f initialize -oo
        int[][][] f = new int[n + 1][2][2];

        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= 1; j++)
                for (int l = 0; l <= 1; l++)
                    f[i][j][l] = -1000000000;
        f[0][0][0] = 0;

        //2.Look over all states 前面点怎么走过来
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 2; j++)
                for (int l = 0; l <= 1; l++) {
                    //3. copy decisions
                    f[i][1][0] = Math.max(f[i][1][0], f[i - 1][0][0] - prices[i]); //买 i-1天没股票，交出prices[i]的现金买股票，获得1的仓位(买之前没有冷冻，买之后也没有冷冻)
                    f[i][0][1] = Math.max(f[i][0][1], f[i - 1][1][0] + prices[i]);//卖 i-1天有股票，兑现获得prices[i]的现金，仓位变成0 (卖之前没有冷冻，买之后处于冷冻,具有一天的冷冻期)
                    f[i][j][0] = Math.max(f[i][j][0], f[i - 1][j][l]); //啥也不干，只有时间推移 (无论之前是否处于冷冻期，之后必然解除冷冻期)
                }
        }
        //4. return target
        return Math.max(f[n][0][1], f[n][0][0]);
    }

    //列表法 考虑往后走 注意i变量的取值范围： 0到n-1
    public int maxProfit2(int[] pricesInput) {
        int n = pricesInput.length;

        // 0.Move index to 1 based
        int[] prices = new int[n + 1];
        prices[0] = 0;
        for (int i = 1; i <= n; i++) prices[i] = pricesInput[i - 1];

        //1.Define f initialize -oo
        int[][][] f = new int[n + 1][2][2];

        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= 1; j++)
                for (int l = 0; l <= 1; l++)
                    f[i][j][l] = -1000000000;
        f[0][0][0] = 0;

        //2.Look over all states  从当前点往后怎么走
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++)
                for (int l = 0; l <= 1; l++) {
                    if (f[i][j][l] == -1000000000) continue; //状态不合法
                    if (j == 0 && l == 0)  //买的条件：没有股票仓位=0 没有冷冻
                        f[i + 1][1][0] = Math.max(f[i + 1][1][0], f[i][j][l] - prices[i + 1]); //买之后没有冷冻期 prices[i + 1]是代价

                    if (j == 1 && l == 0) //卖的条件：有股票 仓位=1 没有冷冻
                        f[i + 1][0][1] = Math.max(f[i + 1][0][1], f[i][j][l] + prices[i + 1]);//卖 冷冻一天

                    f[i + 1][j][0] = Math.max(f[i + 1][j][0], f[i][j][l]); //啥也不干，没有条件，只有时间推移 (无论之前是否处于冷冻期，之后必然解除冷冻期)
                }
        }
        //4. return target
        return Math.max(f[n][0][1], f[n][0][0]);
    }

    //空间优化：滚动数组  & 1 等价于%2 奇数变1 偶数变0   &优先级小于+ -符号
    public int maxProfit3(int[] pricesInput) {
        int n = pricesInput.length;

        // 0.Move index to 1 based
        int[] prices = new int[n + 1];
        prices[0] = 0;
        for (int i = 1; i <= n; i++) prices[i] = pricesInput[i - 1];

        //1.Define f initialize -oo
        int[][][] f = new int[2][2][2];

        for (int i = 0; i <= 1; i++)
            for (int j = 0; j <= 1; j++)
                for (int l = 0; l <= 1; l++)
                    f[i][j][l] = -1000000000;
        f[0][0][0] = 0;

        //2.Look over all states  从当前点往后怎么走
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++)
                for (int l = 0; l <= 1; l++) {
                    if (f[i & 1][j][l] == -1000000000) continue; //状态不合法，减少无效计算
                    if (j == 0 && l == 0)  //没有股票 仓位=0 没有冷冻
                        f[i + 1 & 1][1][0] = Math.max(f[i + 1 & 1][1][0], f[i & 1][j][l] - prices[i + 1]); //买之后没有冷冻期 prices[i + 1]是代价

                    if (j == 1 && l == 0) //有股票 仓位=1 没有冷冻
                        f[i + 1 & 1][0][1] = Math.max(f[i + 1 & 1][0][1], f[i & 1][j][l] + prices[i + 1]);//卖 冷冻一天

                    f[i + 1 & 1][j][0] = Math.max(f[i + 1 & 1][j][0], f[i & 1][j][l]); //啥也不干，没有条件，只有时间推移 (无论之前是否处于冷冻期，之后必然解除冷冻期)

                    f[i & 1][j][l] = -1000000000; //第i行用过了，重新初始化
                }
        }
        //4. return target
        return Math.max(f[n & 1][0][1], f[n & 1][0][0]);
    }
}
