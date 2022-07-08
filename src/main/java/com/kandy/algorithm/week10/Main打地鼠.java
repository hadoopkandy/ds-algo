package com.kandy.algorithm.week10;

import java.io.*;
import java.util.*;

public class Main打地鼠 {

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int c;

        public Node(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            if (this.c == o.c) {
                if (this.x + this.y == o.x + o.y) {
                    return o.x - this.x;
                }
                return o.x + o.y - this.x - this.y;
            }
            return this.c - o.c;

        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        //地鼠
        int[][] mice = new int[n][2];
        for (int i = 0; i < n; i++) {
            mice[i][0] = scanner.nextInt();
            mice[i][1] = scanner.nextInt();
        }

        //植物
        int[][] plants = new int[m][2];
        for (int i = 0; i < m; i++) {
            plants[i][0] = scanner.nextInt();
            plants[i][1] = scanner.nextInt();
        }

        //地鼠坐标计数
        Map<String, Integer> miceCounter = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String key = mice[i][0] + "_" + mice[i][1];
            miceCounter.put(key, miceCounter.getOrDefault(key, 0) + 1);
        }

        Set<String> flowerSet = new HashSet<>();
        for (int i = 0; i < m; i++) {
            String key = plants[i][0] + "_" + plants[i][1];
            if (!flowerSet.contains(key)) {
                flowerSet.add(key);
            }
        }

        TreeSet<Node> treeset = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            String key = mice[i][0] + "_" + mice[i][1];
            int count = miceCounter.getOrDefault(key, 0);
            if (flowerSet.contains(key)) {
                continue;
            }
            treeset.add(new Node(mice[i][0], mice[i][1], count));
        }

        Node node = treeset.last();
        System.out.print(node.x + " " + node.y);
    }
}
/*
https://blog.csdn.net/arxobject/article/details/119920726
题目:
你有一片菜地，种着M株植物，每株植物的位置可以用一个二维坐标来表示。
你的地里闯进了一只超级地鼠，这只地鼠使出了瞬间绝技在地里到处乱窜。幸运的是，
你统计出了前段时间内，地鼠在乱窜过程中出现过的N个位置(N个二维坐标，位置可能重复).
你希望统计一下地鼠在哪里出现的次数最多，然后拿起锤子向那个地方砸去，毕竟这样砸中的
几率最大。但有一个前提，你不想砸到植物所在的位置。

输入

第一行两个整数N，M
接下来N行每行两个整数，表示地鼠出现过的位置.
接下来M行每行两个整数，表示植物的位置。

输出

一个位置，由两个整数和一个空格组成。
如果答案不唯一，输出其中横坐标之和(x+y)最小的，若还不唯一，
输出其中横坐标(x)最小的。

示例1

3 1
1 2
2 2
1 2
1 2

说明: 地鼠出现过3次，分别是(1,2),(2,2)和(1,2)三个坐标。其中在(1,2)出现过2次
在(2,2)出现过1次.本来你应该砸(1,2)。但(1,2)种着一株植物。
所以(2,2)是不砸植物的前提下，砸到地鼠几率最高的位置。

输出:
2 2

数据范围
1<=N , M<=1000 ,坐标范围在0 ~ 10,000,000之间。

分析：
1.用一个hash表来统计地鼠出现过的次数，key为坐标，value为次数。
2.用一个hashset来记录植物的坐标，作为过滤用。
3.创建一个Node节点，主要是用来排序，Node的属性是x横坐标,y纵坐标,c次数。排序规则 (1) c的大小 (2) x+y的值 (3) x的值
4.排序可以用平衡树 TreeSet,或者大堆 PriorityQueue
 */
