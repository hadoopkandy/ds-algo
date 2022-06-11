package com.kandy.algorithm.week08;

import java.util.ArrayList;
import java.util.List;

/**
 * 最小生成树(克鲁斯卡尔算法)
 */
public class Kruskal模板LC1584连接所有点的最小费用 {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        //构造出边<i,j,边权>
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                edges.add(new int[]{i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])});
        // 按照边权排序
        edges.sort((a, b) -> {
            return a[2] - b[2];
        });
        // Kruskal算法
        fa = new int[n];
        for (int i = 0; i < n; i++) fa[i] = i;
        int ans = 0;
        //扫描每条边，考虑用并查集合并
        for (int i = 0; i < edges.size(); i++) {
            int x = edges.get(i)[0];
            int y = edges.get(i)[1];
            int z = edges.get(i)[2];
            x = find(x);//找集合代表
            y = find(y);
            if (x != y) {//如果xy还没有合并(相连)，如果xy相连(在同一集合里，则忽略)
                ans += z;
                fa[x] = y;//x y相连合并
            }
        }
        return ans;
    }

    int[] fa;

    int find(int x) {
        if (x == fa[x]) return x;
        return fa[x] = find(fa[x]);
    }

}
