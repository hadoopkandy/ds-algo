package com.kandy.algorithm.week06;

/**
 * 贪心 决策包容性
 */
public class LC134加油站 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int totalGas = 0;
        int totalCost = 0;

        for (int i = 0; i < n; i++) {
            totalGas += gas[i];
            totalCost+= cost[i];
        }

        if (totalGas < totalCost) { //总油量小于总油耗 肯定不能走一圈
            return -1;
        }

        int currentGas = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            currentGas += gas[i] - cost[i];
            if (currentGas < 0) {
                //如果到达下一站的时候油量为负数 就以这个站为起点 从新计算
                currentGas = 0;
                start = i + 1;
            }
        }
        return start;
    }
}
