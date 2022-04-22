package com.kandy.algorithm.week02;

/**
 * 304. 二维区域和检索 - 矩阵不可变
 */
public class LC304二维区域和检索 {
    class NumMatrix {
        int[][] sums;

        /**
         * NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
         * 创建m行n+1列的二维数组sums，其中 m和分别是矩阵matrix的行数和列数，sums[i]为matrix[i]的前缀和数组。
         * 将sums的列数设为 n+1的目的是为了方便计算每一行的子数组和，不需要对col=0 的情况特殊处理
         */
        public NumMatrix(int[][] matrix) {
            int m = matrix.length;
            if (m > 0) {
                int n = matrix[0].length;
                sums = new int[m][n + 1];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        sums[i][j + 1] = sums[i][j] + matrix[i][j];
                    }
                }
            }
        }

        /**
         * 返回 左上角 (row1, col1) 、右下角 (row2, col2) 所描述的子矩阵的元素 总和
         * @param row1
         * @param col1
         * @param row2
         * @param col2
         * @return
         */
        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int i = row1; i <= row2; i++) {
                sum += sums[i][col2 + 1] - sums[i][col1];
            }
            return sum;
        }
    }
}
