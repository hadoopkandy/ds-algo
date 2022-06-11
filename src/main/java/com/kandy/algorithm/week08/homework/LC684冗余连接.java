package com.kandy.algorithm.week08.homework;

/**
 * 并查集实现
 */
public class LC684冗余连接 {
    public int[] findRedundantConnection(int[][] edges) {
        //如果一棵树有 n个节点，则这棵树有 n-1条边
        //根据题意这道题中的图在树的基础上多了一条附加的边，因此边的数量也是n
        n = edges.length;// 根据题意分析，顶点数=edges.length

        fa = new int[n + 1];//1..n表是节点 0浪费掉
        for (int i = 1; i <= n; i++) fa[i] = i;

        //扫描每条边，考虑用并查集合并
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            x = find(x);//找集合代表
            y = find(y);
            //x y相连合并
            if (x != y) {
                fa[x] = y;
            } else {
                //x y已经相连过，加入的这条边引起环
                return edge;
            }
        }
        return null;
    }

    int n;
    int[] fa;//fa[x]表示：编号为x的节点的父节点

    //find时进行路径压缩 查找元素x所在的集合代表
    int find(int x) {
        if (x == fa[x]) return x;
        return fa[x] = find(fa[x]);
    }
}
