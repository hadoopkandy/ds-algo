package com.kandy.algorithm.week08;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Dijkstra算法是基于贪心思想，只适用于所有边的长度都是非负数的图
 * 1.初始化dist[1] =0,其余节点的dist值为正无穷大  O(n)
 * 2.找出一个未被标记的，dist[x] 最小的节点x,然后标记节点x  O(n2)
 * 3.扫描节点x的所有出边(x,y,z) 若dist[y] > dist[x] + z, 则用dist[x] + z更新dist[y]   O(m)
 * 4.重复上述2-3两个步骤，直到所以节点都被标记
 *
 * 给定一个n个点m条边的有向图，图中可能存在重边和自环，所有边权均为非负值。
 * 请你求出1号点到n号点的最短距离，如果无法从1号走到n号点，则输出-1。
 * 输入样例：
 * 3 3
 * 1 2 2
 * 2 3 1
 * 1 3 4
 */
public class Dijkstra模板acwing850 {
    public static void main(String args[]) throws Exception {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();//顶点数
        int m = input.nextInt();//边数

        // 模板：出边数组初始化
        // 初态：[[], [], ... []]
        List<List<Integer>> ver = new ArrayList<List<Integer>>(); // 另一端点
        List<List<Integer>> edge = new ArrayList<List<Integer>>(); // 边权
        boolean[] v = new boolean[n + 1];
        int[] dist = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            ver.add(new ArrayList<Integer>());
            edge.add(new ArrayList<Integer>());
            v[i] = false;
            dist[i] = (int)1e9;
        }

        for (int i = 1; i <= m; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            int z = input.nextInt();
            // 出边数组 addEdge 模板
            ver.get(x).add(y);
            edge.get(x).add(z);
        }

        // Dijkstra算法
        dist[1] = 0;
        // 堆，每个结点是长度为2的数组 [点，dist]
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {return a[1] - b[1];});
        q.offer(new int[]{1, 0});
        while(!q.isEmpty()){
            int[] top = q.poll();
            int x = top[0];
            if (v[x]) continue;
            v[x] = true;
            for (int i = 0; i < ver.get(x).size(); i++) {
                int y = ver.get(x).get(i);
                int z = edge.get(x).get(i);
                if (dist[y] > dist[x] + z) {
                    dist[y] = dist[x] + z;
                    q.offer(new int[]{y, dist[y]});
                }
            }
        }
        System.out.println(dist[n] == 1e9 ? -1 : dist[n]);
    }
}
