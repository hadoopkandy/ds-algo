package com.kandy.algorithm.week05;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 两种思路：
 * BFS 拓扑排序 起->终 自顶向下
 * DFS 记忆化搜索 终->起 自底向上
 */
public class L11_C329矩阵中的最长递增路径 {
    List<List<Integer>> to; //出边数组
    int[] deg; //入度
    int[] bfs_dist; //每个点的最长长度

    public int longestIncreasingPath(int[][] matrix) {
        //初始化
        m = matrix.length;
        n = matrix[0].length;
        to = new ArrayList<>();
        deg = new int[m * n];
        bfs_dist = new int[m * n];
        for (int i = 0; i < m * n; i++) {
            to.add(new ArrayList());
        }

        //方向数组，上 左 右 下
        int dx[] = new int[]{-1, 0, 0, 1};
        int dy[] = new int[]{0, -1, 1, 0};

        //建图
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    int ni = i + dx[k];
                    int nj = j + dy[k];
                    //遇到递增的点就加边
                    if (valid(ni, nj) && matrix[ni][nj] > matrix[i][j]) {
                        addEdge(num(i, j), num(ni, nj));
                    }
                }
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < m * n; i++) {
            //一个点没有入度表示周围点都比它大，它是起点
            if (deg[i] == 0) {
                q.offer(i);
                bfs_dist[i] = 1;
            }
        }

        //拓扑排序
        while (!q.isEmpty()) {
            int x = q.poll();
            for (int y : to.get(x)) {
                bfs_dist[y] = Math.max(bfs_dist[y], bfs_dist[x] + 1);
                deg[y]--;
                //入边都求完max
                if (deg[y] == 0) {
                    q.offer(y);
                }
            }
        }

        //从零度点之一到最长的长度即为答案
        int ans = 0;
        for (int i = 0; i < m * n; i++) {
            ans = Math.max(ans, bfs_dist[i]);
        }

        return ans;
    }

    //加边
    private void addEdge(int u, int v) {
        to.get(u).add(v);
        deg[v]++;
    }

    //把二维的行列号变为以一维点编号
    int num(int i, int j) {
        return i * n + j;
    }

    //以下为记忆化搜素DFS解法
    public int longestIncreasingPath2(int[][] matrix) {
        this.matrix = matrix;
        m = matrix.length;
        n = matrix[0].length;
        dist = new int[m][n]; // default value: 0 表示没算过
        //方向数组 上 左 右 下
        dx = new int[]{-1, 1, 0, 0};
        dy = new int[]{0, 0, -1, 1};
        int ans = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                ans = Math.max(ans, dfs(i, j));
        return ans;
    }

    // 子问题：从(x,y)出发能走多远
    // x：行号，y：列号
    int dfs(int x, int y) {
        // 算过了，直接返回（只算一次）避免重复计算
        if (dist[x][y] != 0) return dist[x][y];
        // 走不动了，至少是1
        dist[x][y] = 1;
        // 四个方向
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (valid(nx, ny) && matrix[nx][ny] > matrix[x][y]) {
                dist[x][y] = Math.max(dist[x][y], dfs(nx, ny) + 1);
            }
        }
        return dist[x][y];
    }

    //给行号 列号 是否合法
    boolean valid(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }

    int[][] matrix;
    int m, n; //行 列
    int[] dx; //方向数组
    int[] dy; //方向数组
    int[][] dist;
}
