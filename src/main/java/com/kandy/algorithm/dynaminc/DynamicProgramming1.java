package com.kandy.algorithm.dynaminc;

/**
 * 0-1背包问题：对于一组不同重量、不可分割的物品，选择其中一些物品装入背包，
 * 在不超过背包可承载的最大重量的前提下，背包中可装载物品总重量最大是多少？
 * 假设背包的最大承载重量是9,现在有5个不同的物品，每个物品的重量分别是2、2、4、6、3
 */
public class DynamicProgramming1 {
    //回溯算法的实现  注意：作者把输入的变量都定义成了成员变量
    private int maxW = Integer.MIN_VALUE;//结果放到变量maxW中
    private int[] weight = {2, 2, 4, 6, 3};//物品重量
    private int n = 5;//物品数量
    private int w = 9; //背包可承载的最大重量

    /**
     * @param i  将决策第几个物品是否装入背包，cw表示当前背包中物品的总重量
     * @param cw
     */
    public void f(int i, int cw) { // 调用f(0, 0)
        if (cw == w || i == n) { // cw==w表示装满了，i==n表示物品都考察完了
            if (cw > maxW) maxW = cw;
            return;
        }
        f(i + 1, cw); // 选择不装第i个物品
        if (cw + weight[i] <= w) {
            f(i + 1, cw + weight[i]); // 选择装第i个物品
        }
    }

    /**
     * 回溯算法的"备忘录"优化版本
     */
    private boolean[][] mem = new boolean[5][10]; // 备忘录，默认值false

    public void f2(int i, int cw) { // 调用f(0, 0)
        if (cw == w || i == n) { // cw==w表示装满了，i==n表示物品都考察完了
            if (cw > maxW) maxW = cw;
            return;
        }
        if (mem[i][cw]) return; // 重复状态
        mem[i][cw] = true; // 记录(i, cw)这个状态
        f2(i + 1, cw); // 选择不装第i个物品
        if (cw + weight[i] <= w) {
            f2(i + 1, cw + weight[i]); // 选择装第i个物品
        }
    }

    public static void main(String[] args) {
        final DynamicProgramming1 dp = new DynamicProgramming1();
//        dp.f(0, 0);
        dp.f2(0, 0);
        System.out.println(dp.maxW);
    }
}
