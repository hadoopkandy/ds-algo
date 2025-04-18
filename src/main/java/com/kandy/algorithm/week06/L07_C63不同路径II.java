package com.kandy.algorithm.week06;


/**
 * 路径计数
 * Top-down： f[i][j] = f[i - 1][j] + f[i][j - 1]  f(i,j) 表示从Start到(i,j)的路径数  （每步只能从上或左来）
 * Bottom-up：f(i,j)=f(i+1,j) + f(i,j+1)  f(i,j) 表示从(i,j)到End的路径数  每步只能往下或往右走
 */
public class L07_C63不同路径II {
    // Top-down
    // f(i,j) 表示从Start到(i,j)的路径数  （从上或左来）
    // 如果（i，j）是空地 ,f(i,j)=f(i-1,j)+f(i,j-1) 否则f(i,j)=0
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] f = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) f[i][j] = 0;//有障碍物
                else if (i == 0 && j == 0) f[i][j] = 1;
                else if (i == 0) f[i][j] = f[i][j - 1];
                else if (j == 0) f[i][j] = f[i - 1][j];
                else f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        return f[m - 1][n - 1];
    }

    // Bottom-up
    // f(i,j) 表示从(i,j)到End的路径数 每步只能往下或往右走
    // 如果（i，j）是空地 ,f(i,j)=f(i+1,j)+f(i,j+1) 否则f(i,j)=0
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        m = obstacleGrid.length;
        n = obstacleGrid[0].length;
        opt = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                opt[i][j] = -1;
            }
        }
        return countPaths(obstacleGrid, 0, 0);
    }

    //记忆化搜索（递归、分治思想）回溯时累加结果
    int countPaths(int[][] grid, int row, int col) {
        if (!validSquare(grid, row, col)) return 0; //碰到障碍物或者越界
        if (isAtEnd(row, col)) return 1; //到终点

        if (opt[row][col] != -1) {
            return opt[row][col]; //算过了，直接返回
        }

        opt[row][col] = countPaths(grid, row + 1, col) + countPaths(grid, row, col + 1);

        return opt[row][col];
    }

    boolean validSquare(int[][] grid, int row, int col) {
        return row >= 0 && row <= m - 1 && col >= 0 && col <= n - 1 && grid[row][col] != 1;
    }

    boolean isAtEnd(int row, int col) {
        return row == m - 1 && col == n - 1;
    }

    int[][] opt;
    int m;
    int n;
}
