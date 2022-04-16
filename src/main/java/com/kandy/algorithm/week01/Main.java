package com.kandy.algorithm.week01;

import java.util.Arrays;
import java.util.Scanner;

/**
 * acwing136 邻值查找
 */
public class Main {
    public static class Node {
        long val;
        int idx;
        Node pre;
        Node next;
    };

    static int SIZE = 100005;
    static int[] a = new int[SIZE];
    static int[] ans = new int[SIZE];
    static Integer[] rk = new Integer[SIZE];
    static Node[] pos = new Node[SIZE];
    static int n;

    // 双链表插入模板，在node后面插入新结点
    static Node AddNode(Node node, int idx) {
        Node newNode = new Node();
        newNode.val = a[idx];
        newNode.idx = idx;
        node.next.pre = newNode; newNode.next = node.next;
        newNode.pre = node; node.next = newNode;
        return newNode;
    }

    // 双链表删除模板
    static void DeleteNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public static void main(String args[]) throws Exception {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = input.nextInt();
            rk[i] = i;
        }
        /* 使用下面的方法读入，比Scanner类更快一些（选学）
        DataInputStream stream = new DataInputStream(System.in);
        StringTokenizer input = new StringTokenizer(new String(stream.readAllBytes()));
        int n = Integer.parseInt(input.nextToken());
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(input.nextToken());
            rk[i] = i;
        }*/

        // rk的含义：rank[i]表示排第i名的是谁（是哪个下标）？
        // 有序序列是：a[rk[1]]，a[rk[2]]，... a[rk[n]]
        // a = [1, 5, 3, 4, 2]
        // rk = [1,   5,   3,   4,   2]
        //      a[1] a[5] a[3] a[4] a[2]
        // a[rk] = [1, 2,   3,   4,   5]
        Arrays.sort(rk, 1, n + 1, (rki, rkj) -> { return a[rki] - a[rkj]; });
        // 保护结点
        Node head = new Node();
        Node tail = new Node();
        head.next = tail;
        tail.pre = head;
        head.val = (long)-1e10;
        tail.val = (long)1e10;
        for (int i = 1; i <= n; i++) {
            // 数值：a[rk[i]]，下标：rk[i]
            pos[rk[i]] = AddNode(tail.pre, rk[i]);
        }
        for (int i = n; i > 1; i--) {
            Node curr = pos[i];
            if (a[i] - curr.pre.val <= curr.next.val - a[i]) {
                ans[i] = curr.pre.idx;
            } else {
                ans[i] = curr.next.idx;
            }
            DeleteNode(curr);
        }
        // 使用StringBuilder输出，比一行行print快很多
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            sb.append(Math.abs(a[ans[i]] - a[i]));
            sb.append(" ");
            sb.append(ans[i]);
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
