package com.kandy.algorithm.week05;

import java.util.Arrays;

/**
 * 'M' 代表一个 未挖出的 地雷
 * 'E' 代表一个 未挖出的 空方块
 * 'B' 代表周围没有地雷，已挖出的空白方块
 * 数字 代表有多少地雷在周围（1-8）
 * 'X' 则表示一个 已挖出的地雷
 * <p>
 * 解题思路：
 * 1.如果是雷，直接更新成X退出
 * 2.统计当前格子周围有雷，则将当前格子更新成雷的数量，否则更新成B
 * 3.如果附近有雷，则不再继续扩展  没有雷 则按照2 递归拓展
 */
public class LC529扫雷游戏 {
    public char[][] updateBoard(char[][] board, int[] click) {
        m = board.length;
        n = board[0].length;
        int x = click[0], y = click[1];
        if (board[x][y] == 'M') {
            // 规则 1 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'
            board[x][y] = 'X';
        } else {
            //游戏继续，进入DFS遍历
            dfs(board, x, y);
        }
        return board;
    }

    public void dfs(char[][] board, int x, int y) {
        //统计 x y 附近雷的数量cnt
        int cnt = 0;
        for (int i = 0; i < 8; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            //格子越界 continue
            if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                continue;
            }
            // 不用判断 M，因为如果有 M 的话游戏已经结束了
            if (board[nx][ny] == 'M') {
                ++cnt; //统计x y周围有多少雷
            }
        }
        //递归终止条件1：周围有雷
        if (cnt > 0) {
            // 规则 3 更新 x y周围雷的数量
            board[x][y] = (char) (cnt + '0');
        } else {
            // 规则 2 如果当前 x y 周围没有雷，先将当前x y这个位置，更新成B，然后开始递归的搜索和这个x y相邻的雷的情况
            board[x][y] = 'B';//记忆化搜索 将 E 改成 B
            for (int i = 0; i < 8; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 这里不需要在存在 B 的时候继续扩展，因为 B 之前被点击的时候已经被扩展过了
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || board[nx][ny] != 'E') {
                    continue;
                }
                dfs(board, nx, ny);
            }
            //影藏的递归终止条件2：没有更多的方块可揭露（要么越界，要么都已经E都已经访问过（变成B））
        }
    }

    private int m;
    private int n;
    //方向数组，代表相邻的8个方向 （上下左右及对角线4个方向）
    int[] dx = {-1, 1, 0, 0, 1, 1, -1, -1};
    int[] dy = {0, 0, -1, 1, 1, -1, 1, -1};

    public static void main(String[] args) {
        char[][] board = {{'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'M', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}};
        LC529扫雷游戏 game = new LC529扫雷游戏();
        game.updateBoard(board, new int[]{3, 0});
        int m = board.length;
        for (int i = 0; i < m; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }
}
