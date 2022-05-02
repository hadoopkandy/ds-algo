package com.kandy.algorithm.week03.homework;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 关键点：出边数组、入度数组、拓扑排序
 * 拓扑排序第一步，零入度点入队
 * 第二步，扩展每个点 周围的点入度减1
 * 第三步，入度为0，表示可以入队了
 */
public class LC210课程表II {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 初始化
        n = numCourses;
        to = new ArrayList<List<Integer>>();
        inDeg = new int[n];
        for (int i = 0; i < n; i++) {
            to.add(new ArrayList<Integer>());
            inDeg[i] = 0;
        }
        // 建图，加边
        for (int[] pre : prerequisites) {
            int ai = pre[0];
            int bi = pre[1];
            //[ai, bi]，表示在选修课程ai前必须先选修bi
            addEdge(bi, ai);
        }
        // 拓扑排序
        return topsort();
    }

    int[] topsort() {
        int[] order = new int[n];
        int m = 0;
        Queue<Integer> q = new LinkedList<Integer>();
        // 拓扑排序第一步，零入度点入队
        for (int i = 0; i < n; i++)
            if (inDeg[i] == 0) q.offer(i);
        while (!q.isEmpty()) {
            Integer x = q.poll();
            order[m++] = x;
            // 第二步，扩展每个点 周围的点入度减1
            for (Integer y : to.get(x)) {
                inDeg[y]--;
                //第三步，入度减为0，表示可以入队了
                if (inDeg[y] == 0) q.offer(y);
            }
        }
        // n个课程都进出过队列，说明可以完成
        if (m == n) return order;
        return new int[0];
    }

    private void addEdge(int x, int y) {
        to.get(x).add(y);
        inDeg[y]++;
    }

    private int n; //课程数
    private List<List<Integer>> to; //出边数组
    private int[] inDeg; //入度
}
