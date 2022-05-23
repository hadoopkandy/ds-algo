package com.kandy.algorithm.week06;


/**
 * 路径计数
 */
public class LC63不同路径II {
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
    // f(i,j) 表示从(i,j)到End的路径数 往下或往右走
    // 如果（i，j）是空地 ,f(i,j)=f(i+1,j)+f(i,j+1) 否则f(i,j)=0
}
