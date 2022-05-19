package com.kandy.algorithm.week05;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 迷宫 https://blog.csdn.net/ly0724ok/article/details/119058015
 */
public class LC490迷宫 {

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        //方向数组 上下左右
        dx = new int[]{-1, 1, 0, 0};
        dy = new int[]{0, 0, -1, 1};
        m = maze.length;
        n = maze[0].length;
        visit = new boolean[m][n];  //标记哪些节点被遍历过
        dest = destination;
        return bfs(maze, start[0], start[1]);
    }

    public boolean dfs(int[][] maze, int x, int y) {
        //已搜索过的值不用继续判断，直接回溯
        if (visit[x][y])
            return false;
        if (x == dest[0] && y == dest[1])
            return true;

        visit[x][y] = true;  //标记当前节点已遍历过

        //方向数组 上下左右
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            while (nx >= 0 && nx < m && ny >= 0 && ny < n && maze[nx][ny] == 0) {
                // 只要是空格，就一路往前走，直到碰到墙壁
                nx += dx[i];
                ny += dy[i];
            }
            //结束while时，说明当前点是墙壁或者超出边界，往回走一格
            nx -= dx[i];
            ny -= dy[i];

            //这个for循环记录从起点开始能够到达的每一个节点，然后再逐一计算这些节点的每一个节点
            //继续循环各个方向，如果这个停止点是曾经访问过的，则忽略
            if (dfs(maze, nx, ny))    //继续找当前节点的上下左右节点
                return true;
        }
        return false;  //如果遍历所有节点都未找到终点值，返回false
    }

    private boolean bfs(int[][] maze, int sx, int sy) {
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();//存储节点坐标
        q.offer(new Pair<Integer, Integer>(sx, sy));
        visit[sx][sy] = true;
        while (!q.isEmpty()) {
            // 获取当前坐标
            int x = q.peek().getKey();
            int y = q.poll().getValue();
            if (x == dest[0] && y == dest[1]) {
                return true;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                while (nx >= 0 && nx < m && ny >= 0 && ny < n && maze[nx][ny] == 0) {
                    // 只要是空格，就一路往前走，直到碰到墙壁
                    nx += dx[i];
                    ny += dy[i];
                }
                //结束while时，说明当前点是墙壁或者超出边界，往回走一格
                nx -= dx[i];
                ny -= dy[i];
                //这个for循环记录从起点开始能够到达的每一个节点，然后再逐一计算这些节点的每一个节点
                // 如果这个停止点是曾经访问过的，则忽略
                if (!visit[nx][ny]) {
                    q.offer(new Pair<Integer, Integer>(nx, ny));
                    visit[nx][ny] = true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] maze = new int[][]{{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        int[] start = new int[]{0, 4};
        //4,4 可以停止  3,2 可以途经，但是不会停止
        int[] destination = new int[]{3, 2};
        LC490迷宫 lc = new LC490迷宫();
        System.out.println(lc.hasPath(maze, start, destination));
    }

    private int m;
    private int n;
    private boolean[][] visit;
    int[] dx;// 网格中行走题目，技巧：方向数组
    int[] dy;
    int[] dest;
}
