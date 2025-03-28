package com.kandy.algorithm.week01;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.acwing.com/problem/content/description/138/
 * acwing136 邻值查找
 * 按数值排序,建立有序双链表
 * 链表虽不能随机访问，但是可以记录A数组每个下标对应的链表结点
 * 倒序考虑每个下标,只需要在链表中查找前驱、后继,然后删除节点
 *
 * 关键点：
 * "索引"的灵活性 按照下标Node数组、按值链表
 * 不同"索引"的数据结构之间建立"映射"关系
 * 倒序考虑问题
 */
public class L06_Main邻值查找 {
    public static class Node {
        long val;//原始数组的值
        int idx;//原始数组的下标
        Node pre;
        Node next;
    }

    static int SIZE = 100005;
    static int[] a = new int[SIZE]; //原始数据
    static int[] ans = new int[SIZE]; //答案数组
    static Integer[] rk = new Integer[SIZE]; //rk[i]表示排第i名的是谁（是哪个下标）
    static Node[] pos = new Node[SIZE]; //指针数据指向链表
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

    // 双链表删除模板  被删除的点 前驱后继互相指
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
        //倒序考虑 第一个不用考虑
        for (int i = n; i > 1; i--) {
            Node curr = pos[i];
            //与前驱的差值，与后继的差值比较
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
/*
输入:
5
1 5 3 4 2

输出:
4 1
2 1
1 3
1 1

1.根据题目的意思,得出是求前驱 后继
2.倒序考虑问题
如果正着考虑,每次都要排序，要考虑当前这个数插在有序序列的什么位置
先把所有数全排好序,倒着考虑,每次求完前驱后继后,把当前这个数删除即可
先考虑 2的前驱后继,把2 删了
再考虑 4的前驱后继,把4 删了
依次类推

3.数据结构
a[] 原始数据 [1,5,3,4,2]
rk[] 存排名的下标  [1,5,3,4,2] （排第1的是下标1, 排第2的是下标5,排第3的是下标3,排第4的是下标4,排第5的下标2）
pos[] 指针数组,通过它索引到链表
    pos[rk[i]] = a[rk[i]]
    pos[1] = 1  idx = 1
    pos[5] = 2  idx = 5
    pos[3] = 3  idx = 3
    pos[4] = 4  idx = 4
    pos[2] = 5  idx = 2
a[rk[i]] 有序序列 pos[i] 找到链表节点
通过pos[i] 可以找到a[i] 在双链表中的Node节点
带保护节点head tail的双链表
 */
