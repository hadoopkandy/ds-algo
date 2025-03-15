package com.kandy.algorithm.week03;

import javafx.util.Pair;

import java.util.*;

/**
 * 1245.树的直径
 * 两次深度优先遍历：
 * 第一次从任意一个点出发，找到距离它最远的点p
 * 第二次从p出发，找到距离它最远的点q
 * 连接p,q的路径即为树的直径
 *
 * 关键点：出边数组存储图、基于queue的广度优先遍历记录深度
 */
public class L09_C1245树的直径 {
    public int treeDiameter(List<List<Integer>> edges) {
        to = new ArrayList<>();
        n = 0;
        for (List<Integer> edge : edges) {
            int x = edge.get(0);
            int y = edge.get(1);
            n = Math.max(n, Math.max(x, y));
        }
        //最大编号+1 = 点数
        n++;
        for (int i = 0; i < n; i++) {
            to.add(new ArrayList<>());
        }

        //出边数组初始化
        for (List<Integer> edge : edges) {
            int x = edge.get(0);
            int y = edge.get(1);
            //无向图
            to.get(x).add(y);
            to.get(y).add(x);
        }

        //离0最远的点p
        int p = findFarthest(0).getKey();
        //离p最远的距离
        return findFarthest(p).getValue();
    }

    //<点，距离>
    Pair<Integer, Integer> findFarthest(int start) {
        Queue<Integer> q = new LinkedList<>();
        //深度
        int[] depth = new int[n];
        //数组初始化默认值-1
        Arrays.fill(depth,-1);
        depth[start] = 0;
        //起始第0层
        q.add(start);
        while (!q.isEmpty()) {
            int x = q.poll();
            //遍历x可以到的to的点
            for (int y : to.get(x)) {
                if (depth[y] != -1) {
                    continue;//走过了
                }
                depth[y] = depth[x] + 1;
                q.add(y);
            }
        }
        //更新答案
        int ans = start;
        for (int i = 0; i < n; i++) {
            if (depth[i] > depth[ans]) ans = i;
        }
        return new Pair<>(ans, depth[ans]);
    }

    //出边数组
    List<List<Integer>> to;
    //点数
    int n;

    public static void main(String[] args) {
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(Arrays.asList(0, 1));
        edges.add(Arrays.asList(1, 2));
        edges.add(Arrays.asList(2, 3));
        edges.add(Arrays.asList(1, 4));
        edges.add(Arrays.asList(4, 5));
        L09_C1245树的直径 lc = new L09_C1245树的直径();
        System.out.println(lc.treeDiameter(edges));
    }

}
