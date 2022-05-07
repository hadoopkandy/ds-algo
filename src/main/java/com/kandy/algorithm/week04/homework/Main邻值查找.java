package com.kandy.algorithm.week04.homework;

import java.util.Objects;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * acwing136 邻值查找
 * <p>
 * 关键点：
 * "索引"的灵活性 按照下标Node数组、按值链表
 * 不同"索引"的数据结构之间建立"映射"关系
 * 倒序考虑问题
 */
public class Main邻值查找 {

    public static class Node {
        long val;
        int idx;

        public Node(long val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return val == node.val &&
                    idx == node.idx;
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, idx);
        }
    }

    static int SIZE = 100005;
    static int[] a = new int[SIZE]; //原始数据
    static int[] ans = new int[SIZE]; //答案数组
    static TreeSet<Node> orderSet = new TreeSet<>(((a, b) -> Long.compare(a.val, b.val)));
    static int n;

    public static void main(String args[]) throws Exception {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 1; i <= n; i++) {
            int val = input.nextInt();
            a[i] = val;
            orderSet.add(new Node(a[i], i));
        }

        //倒序考虑 第一个不用考虑
        for (int i = n; i > 1; i--) {
            Node cur = new Node(a[i], i);
            //后继
            Node next = orderSet.higher(cur);
            //前驱
            Node pre = orderSet.lower(cur);

            if (next == null) {
                ans[i] = pre.idx;
            } else if (pre == null) {
                ans[i] = next.idx;
            } else if (a[i] - pre.val <= next.val - a[i]) { //与前驱的差值，与后继的差值比较 谁小要谁
                ans[i] = pre.idx;
            } else {
                ans[i] = next.idx;
            }
            orderSet.remove(cur);
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
