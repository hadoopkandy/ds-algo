package com.kandy.algorithm.week10.homework;

import java.util.LinkedList;
import java.util.Queue;


/*
高级搜索
1091.二进制矩阵中的最短路径
https://leetcode.cn/problems/shortest-path-in-binary-matrix/solution/bfsa-qi-fa-sou-suo-duo-chong-fang-fa-you-jrqp/
752. 打开转盘锁
https://leetcode.cn/problems/open-the-lock/solution/gong-shui-san-xie-yi-ti-shuang-jie-shuan-wyr9/
847. 访问所有节点的最短路径
https://leetcode.cn/problems/shortest-path-visiting-all-nodes/solution/gong-shui-san-xie-yi-ti-shuang-jie-bfs-z-6p2k/
 */
public class LC1091二进制矩阵中的最短路径 {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {  // 只有两端都是0才可以开始
            return -1;
        }
        int count = 0;
        int[] dx = {0, 1, 0, -1, 1, -1, 1, -1};
        int[] dy = {1, 0, -1, 0, 1, -1, -1, 1}; // 8个位置
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        grid[0][0] = 1; // 走过的标记为1，这样就可以走最短的了
        while (!queue.isEmpty()) { // 进入BFS
            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++) { // 逐层搜索
                int[] tmp = queue.poll();
                int x = tmp[0];
                int y = tmp[1];
                if (x == n - 1 && y == n - 1) { // 到达终点就返回
                    return count;
                }
                for (int k = 0; k < 8; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 0) {  // 加入下一层的数
                        queue.offer(new int[]{nx, ny});
                        grid[nx][ny] = 1;
                    }
                }
            }
        }
        return -1;
    }
}
