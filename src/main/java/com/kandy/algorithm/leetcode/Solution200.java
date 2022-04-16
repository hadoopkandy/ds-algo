package com.kandy.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 200. 岛屿数量
 */
public class Solution200 {

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

    public int numIsland2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        //获取数组的行数和列数
        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;//初始化岛屿数量

        //遍历数组的每个元素
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {//若当前元素值为1
                    ++num_islands; //岛屿数加1
                    grid[r][c] = '0'; //将当前元素值设为0，表示已访问
                    Queue<Integer> neighbors = new LinkedList<>(); //使用队列进行广度优先搜索
                    neighbors.add(r * nc + c); //将当前元素加入队列
                    //循环迭代直到队列为空
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.remove();
                        int row = id / nc;
                        int col = id % nc;
                        //遍历上下左右四格，若有元素值为1则加入队列
                        if (row - 1 >= 0 && grid[row-1][col] == '1') {
                            neighbors.add((row-1) * nc + col);
                            grid[row-1][col] = '0';
                        }
                        if (row + 1 < nr && grid[row+1][col] == '1') {
                            neighbors.add((row+1) * nc + col);
                            grid[row+1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col-1] == '1') {
                            neighbors.add(row * nc + col-1);
                            grid[row][col-1] = '0';
                        }
                        if (col + 1 < nc && grid[row][col+1] == '1') {
                            neighbors.add(row * nc + col+1);
                            grid[row][col+1] = '0';
                        }
                    }
                }
            }
        }
        return num_islands;
    }
}
