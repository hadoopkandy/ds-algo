package com.kandy.algorithm.week08.homework;


/**
 * 并查集实现
 */
public class LC200岛屿数量 {
    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        //并查集 MakeSet(mn)
        fa = new int[m * n];
        for (int i = 0; i < m * n; i++) fa[i] = i;

        //方向数组
        int[] dx = new int[]{-1, 0, 0, 1};
        int[] dy = new int[]{0, -1, 1, 0};

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') continue;

                //如果一个位置为1，则与相邻4个方向在并查集中合并
                for (int k = 0; k < 4; k++) {
                    int ni = i + dx[k];
                    int nj = j + dy[k];
                    //下标不合法跳过
                    if (ni < 0 || nj < 0 || ni >= m || nj >= n) {
                        continue;
                    } else {
                        if (grid[ni][nj] == '1')
                            unionSet(num(i, j), num(ni, nj));
                    }
                }
            }
        int ans =0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                //如果位置为1 fa[x] != x 说明被合并了
                if (grid[i][j] == '1' && fa[num(i, j)] == num(i,j))
                    ans++;
        return ans;
    }
    //并查集 find时进行路径压缩 查找元素x所在的集合代表
    int find(int x) {
        if (x == fa[x]) return x;
        return fa[x] = find(fa[x]);
    }

    //并查集 把元素x和元素y所在的集合合并，集合代表不一样时,把x的fa 改成y
    void unionSet(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) fa[x] = y;
    }

    //行列坐标变成一维编号
    int num(int i, int j) {
        return i * n + j;
    }

    int m, n;
    int[] fa;//并查集 fa[x]表示：编号为x的节点的父节点
}
