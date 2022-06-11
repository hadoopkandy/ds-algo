package com.kandy.algorithm.week08;

public class Floyd模板LC1334阈值距离内邻居最少的城市 {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // 邻接矩阵初值：i到i长度为0，没有边长度为oo，其余为输入的边
        //三种情况：没边相连=1e9  有边相连=边权  ii=0
        int[][] d = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                d[i][j] = (int) 1e9;
        for (int i = 0; i < n; i++) d[i][i] = 0;

        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], z = edge[2];
            d[x][y] = d[y][x] = z;
        }

        // Floyd算法
        //中继点从小到大 k是阶段 按状态决策
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);//d[i][j]表示:i->j的最短路径
        // 根据题意统计答案
        int minNeighbour = (int) 1e9;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int neighbour = 0;
            for (int j = 0; j < n; j++)
                if (i != j && d[i][j] <= distanceThreshold) neighbour++;
            if (neighbour < minNeighbour || (neighbour == minNeighbour && i > ans)) {
                minNeighbour = neighbour;
                ans = i;
            }
        }
        return ans;
    }
}
