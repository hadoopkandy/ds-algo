package com.kandy.algorithm.week05;


/**
 * 200. 岛屿数量
 */
public class LC200岛屿数量 {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        //获取数组行数和列数
        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        //遍历数组中每一个元素
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;  //若出现元素值为1，则岛屿数加一
                    dfs(grid, r, c); //使用深度优先遍历将岛屿所有元素变为0
                }
            }
        }

        return num_islands;
    }

    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length; //获取数组行数
        int nc = grid[0].length; //获取数组列数

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0'; //将当前格的值设为0，表示已经遍历过
        //遍历上下左右四个
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }
}
