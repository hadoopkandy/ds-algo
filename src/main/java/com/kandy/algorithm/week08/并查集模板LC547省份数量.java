package com.kandy.algorithm.week08;

/**
 * 1.并查集初始状态下每个结点的父亲均为它自己
 * 2.并查集执行N次合并操作的时间复杂度为O(n*α(n))，近似于线性
 * 3.并查集使用了路径压缩的思想进行优化
 */
public class 并查集模板LC547省份数量 {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        DisjointSet s = new DisjointSet(n);
        //对于每条边，把两个集合合并
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (isConnected[i][j] == 1) s.unionSet(i, j);
        //有几棵树(有几个根)，就是有几个省份
        int ans = 0;
        for (int i = 0; i < isConnected.length; i++)
            if (s.find(i) == i) ans++;
        return ans;
    }

    class DisjointSet {
        //MakeSet(n)
        public DisjointSet(int n) {
            fa = new int[n];
            for (int i = 0; i < n; i++) fa[i] = i;//根节点的fa等于自己
        }

        //find时进行路径压缩 查找元素x所在的集合代表
        public int find(int x) {
            if (x == fa[x]) return x;
            return fa[x] = find(fa[x]);
        }

        //把元素x和元素y所在的集合合并，把x的fa 改成y
        public void unionSet(int x, int y) {
            x = find(x);
            y = find(y);
            if (x != y) fa[x] = y;
        }

        //fa[x]表示：编号为x的节点的父节点
        int[] fa;
    }
}
