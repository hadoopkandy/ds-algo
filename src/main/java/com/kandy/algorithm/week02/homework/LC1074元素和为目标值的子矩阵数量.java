package com.kandy.algorithm.week02.homework;

/**
 * 1074.元素和为目标值的子矩阵数量
 */
public class LC1074元素和为目标值的子矩阵数量 {

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int ans = 0;
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) { // 枚举上边界
            int[] sum = new int[n];
            for (int j = i; j < m; j++) { // 枚举下边界
                for (int c = 0; c < n; c++) {
                    sum[c] += matrix[j][c]; // 更新每列的元素和
                }
                ans += subarraySum(sum, target);
            }
        }
        return ans;
    }

    //暴力枚举法
    public int subarraySum(int[] nums, int k) {
        //记录和为k的子数组个数
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            //累计子数组[j,i]的和
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
