package com.kandy.algorithm.week05.homework;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 关键点：任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'
 * 从没有访问过的边界点'O'出发，进行DFS或者BFS遍历，将其相连的'O'标记为已访问。
 * 根据题目意思：没有访问过的'O'标记成'X'即可
 *
 * 特征：地图类DFS/、判重、方向数组
 */
public class LC130被围绕的区域 {
    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;
        visit = new boolean[m][n];//默认就是false，无需再次初始化

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                //没有访问的边界点'O'出发进行DFS 或BFS
                if (isBoundary(i, j) && board[i][j] == 'O' && !visit[i][j]) {
                    dfs(board, i, j);
                }

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                //没有访问过的'O' 将其设置为'X'
                if (board[i][j] == 'O' && !visit[i][j]) {
                    board[i][j] = 'X';
                }

    }

    //使用深度优先遍历将边界'O'以及相连的'O'标记为已访问
    private void dfs(char[][] board, int x, int y) {
        if (x < 0 || y < 0 || x >= m || y >= n || board[x][y] == 'X' || visit[x][y]) {
            return;
        }
        visit[x][y] = true;

        //方向数组 上下左右
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            dfs(board, nx, ny);
        }
    }

    //给行号 列号 是否是边界点
    private boolean isBoundary(int i, int j) {
        return i == 0 || i == m - 1 || j == 0 || j == n - 1;
    }

    // 从(sx,sy)出发bfs 将边界'O'以及相连的'O'标记为已访问
    private void bfs(char[][] board, int sx, int sy) {
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        // 第一步：push起点
        q.offer(new Pair<>(sx, sy));
        visit[sx][sy] = true;
        while (!q.isEmpty()) {
            int x = q.peek().getKey();
            int y = q.poll().getValue();
            // 扩展所有出边（四个方向）
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 任何时候访问数组前，判断合法性
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                if (board[nx][ny] == 'O' && !visit[nx][ny]) {
                    q.offer(new Pair<>(nx, ny));
                    // BFS：入队时标记visit
                    visit[nx][ny] = true;
                }
            }
        }
    }

    private int m;
    private int n;
    private boolean[][] visit;
    int[] dx = new int[]{-1, 1, 0, 0};// 网格中行走题目，技巧：方向数组
    int[] dy = new int[]{0, 0, -1, 1};//上下左右
}
