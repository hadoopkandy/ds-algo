package com.kandy.algorithm.week08;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 本题用堆优化的Dijkstra算法解题，以下几个细节需要注意：
 * 1.节点数是n个 0~n-1，因此dist expand数组初始化成容量为n
 * 2.本题的距离变成了改成概率double类型，dist[start] 起点的初始化成1.0
 * 3.本地要求的是最大概率（路径），因此PriorityQueue 要变成大根堆，路径更新方式是用乘法。
 * 4.本题给的是无向图，建图需要双向建立
 */
public class LC1514概率最大的路径 {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        dist = new double[n]; //节点0~n-1
        ver = new ArrayList<List<Integer>>(); // 另一端点
        edge = new ArrayList<List<Double>>(); // 边权
        expand = new boolean[n];

        //建图
        for (int i = 0; i < n; i++) {
            ver.add(new ArrayList<Integer>());
            edge.add(new ArrayList<Double>());
            dist[i] = -1e9;
        }
        dist[start] = 1.0;//起点

        //建图,存在出边数组、边权数组
        for(int i = 0;i<edges.length;i++){
            int x = edges[i][0];
            int y = edges[i][1];
            double z = succProb[i];
            // 出边数组 addEdge 模板
            ver.get(x).add(y);
            edge.get(x).add(z);

            ver.get(y).add(x);
            edge.get(y).add(z);
        }

        //大根堆
        q = new PriorityQueue<>((a, b) -> { return Double.compare(b.getKey(),a.getKey()); });
        q.offer(new Pair<>(1.0,start));//距离是0，编号是k
        while (!q.isEmpty()) {
            Pair<Double,Integer> top = q.poll();
            int x = top.getValue();

            if (expand[x]) continue;
            expand[x] = true;
            for (int i = 0; i < ver.get(x).size(); i++) {
                int y = ver.get(x).get(i);
                double z = edge.get(x).get(i);
                if (dist[y] < dist[x] * z) {
                    dist[y] = dist[x] * z;
                    q.offer(new Pair<>(dist[y],y));
                }
            }
        }
        return (dist[end] == -1e9) ? 0.0 : dist[end];
    }
    double[] dist;
    private List<List<Integer>> ver; // 另一端点(出边数组)
    private List<List<Double>> edge;// 边权
    boolean[] expand; //标记每个点是否被标记

    PriorityQueue<Pair<Double,Integer>> q;//堆，每个结点是长度为2的数组 [概率，编号]
}
