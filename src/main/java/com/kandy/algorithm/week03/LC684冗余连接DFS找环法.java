package com.kandy.algorithm.week03;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据实际上是一棵基环树
 * 深度优先遍历找环，环上删除一条边
 *
 * 关键点：出边数组、无向图深度优先遍历找环、visit数组，避免重复访问
 */
public class LC684冗余连接DFS找环法 {
    public int[] findRedundantConnection(int[][] edges) {
        // 出现过的最大的点就是n
        n = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            n = Math.max(u, n);
            n = Math.max(v, n);
        }

        // 模板：出边数组初始化
        // 初态：[[], [], ... []]
        to = new ArrayList<List<Integer>>();
        // [false, false, ...]  1..n表是节点 0浪费掉
        visit = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            to.add(new ArrayList<Integer>());
            visit[i] = false;
        }
        hasCycle = false;

        // 加边
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            // 无向图看作双向边的有向图
            addEdge(x, y);
            addEdge(y, x);

            // 每加一条边，看图中是否多了环c
            for (int i = 1; i <= n; i++) visit[i] = false;
            dfs(x, -1);
            if (hasCycle) return edge;
        }
        return null;
    }

    // 模板：无向图深度优先遍历找环
    // visit数组，避免重复访问
    // father是第一次走到x的点
    private void dfs(int x, int father) {
        // 第一步：标记已访问
        visit[x] = true;
        // 第二步：遍历所有出边
        for (Integer y : to.get(x)) {
            if (y == father) continue; // 返回父亲，S-A A-S不是环
            if (visit[y]) hasCycle = true;
            else dfs(y, x); //x是y的父亲
        }
    }

    // 模板：加边
    private void addEdge(int x, int y) {
        to.get(x).add(y);
    }

    // 出边数组
    int n;
    private List<List<Integer>> to;
    boolean hasCycle;
    private boolean[] visit;
}
