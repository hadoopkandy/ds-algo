package com.kandy.algorithm.week05;


import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 200. 岛屿数量
 * 特征：地图类DFS/、判重、方向数组
 */
public class LC200岛屿数量 {

    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visit = new boolean[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                visit[i][j] = false;
        //方向数组 上下左右
        dx = new int[]{-1, 1, 0, 0};
        dy = new int[]{0, 0, -1, 1};

        int ans = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                //没有走过的1
                if (grid[i][j] == '1' && !visit[i][j]) {
                    bfs(grid, i, j);
                    ans++;
                }
        return ans;
    }

    // 从(sx,sy)出发bfs
    private void bfs(char[][] grid, int sx, int sy) {
        Queue<Pair<Integer, Integer>> q = new LinkedList<Pair<Integer, Integer>>();
        // 第一步：push起点
        q.offer(new Pair<Integer, Integer>(sx, sy));
        visit[sx][sy] = true;
        while (!q.isEmpty()) {
            //第二步：取队头
            int x = q.peek().getKey();
            int y = q.poll().getValue();
            // 第三步：扩展队头 扩展所有出边（四个方向）
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 任何时候访问数组前，判断合法性
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                if (grid[nx][ny] == '1' && !visit[nx][ny]) {
                    q.offer(new Pair<Integer, Integer>(nx, ny));
                    // BFS：入队时标记visit
                    visit[nx][ny] = true;
                }
            }
        }
    }

    //使用深度优先遍历将岛屿元素为1的变为visit
    void dfs(char[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == '0' || visit[x][y]) {
            return;
        }
        visit[x][y] = true;

        //方向数组 上下左右
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            dfs(grid, nx, ny);
        }
    }

    private int m;
    private int n;
    private boolean[][] visit;
    int[] dx;// 网格中行走题目，技巧：方向数组
    int[] dy;
}
