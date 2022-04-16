package com.kandy.algorithm.week01;

import com.kandy.algorithm.leetcode.ListNode;

/**
 * 141. 环形链表
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 */
public class LeetCode141 {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            head = head.next;
            if (fast == head) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode node3 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node0 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        head.next = node3;
        node3.next = node2;
        node2.next = node0;
        node0.next = node4;
        node4.next = node2;
        System.out.println(new LeetCode141().hasCycle(head));

        ListNode node11 = new ListNode(1);
        ListNode node22 = new ListNode(2);
        node11.next = node22;
        node22.next = node11;
        System.out.println(new LeetCode141().hasCycle(node11));
    }

}
