package com.kandy.algorithm.week03.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 有向图环检测方法：通过color数组进行DFS遍历
 * 0尚未访问 1正在递归访问中 2访问完毕
 */
public class LC685冗余连接II {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        //出现过的最大的点就是n
        n = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            n = Math.max(u, n);
            n = Math.max(v, n);
        }

        // 模板：出边数组初始化
        // 初态：[[], [], ... []]
        this.to = new ArrayList<>();
        this.from = new ArrayList<>();
        // color数组初始化
        color = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            this.to.add(new ArrayList<>());
            this.from.add(new ArrayList<>());
            color[i] = 0;
        }
        hasCycle = false;
        //保存冲突边、成还边
        Stack<int[]> deleteEdges = new Stack<>();
        // 加边
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            //加有向边
            addToEdge(x, y);
            if (from.get(y).size() == 2) {
                //把引起度为2的出边都加入待删列表里
                deleteEdges.add(new int[]{from.get(y).get(0), y});
                deleteEdges.add(new int[]{from.get(y).get(1), y});
            }
            hasCycle = false;
            visit();
            if (hasCycle) {
                //引起环的也加入待删列表
                deleteEdges.add(new int[]{x, y});
            }
        }
        while (!deleteEdges.isEmpty()) {
            int[] edge = deleteEdges.pop();
            int x = edge[0];
            int y = edge[1];
            removeEdge(edge[0], edge[1]);
            hasCycle = false;
            visit();
            if (hasCycle || hasConflict()) {
                addToEdge(x, y);
            } else {
                return new int[]{x, y};
            }
        }
        return null;
    }

    private boolean hasConflict() {
        for (int i = 1; i <= n; i++) {
            if (from.get(i).size() == 2) {
                return true;
            }
        }
        return false;
    }

    private void visit() {
        Arrays.fill(color, 0);
        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) {
                dfs(i);
            }
        }
    }

    // 模板：有向图深度优先遍历找环
    // color数组，记录访问状态
    private void dfs(int x) {
        // 第一步：标记正在访问
        color[x] = 1;
        // 第二步：遍历所有出边
        for (Integer y : to.get(x)) {
            //并且已经被访问过
            if (color[y] == 1) {
                hasCycle = true;//有环
                break;
            } else if (color[y] == 2) {
                //访问完毕，直接跳至下一个结点
                continue;
            } else {
                dfs(y);//否则递归访问
            }
        }
        color[x] = 2;
    }

    //加有向边
    private void addToEdge(int x, int y) {
        to.get(x).add(y);
        from.get(y).add(x);
    }

    //减有向边
    private void removeEdge(int x, int y) {
        to.get(x).remove(Integer.valueOf(y));
        from.get(y).remove(Integer.valueOf(x));
    }

    int n;
    private List<List<Integer>> to;// 出边数组
    private List<List<Integer>> from;// 入边数组
    boolean hasCycle; //是否有环
    private int[] color; //有向图访问状态 0尚未访问 1正在递归访问中 2访问完毕

    public static void main(String[] args) {
        LC685冗余连接II lc = new LC685冗余连接II();
        //case 1.有冲突，无环：无冲突，则直接返回成冲突边
        //此时[2,3] 会产生冲突，但不产生环
//        int[][] edges = {{1,2},{1,3},{2,3}};
//        lc.findRedundantDirectedConnection(edges);
//        int[] edge = lc.findRedundantDirectedConnection(edges);
//        System.out.println(Arrays.toString(edge));

        //case 2. 有环，无冲突：无环，则直接返回成环边
        //此时[4,1]会产生环，但不产生冲突
//        int[][] edges = {{1,2},{2,3},{3,4},{4,1},{1,5}};
//        int[] edge = lc.findRedundantDirectedConnection(edges);
//        System.out.println(Arrays.toString(edge));

        // case 3.有冲突，有环：这种情况下要保留冲突边，因为环的影响更优先于冲突，那么去掉环之中会导致冲突的那条边，即[parent[v],v]
        //此时[3,1]产生冲突，[4,2]产生环，要保留冲突边[3,1]，通过找到1的父节点，从而获得环之中的冲突边：[2,1]
        int[][] edges = {{2, 1}, {3, 1}, {1, 4}, {4, 2}};
        int[] edge = lc.findRedundantDirectedConnection(edges);
        System.out.println(Arrays.toString(edge));
    }
}
