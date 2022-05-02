package com.kandy.algorithm.dynaminc;

/**
 * 动态规划
 */
public class DynamicProgramming2 {

    public int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w + 1];//默认值为false
        states[0][0] = true; //第一行的数据要特殊处理，可以利用"哨兵"优化
        if (weight[0] < w) {
            states[0][weight[0]] = true;
        }

        for (int i = 1; i < n; ++i) {//动态规划，状态转移
            for (int j = 0; j <= w; ++j) {//不把第i个物品放入背包
                if (states[i - 1][j] == true) states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j <= w - weight[i]; ++j) {//把第i个物品放入背包
                if (states[i - 1][j] == true) states[i][j + weight[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) {
            if (states[n - 1][i] == true) return i;
        }
        return 0;
    }

    /**
     * 动态规划的优化版本：降低空间消耗
     *
     * @param items
     * @param n
     * @param w
     * @return
     */
    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w + 1]; // 默认值false
        states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (items[0] <= w) {
            states[items[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = w - items[i]; j >= 0; --j) {//把第i个物品放入背包
                if (states[j] == true) states[j + items[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i] == true) return i;
        }
        return 0;
    }


    public static int knapsack3(int[] weight, int[] value, int n, int w) {
        int[][] states = new int[n][w + 1];
        for (int i = 0; i < n; ++i) { // 初始化states
            for (int j = 0; j < w + 1; ++j) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        if (weight[0] <= w) {
            states[0][weight[0]] = value[0];
        }
        for (int i = 1; i < n; ++i) { //动态规划，状态转移
            for (int j = 0; j <= w; ++j) { // 不选择第i个物品
                if (states[i - 1][j] >= 0) states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j <= w - weight[i]; ++j) { // 选择第i个物品
                if (states[i - 1][j] >= 0) {
                    int v = states[i - 1][j] + value[i];
                    if (v > states[i][j + weight[i]]) {
                        states[i][j + weight[i]] = v;
                    }
                }
            }
        }
        // 找出最大值
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[n - 1][j] > maxvalue) maxvalue = states[n - 1][j];
        }
        return maxvalue;
    }

    public static void main(String[] args) {
        int[] weight = {2, 2, 4, 6, 3};//物品重量
        int n = 5;//物品数量
        int w = 9; //背包可承载的最大重量
        System.out.println(new DynamicProgramming2().knapsack(weight, n, w));

        System.out.println(knapsack2(weight, n, w));
    }
}
