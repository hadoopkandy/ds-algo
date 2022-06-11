package com.kandy.algorithm.week08;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Bellman-Ford算法 可处理负权边 时间复杂度O(nm)
 * 基于动态规划和迭代思想
 * 1.扫描所有边(x,y,z) 若dist[y] > dist[x] + z, 则用dist[x] + z更新dist[y]
 * 2.重复上述步骤，直到没有更新操作发生 至多需要n-1轮
 */
public class LC743网络延迟时间 {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[] dist = new int[n + 1]; //网络节点1到n，下标0浪费
        for (int i = 1; i <= n; i++) dist[i] = (int) 1e9;
        dist[k] = 0; //起点
        //至多n-1轮
        for (int round = 1; round <= n - 1; round++) {
            boolean updated = false;
            for (int i = 0; i < times.length; i++) {
                int x = times[i][0];
                int y = times[i][1];
                int z = times[i][2];
                if (dist[y] > dist[x] + z) {
                    dist[y] = dist[x] + z;
                    updated = true;
                }
            }
            if (!updated) break; //如果这一轮没有更新，循环提前结束
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) ans = Math.max(ans, dist[i]);
        return (ans ==  (int)1e9) ? -1 : ans;
    }

    //Dijkstra算法 时间复杂度 O（n*n +m）
    public int networkDelayTime2(int[][] times, int n, int k) {
        dist = new int[n + 1]; //网络节点1到n，下标0浪费
        ver = new ArrayList<List<Integer>>(); // 另一端点
        edge = new ArrayList<List<Integer>>(); // 边权
        expand = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            ver.add(new ArrayList<Integer>());
            edge.add(new ArrayList<Integer>());
            dist[i] = (int) 1e9;
        }
        dist[k] = 0;

        //建图,存在出边数组
        for (int[] t : times) {
            int x = t[0];
            int y = t[1];
            int z = t[2];
            // 出边数组 addEdge 模板
            ver.get(x).add(y);
            edge.get(x).add(z);

        }

        //n个点，每个点都需要拓展一次
        for (int round = 1; round <= n; round++) {
            int temp = (int) 1e9, x = 0;
            //考虑所有点
            for (int i = 1; i <= n; i++) {
                //没拓展过的点里找距离最小的
                if (!expand[i] && dist[i] < temp) {
                    temp = dist[i]; //最小距离
                    x = i;//最小点
                }
            }
            expand[x] = true;
            for (int i = 0; i < ver.get(x).size(); i++) {
                int y = ver.get(x).get(i);//x可以到的端点
                int z = edge.get(x).get(i);//x可以到的端点的边权
                if (dist[y] > dist[x] + z) {
                    dist[y] = dist[x] + z;//更新dist[y]
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) ans = Math.max(ans, dist[i]);
        return (ans == (int)1e9) ? -1 : ans;
    }

    //堆优化的Dijkstra算法 时间复杂度 O（m*logn）
    public int networkDelayTime3(int[][] times, int n, int k) {
        dist = new int[n + 1]; //网络节点1到n，下标0浪费
        ver = new ArrayList<List<Integer>>(); // 另一端点
        edge = new ArrayList<List<Integer>>(); // 边权
        expand = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            ver.add(new ArrayList<Integer>());
            edge.add(new ArrayList<Integer>());
            dist[i] = (int) 1e9;
        }
        dist[k] = 0;
        //建图,存在出边数组
        for (int[] t : times) {
            int x = t[0];
            int y = t[1];
            int z = t[2];
            // 出边数组 addEdge 模板
            ver.get(x).add(y);
            edge.get(x).add(z);

        }

        //小根堆
        q = new PriorityQueue<>((a, b) -> { return a[0] - b[0]; });
        q.offer(new int[]{0, k});//距离是0，编号是k
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int x = top[1];
            /**
             * [0,1]
             * [7,2] [2,3] 取[2,3]
             * [7,2] [6,5] [5,2] 取[5,2]
             */
            if (expand[x]) continue;//懒惰删除 {7,2}
            expand[x] = true;
            for (int i = 0; i < ver.get(x).size(); i++) {
                int y = ver.get(x).get(i);
                int z = edge.get(x).get(i);
                if (dist[y] > dist[x] + z) {
                    dist[y] = dist[x] + z;
                    q.offer(new int[]{dist[y], y});
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) ans = Math.max(ans, dist[i]);
        return (ans == (int) 1e9) ? -1 : ans;
    }

    int[] dist;
    private List<List<Integer>> ver; // 另一端点(出边数组)
    private List<List<Integer>> edge;// 边权
    boolean[] expand; //标记每个点是否被标记

    PriorityQueue<int[]> q;//堆，每个结点是长度为2的数组 [距离，编号]

}
